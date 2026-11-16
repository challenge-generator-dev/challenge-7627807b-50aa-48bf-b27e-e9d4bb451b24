# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener el proyecto completo y arrancable. Guarda los archivos que te devuelva en la
carpeta del repo, respetando exactamente las rutas.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/java/com/pragma/integration/tasks/WriteOrderToDynamoDB.java` — La fase "Configuración inicial" se implementa aqui: este archivo es el ejercicio.
- `src/test/java/com/pragma/integration/questions/OrderPersistenceQuestions.java` — La fase "Creación de pruebas de escritura" se implementa aqui: este archivo es el ejercicio.

## Mapa de componentes

El repo tiene una carpeta por componente. Cada uno se instala y verifica dentro de la suya.

| Componente | Carpeta | Rol | Capa | Track | Tecnologias |
|---|---|---|---|---|---|
| `app` | (raiz) | practice, chapter | testing | Automatizador | - |
| `infra-local` | (raiz) | support | infra | Compose local | DynamoDB |

## Orden de arranque

1. `docker compose up -d` (en la raiz) — levanta la infraestructura local.
2. `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` — componente `app` (practice, chapter).

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Boilerplate del stack que falta

Sin esto no compila ni arranca. Es andamiaje, no toca nada de lo pedagogico:

- **XSteps.java** — Sin los step definitions, los pasos Gherkin no tienen implementacion y Cucumber falla con "step undefined".

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/test/java/com/pragma/integration/abilities/DynamoDBClientAbility.java` — `Builder.endpointOverride`: Se invoca `endpointOverride` sobre `Builder`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integration/abilities/DynamoDBClientAbility.java` — `Builder.build`: Se invoca `build` sobre `Builder`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Imports sin uso — no rompen la compilacion

Limpieza opcional: el proyecto compila igual con ellos:

- `src/test/java/com/pragma/integration/tasks/WriteOrderToDynamoDB.java` — `DynamoDBClientAbility`
- `src/test/java/com/pragma/integration/questions/OrderPersistenceQuestions.java` — `DynamoDBClientAbility`
- `src/test/java/com/pragma/integration/questions/OrderPersistenceQuestions.java` — `ReadOrderFromDynamoDB`

## Como saber que terminaste

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Tecnología Java Selenium, Advanced L2

### Brecha de conocimiento
Automatizar pruebas de integracion sobre una tabla DynamoDB, Automatiza pruebas de integracion que validan lecturas y escrituras en una tabla DynamoDB

### Misión / candidato
Cubrir el flujo de persistencia de pedidos

### Reto
- Tema: Automatizacion de pruebas de integracion contra DynamoDB
- Seniority: advanced-l2
- Tipo: practical
- Título: Automatización de pruebas de integración en DynamoDB
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración inicial — objetivo: Configurar el entorno de pruebas y conectarse a la tabla DynamoDB. — entregable (NO resolver): Entorno de pruebas configurado y conexión a DynamoDB establecida.
- Fase 2: Creación de pruebas de escritura — objetivo: Crear pruebas automatizadas para validar la escritura de datos en DynamoDB. — entregable (NO resolver): Pruebas automatizadas para la escritura de datos en DynamoDB.
- Fase 3: Creación de pruebas de lectura — objetivo: Crear pruebas automatizadas para validar la lectura de datos en DynamoDB. — entregable (NO resolver): Pruebas automatizadas para la lectura de datos en DynamoDB.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (entrega de archivos).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Entrega de archivos
Entregá cada archivo con su ruta completa, respetando exactamente la estructura de rutas
indicada por los marcadores: un bloque por archivo, precedido por su ruta. No generes ZIPs
ni archivos adjuntos.
La entrega debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: Entregá los archivos completos al finalizar. No preguntes si el usuario
quiere recibirlos. Simplemente entregalos; No debes desplegar en el chat el resumen de lo que arreglaste, solo entregá los archivos.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del último archivo entregado
No preguntes si el usuario quiere los archivos: simplemente entregalos siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.13</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>reto</artifactId>
    <version>1.0.0</version>
    <name>reto</name>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>8.1.0</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>8.1.0</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>dynamodb</artifactId>
            <version>2.59.1</version>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>sts</artifactId>
            <version>2.59.1</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay</artifactId>
            <version>4.3.4</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/test/java/resources/features/orders_persistence.feature ===
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

// === ARCHIVO: src/main/java/RunCucumberTest.java ===
package com.pragma;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.pragma.integration.steps", "com.pragma.integration.runners"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml"
    },
    monochrome = true,
    tags = "@integration"
)
public class RunCucumberTest {
}

// === ARCHIVO: src/test/java/com/pragma/integration/runners/RunIntegrationTest.java ===
package com.pragma.integration.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.pragma.integration.steps",
        "com.pragma.integration.tasks",
        "com.pragma.integration.questions",
        "com.pragma.integration.models",
        "com.pragma.integration.abilities"
    },
    plugin = {
        "pretty",
        "serenity:target/site/serenity/index.html",
        "json:target/serenity-reports/serenity.json"
    },
    monochrome = true,
    dryRun = false,
    tags = "@integration"
)
public class RunIntegrationTest {
}

// === ARCHIVO: src/test/java/com/pragma/integration/tasks/WriteOrderToDynamoDB.java ===
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

// === ARCHIVO: src/test/java/com/pragma/integration/tasks/ReadOrderFromDynamoDB.java ===
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

// === ARCHIVO: src/test/java/com/pragma/integration/questions/OrderPersistenceQuestions.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package com.pragma.integration.questions;

import com.pragma.integration.abilities.DynamoDBClientAbility;
import com.pragma.integration.models.Order;
import com.pragma.integration.tasks.ReadOrderFromDynamoDB;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class OrderPersistenceQuestions {

    public static Question<Order> orderWithId(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): orderWithId");
    }

    public static Question<Boolean> orderExists(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): orderExists");
    }

    private static class OrderByIdQuestion implements Question<Order> {
        private final String orderId;

        public OrderByIdQuestion(String orderId) {
            this.orderId = orderId;
        }

        @Override
        public Order answeredBy(Actor actor) {
          throw new UnsupportedOperationException("TODO(reto): answeredBy");
        }
    }

    private static class OrderExistsQuestion implements Question<Boolean> {
        private final String orderId;

        public OrderExistsQuestion(String orderId) {
            this.orderId = orderId;
        }

        @Override
        public Boolean answeredBy(Actor actor) {
          throw new UnsupportedOperationException("TODO(reto): answeredBy");
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/integration/models/Order.java ===
package com.pragma.integration.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Modelo de dominio que representa un pedido en el sistema de pruebas de integración.
 * Este modelo se utiliza para mapear los datos entre la aplicación y DynamoDB.
 */
public class Order {

    private String orderId;
    private String customerId;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    private String status;

    public Order() {
        this.items = new ArrayList<>();
    }

    public Order(String orderId, String customerId, List<OrderItem> items, BigDecimal totalAmount, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items != null ? items : new ArrayList<>();
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items != null ? items : new ArrayList<>();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addItem(OrderItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    public BigDecimal calculateTotal() {
        if (items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return items.stream()
                .filter(item -> item.getPrice() != null && item.getQuantity() != null)
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(customerId, order.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", items=" + (items != null ? items.size() : 0) +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private String orderId;
        private String customerId;
        private List<OrderItem> items = new ArrayList<>();
        private BigDecimal totalAmount;
        private String status;

        public OrderBuilder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderBuilder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public OrderBuilder items(List<OrderItem> items) {
            this.items = items;
            return this;
        }

        public OrderBuilder addItem(OrderItem item) {
            this.items.add(item);
            return this;
        }

        public OrderBuilder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public OrderBuilder status(String status) {
            this.status = status;
            return this;
        }

        public Order build() {
            return new Order(orderId, customerId, items, totalAmount, status);
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/integration/models/OrderItem.java ===
package com.pragma.integration.models;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Representa un elemento individual dentro de un pedido.
 * Cada item contiene información del producto, cantidad y precio unitario.
 */
public class OrderItem {

    private String productId;
    private Integer quantity;
    private BigDecimal price;

    public OrderItem() {
    }

    public OrderItem(String productId, Integer quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        if (price == null || quantity == null) {
            return BigDecimal.ZERO;
        }
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem that = (OrderItem) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

// === ARCHIVO: src/test/java/com/pragma/integration/abilities/DynamoDBClientAbility.java ===
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

// === ARCHIVO: docker-compose.yml ===
# Infraestructura local del reto. SOLO desarrollo: credenciales de ejemplo (local-dev-only), puertos en loopback.
# Levantar: docker compose up -d --wait
name: infra-local
services:
  dynamodb-local:
    image: "amazon/dynamodb-local:2.5.2"
    command: ["-jar","DynamoDBLocal.jar","-sharedDb","-inMemory"]
    ports:
      - "127.0.0.1:8000:8000"
    healthcheck:
      test: ["CMD-SHELL","bash -c ':> /dev/tcp/127.0.0.1/8000' || exit 1"]
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 5s


// === ARCHIVO: infra-local/README.md ===
# Infraestructura local

Servicios de soporte para el reto, definidos en `docker-compose.yml` (raiz del repo).

```bash
docker compose up -d --wait
```

| Servicio | Imagen | Puertos (host) |
|---|---|---|
| DynamoDB Local | `amazon/dynamodb-local:2.5.2` | 8000 |

Credenciales: solo de desarrollo local (`local-dev-only`, `local`). No son secretos ni deben reutilizarse.
Los scripts de `infra-local/` (tablas, topics) se ejecutan con el compose en estado healthy.

Tablas de DynamoDB: `orders` (pk `id`).


// === ARCHIVO: infra-local/dynamodb/create-tables.sh ===
#!/usr/bin/env bash
# Crea las tablas en DynamoDB Local (idempotente). Credenciales dummy: DynamoDB Local no valida firmas.
set -euo pipefail

export AWS_ACCESS_KEY_ID=local
export AWS_SECRET_ACCESS_KEY=local
export AWS_REGION=us-east-1
ENDPOINT="${DYNAMODB_ENDPOINT:-http://localhost:8000}"

create_table() {
  local name="$1" pk="$2" sk="${3:-}"
  if aws dynamodb describe-table --table-name "$name" --endpoint-url "$ENDPOINT" >/dev/null 2>&1; then
    echo "Tabla $name ya existe"
    return
  fi
  local attrs="AttributeName=$pk,AttributeType=S"
  local keys="AttributeName=$pk,KeyType=HASH"
  if [ -n "$sk" ]; then
    attrs="$attrs AttributeName=$sk,AttributeType=S"
    keys="$keys AttributeName=$sk,KeyType=RANGE"
  fi
  # shellcheck disable=SC2086
  aws dynamodb create-table --table-name "$name" --attribute-definitions $attrs --key-schema $keys \
    --billing-mode PAY_PER_REQUEST --endpoint-url "$ENDPOINT" >/dev/null
  echo "Tabla $name creada"
}

create_table "orders" "id"

```
