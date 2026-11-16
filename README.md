# Automatización de pruebas de integración en DynamoDB

Automatizar pruebas de integración sobre una tabla DynamoDB para cubrir el flujo de persistencia de pedidos. El sistema debe validar la correcta lectura y escritura de datos en la tabla DynamoDB.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Automatizacion de pruebas de integracion contra DynamoDB |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y entregará cada archivo con su ruta.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y entregará cada archivo con su ruta. Guárdalos en la carpeta donde quieras trabajar, respetando las rutas.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no entrega todos los archivos al terminar el análisis, escríbele: "entrégame todos los archivos con su ruta".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración inicial

**Objetivo:** Configurar el entorno de pruebas y conectarse a la tabla DynamoDB.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Configurar el entorno de pruebas con las dependencias necesarias.
- Establecer la conexión con la tabla DynamoDB.
- Verificar que la conexión se realiza correctamente y que se puede acceder a la tabla.

**Entregable:** Entorno de pruebas configurado y conexión a DynamoDB establecida.

<details>
<summary>Pistas de conocimiento</summary>

- Configuración de entornos de pruebas.
- Conexión a bases de datos NoSQL.

</details>

### Fase 2: Creación de pruebas de escritura

**Objetivo:** Crear pruebas automatizadas para validar la escritura de datos en DynamoDB.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Diseñar y escribir pruebas que inserten datos en la tabla DynamoDB.
- Verificar que los datos se insertan correctamente y que se pueden recuperar.

**Entregable:** Pruebas automatizadas para la escritura de datos en DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Diseño de pruebas de integración.
- Validación de datos en bases de datos NoSQL.

</details>

### Fase 3: Creación de pruebas de lectura

**Objetivo:** Crear pruebas automatizadas para validar la lectura de datos en DynamoDB.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Diseñar y escribir pruebas que lean datos de la tabla DynamoDB.
- Verificar que los datos se leen correctamente y que coinciden con los datos insertados.

**Entregable:** Pruebas automatizadas para la lectura de datos en DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Diseño de pruebas de integración.
- Validación de datos en bases de datos NoSQL.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las pruebas de integración y por qué son importantes en este contexto?
- **paraQueSirve**: ¿Para qué sirven las pruebas de integración en el flujo de persistencia de pedidos?
- **comoSeUsa**: ¿Cómo se usan las pruebas de integración para validar la lectura y escritura en DynamoDB?
- **erroresComunes**: ¿Qué errores comunes pueden ocurrir al automatizar pruebas de integración contra DynamoDB?
- **queDecisionesImplica**: ¿Qué decisiones implica la automatización de pruebas de integración en este contexto?

## Criterios de Evaluacion

- Configuración correcta del entorno de pruebas y conexión a DynamoDB.
- Creación de pruebas automatizadas para la escritura de datos en DynamoDB.
- Creación de pruebas automatizadas para la lectura de datos en DynamoDB.
- Identificación y manejo de errores comunes en las pruebas de integración.
- Justificación de las decisiones tomadas en la automatización de las pruebas de integración.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT) si no tenes un agente en el IDE. Declara lo mismo que AGENTS.md; los archivos que te devuelva el chat los guardas en esta carpeta respetando las rutas.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Mapa de componentes

El repo tiene una carpeta por componente. Cada uno se instala y verifica dentro de la suya.

| Componente | Carpeta | Rol | Capa | Track | Tecnologias |
|---|---|---|---|---|---|
| `app` | (raiz) | practice, chapter | testing | Automatizador | - |
| `infra-local` | (raiz) | support | infra | Compose local | DynamoDB |

## Orden de arranque

1. `docker compose up -d` (en la raiz) — levanta la infraestructura local.
2. `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` — componente `app` (practice, chapter).

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
