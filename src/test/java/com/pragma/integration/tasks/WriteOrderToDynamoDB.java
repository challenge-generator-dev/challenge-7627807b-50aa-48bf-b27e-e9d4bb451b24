// TODO(reto): archivo stubbeado; implementa los cuerpos.
package com.pragma.integration.tasks;

import com.pragma.integration.abilities.DynamoDBClientAbility;
import com.pragma.integration.models.Order;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

public class WriteOrderToDynamoDB implements Task {

    private final Order order;

    public WriteOrderToDynamoDB(Order order) {
        this.order = order;
    }

    public static WriteOrderToDynamoDB withOrder(Order order) {
      throw new UnsupportedOperationException("TODO(reto): withOrder");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
      throw new UnsupportedOperationException("TODO(reto): performAs");
    }
}