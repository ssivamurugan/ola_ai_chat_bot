# Ola AI Spring Boot Application

## Overview
Ola AI is a Spring Boot-based AI-powered web application that integrates with Spring AI's Ollama model for chat and embedding capabilities. The project demonstrates how to build a conversational AI service backed by text embedding and vector search, leveraging Spring AI libraries.

## Features
- Spring Boot 3.5.5 application using Java 21.
- Chat endpoint for interacting with Ollama AI chat client.
- Embedding endpoint to convert text into vector embeddings.
- Product search using vector similarity from loaded documents.
- Initialization of documents from a product details text file, split into vector store chunks.
- Configurable AI models (Mistral 7B) for chat and embedding via application properties.

## Project Structure
src/main/java/com/siva/ola_ai/
├── OlaAiApplication.java # Main Spring Boot application class
├── OllamaController.java # REST controller exposing AI chat, embedding, and product APIs
├── DataInitializer.java # Component that loads and initializes documents into vector store on startup
├── AppConfig.java # Spring configuration defining VectorStore bean using embedding model
src/main/resources/
├── product_details.txt # Text file with product details used as input data
├── application.properties # Application configuration file with AI model settings

## Technology Stack
- Java 21
- Spring Boot 3.5.5
- Spring AI (1.0.1)
- Spring Web (Spring MVC)
- Ollama AI chat and embedding models
- Vector store for semantic similarity search (Simple Vector in-memory vector store)

## How to Run
1. Ensure Java 21 and Maven are installed.
2. Build the project using Maven:
3. Run the Spring Boot application:
4. Application will start on default port 8080.

## API Endpoints

- `GET /api/{message}`  
Sends a chat message to the Ollama AI chat client and returns the response.

- `POST /api/embedding`  
Accepts a `text` query parameter and returns a float array vector embedding.

- `POST /api/products`  
Accepts a `text` query parameter and returns a list of documents similar to the provided text via vector search.

## Configuration
Application properties are set in `src/main/resources/application.properties`:
spring.application.name=ola_ai
spring.ai.ollama.chat.options.model=mistral:7b
spring.ai.ollama.embedding.model=mistral:7b
These specify the AI model versions used for chat and embedding.

## Dependencies
- Spring Boot Starter Web
- Spring AI Starter Model Ollama
- Spring AI Advisors Vector Store

Dependencies are managed via Maven in `pom.xml`.

## Contact
For questions or contributions, please contact the author or open issues in the repository.


