# rNet Spring Demo

This project is a straightforward demo demonstrating how to integrate the **rNet Protocol** within a **Spring Boot** application. It serves as a practical guide for developers looking to understand the setup, configuration, and execution flow of connecting to AI models (such as Gemini and Text Embeddings) using the `rnet-core` library.

**Official Website**: [https://www.rnetai.org/](https://www.rnetai.org/)

## What it Demonstrates

- **rNet Protocol Integration**: Shows how to include and configure the `io.github.rnetai:rnet-core` library.
- **AI Model Resource Mapping**: Demonstrates how to define AI models as resources using `@Resource` and `RNetResource`.
- **Endpoint Protection**: Uses the custom `@RNetEndpoint` annotation to specify which AI models are consumed by which endpoints.
- **Security**: Includes a simple JWT-based authentication flow to secure protected AI endpoints.

## Project Structure

The codebase follows a standard Spring Boot MVC architecture:

- **`configuration/`**: Contains general beans like `RestTemplateConfig`.
- **`controller/`**: Exposes the REST APIs (`AuthController`, `GeminiController`, `EmbeddingController`, `HealthController`).
- **`dto/`**: Data Transfer Objects for managing request and response payloads.
- **`external/ai/`**: Contains the rNet wrappers for external AI models (e.g., `Gemini2_5FlashLite.java`, `TextEmbedding3Small.java`).
- **`security/`**: JWT configurations, filters, and entry points securing the `/api/gemini/**` endpoints.
- **`service/`**: Contains the core business logic, preparing payloads and invoking the rNet models.

## How rNet Protocol Works Here

1. **Define the Model**: Create a class in `external/ai/` that extends `RNetResource` and annotate it with `@Resource("model-name")`.
   ```java
   @Component
   @Resource("gemini-2.5-flash-lite")
   public class Gemini2_5FlashLite extends RNetResource { }
   ```
2. **Annotate the Endpoint**: In the controller, use `@RNetEndpoint` to declare that an endpoint uses specific AI resources.
   ```java
   @RNetEndpoint(path = "/generate", method = RequestMethod.POST, usageResources = {Gemini2_5FlashLite.class})
   public ResponseEntity<?> generate(...) { ... }
   ```
3. **Call the Model**: In the service layer, use the instantiated model resource to make the actual call, passing the `HttpServletRequest`, a `RestTemplate`, and the prepared payload.
   ```java
   return geminiModel.call(request, restTemplate, requestBody);
   ```

## Prerequisites

- Java 21
- Maven

## Configuration

Before running the application, developers need to configure the rNet developer key. Open `src/main/resources/application.yaml` and update the `rnet.developer.key` field:

```yaml
rnet:
  developer:
    key: "<developer-key-here>"
```

## Build and Run

Developers can run the application directly using the included Maven wrapper:

```bash
./mvnw spring-boot:run
```

Alternatively, developers can build the executable JAR and run it:

```bash
./mvnw clean package
java -jar target/rnet-spring-demo-0.0.1-SNAPSHOT.jar
```

The application will start on `http://127.0.0.1:8080`.

## Example Usage Flow

1. **Generate a JWT Token**:
   Make a `POST` request to `/api/auth/token` with a JSON payload containing a username to get a JWT token.
   ```json
   {
     "username": "demo-user"
   }
   ```

2. **Call an AI Endpoint**:
   Use the generated token in the `Authorization: Bearer <token>` header to make a `POST` request to the protected `/api/gemini/generate` endpoint.
   ```json
   {
     "prompt": "Hello, Gemini!"
   }
   ```

3. **Open Endpoints**:
   Some endpoints like `/api/embedding/test` are open (`permitAll()`) and can be tested directly with a `GET` request.
