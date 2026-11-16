# language: es
Característica: Persistencia de pedidos en DynamoDB
  Como automatizador de pruebas
  Quiero validar que los pedidos se persistieron correctamente en DynamoDB
  Para asegurar la integridad de los datos en la base de datos

  Antecedentes:
    Dado que el cliente de DynamoDB está configurado para la tabla 'orders'
    Y la tabla 'orders' está vacía antes de cada escenario

  @writting
  Escenario: Guardar un pedido nuevo en DynamoDB
    Cuando escribo un pedido con los siguientes datos:
      | orderId   | customerId | status    | totalAmount |
      | ORD-001   | CUST-100   | PENDING   | 1500.50     |
    Y el pedido tiene los siguientes items:
      | productId | quantity | price   |
      | PROD-001  | 2        | 500.00  |
      | PROD-002  | 1        | 500.50  |
    Entonces el pedido debe ser persistido exitosamente en DynamoDB
    Y el código de estado de la operación debe ser 200

  @writting
  Escenario: Guardar múltiples pedidos para el mismo cliente
    Cuando escribo el pedido "ORD-002" para el cliente "CUST-100" con estado "CONFIRMED" y monto "2500.00"
    Y el pedido tiene un item con productId "PROD-003", quantity 5 y price "500.00"
    Y escribo el pedido "ORD-003" para el cliente "CUST-100" con estado "SHIPPED" y monto "750.25"
    Y el pedido tiene un item con productId "PROD-004", quantity 1 y price "750.25"
    Entonces ambos pedidos deben ser persistidos exitosamente
    Y debo poder consultar los pedidos del cliente "CUST-100"

  @reading
  Escenario: Leer un pedido existente por su ID
    Dado que existe un pedido con orderId "ORD-004", customerId "CUST-200", status "PENDING" y totalAmount "999.99"
    Y el pedido tiene un item con productId "PROD-005", quantity 3 y price "333.33"
    Cuando consulto el pedido con orderId "ORD-004" desde DynamoDB
    Entonces debo obtener los datos del pedido
    Y el customerId debe ser "CUST-200"
    Y el status debe ser "PENDING"
    Y el totalAmount debe ser "999.99"
    Y debe tener exactamente 1 item

  @reading
  Escenario: Validar estructura de los items del pedido
    Dado que existe un pedido con orderId "ORD-005", customerId "CUST-300", status "CONFIRMED" y totalAmount "3000.00"
    Y el pedido tiene los siguientes items:
      | productId | quantity | price   |
      | PROD-010  | 10       | 100.00  |
      | PROD-011  | 5        | 200.00  |
      | PROD-012  | 1        | 500.00  |
    Cuando consulto el pedido con orderId "ORD-005" desde DynamoDB
    Entonces debo obtener los datos del pedido
    Y el primer item debe tener productId "PROD-010", quantity 10 y price "100.00"
    Y el segundo item debe tener productId "PROD-011", quantity 5 y price "200.00"
    Y el tercer item debe tener productId "PROD-012", quantity 1 y price "500.00"

  @integration
  Escenario: Persistir y luego leer un pedido para validar consistencia
    Cuando escribo un pedido con los siguientes datos:
      | orderId   | customerId | status    | totalAmount |
      | ORD-006   | CUST-400   | DELIVERED | 4500.75     |
    Y el pedido tiene los siguientes items:
      | productId | quantity | price    |
      | PROD-020  | 4        | 1000.00  |
      | PROD-021  | 2        | 250.375  |
    Y el pedido fue persistido exitosamente
    Cuando consulto el pedido con orderId "ORD-006" desde DynamoDB
    Entonces debo obtener los datos del pedido
    Y el orderId debe ser "ORD-006"
    Y el customerId debe ser "CUST-400"
    Y el status debe ser "DELIVERED"
    Y el totalAmount debe ser "4500.75"
    Y debe tener exactamente 2 items

  @negative
  Escenario: Intentar leer un pedido que no existe
    Cuando consulto el pedido con orderId "NON-EXISTENT" desde DynamoDB
    Entonces no debo obtener ningún resultado
    Y el resultado debe ser vacío o nulo

  @negative
  Escenario: Escribir pedido con datos inválidos
    Cuando intento escribir un pedido con orderId vacío
    Entonces la operación debe fallar con un error de validación
    Y no debo persistir ningún dato en DynamoDB