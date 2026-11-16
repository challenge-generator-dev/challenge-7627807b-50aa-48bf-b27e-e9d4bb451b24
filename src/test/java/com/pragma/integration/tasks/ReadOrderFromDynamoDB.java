package com.pragma.integration.tasks;

import com.pragma.integration.abilities.DynamoDBClientAbility;
import com.pragma.integration.models.Order;
import com.pragma.integration.models.OrderItem;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadOrderFromDynamoDB implements Task {

    private final String orderId;
    private Order retrievedOrder;

    public ReadOrderFromDynamoDB(String orderId) {
        this.orderId = orderId;
    }

    public static ReadOrderFromDynamoDB withOrderId(String orderId) {
        return new ReadOrderFromDynamoDB(orderId);
    }

    public Order getRetrievedOrder() {
        return retrievedOrder;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        DynamoDBClientAbility ability = actor.abilityTo(DynamoDBClientAbility.class);
        DynamoDbClient dynamoDBClient = ability.getClient();
        String tableName = ability.getTableName();

        Map<String, AttributeValue> key = Map.of(
            "orderId", AttributeValue.builder().s(orderId).build()
        );

        GetItemRequest getItemRequest = GetItemRequest.builder()
            .tableName(tableName)
            .key(key)
            .build();

        Map<String, AttributeValue> item = dynamoDBClient.getItem(getItemRequest).item();

        if (item != null && !item.isEmpty()) {
            retrievedOrder = mapToOrder(item);
        }
    }

    private Order mapToOrder(Map<String, AttributeValue> item) {
        String orderId = item.get("orderId") != null ? item.get("orderId").s() : null;
        String customerId = item.get("customerId") != null ? item.get("customerId").s() : null;
        BigDecimal totalAmount = item.get("totalAmount") != null 
            ? new BigDecimal(item.get("totalAmount").n()) 
            : BigDecimal.ZERO;
        String status = item.get("status") != null ? item.get("status").s() : null;

        List<OrderItem> orderItems = new ArrayList<>();
        if (item.get("items") != null && item.get("items").l() != null) {
            for (AttributeValue orderItemAttr : item.get("items").l()) {
                Map<String, AttributeValue> orderItemMap = orderItemAttr.m();
                String productId = orderItemMap.get("productId").s();
                Integer quantity = Integer.parseInt(orderItemMap.get("quantity").n());
                BigDecimal price = new BigDecimal(orderItemMap.get("price").n());
                orderItems.add(new OrderItem(productId, quantity, price));
            }
        }

        return new Order(orderId, customerId, orderItems, totalAmount, status);
    }
}