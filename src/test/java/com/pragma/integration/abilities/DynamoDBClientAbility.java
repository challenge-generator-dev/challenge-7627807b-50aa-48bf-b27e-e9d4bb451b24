package com.pragma.integration.abilities;

import net.serenitybdd.screenplay.Ability;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

/**
 * Habilidad Screenplay que permite a los actores interactuar con DynamoDB.
 * Proporciona metodos para realizar operaciones CRUD sobre la tabla de pedidos.
 * 
 * Esta habilidad encapsula el cliente de DynamoDB y proporciona una interfaz
 * simplificada para las pruebas de integracion.
 */
public class DynamoDBClientAbility implements Ability {

    private final DynamoDbClient client;
    private final String tableName;
    private final String endpoint;

    private DynamoDBClientAbility(Builder builder) {
        this.tableName = builder.tableName;
        this.endpoint = builder.endpoint;

        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                builder.accessKey != null ? builder.accessKey : "dummy",
                builder.secretKey != null ? builder.secretKey : "dummy"
        );

        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        DynamoDbClient.Builder clientBuilder = DynamoDbClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.of(builder.region != null ? builder.region : "us-east-1"));

        if (builder.endpoint != null && !builder.endpoint.isEmpty()) {
            clientBuilder.endpointOverride(URI.create(builder.endpoint));
        }

        this.client = clientBuilder.build();
    }

    /**
     * Crea una nueva instancia del builder para configurar la habilidad.
     */
    public static Builder withConfiguration() {
        return new Builder();
    }

    /**
     * Escribe un pedido en la tabla DynamoDB.
     */
    public void writeOrder(String orderId, Map<String, AttributeValue> item) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        client.putItem(request);
    }

    /**
     * Lee un pedido de la tabla DynamoDB por su clave primaria.
     */
    public Optional<Map<String, AttributeValue>> readOrder(String orderId) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                .build();

        GetItemResponse response = client.getItem(request);

        if (response.hasItem() && !response.item().isEmpty()) {
            return Optional.of(response.item());
        }
        return Optional.empty();
    }

    /**
     * Elimina un pedido de la tabla DynamoDB.
     */
    public void deleteOrder(String orderId) {
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                .build();

        client.deleteItem(request);
    }

    /**
     * Verifica si existe un pedido en la tabla.
     */
    public boolean orderExists(String orderId) {
        return readOrder(orderId).isPresent();
    }

    /**
     * Obtiene el cliente de DynamoDB para operaciones avanzadas.
     */
    public DynamoDbClient getClient() {
        return client;
    }

    /**
     * Obtiene el nombre de la tabla configurada.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Builder para configurar la habilidad de DynamoDB.
     */
    public static class Builder {
        private String tableName = "orders";
        private String endpoint;
        private String region = "us-east-1";
        private String accessKey;
        private String secretKey;

        public Builder tableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public Builder accessKey(String accessKey) {
            this.accessKey = accessKey;
            return this;
        }

        public Builder secretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public DynamoDBClientAbility build() {
            return new DynamoDBClientAbility(this);
        }
    }
}