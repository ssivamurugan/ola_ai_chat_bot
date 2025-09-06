package com.siva.ola_ai;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class OllamaController {

	private ChatClient chatClient;
	
	@Autowired
	private VectorStore vectorStore;
	
	@Autowired
	private EmbeddingModel embeddingModel;
	
	ChatMemory chatMemory = MessageWindowChatMemory
			.builder()
			.build();
	
	public OllamaController(ChatClient.Builder builder) {
		this.chatClient = builder
				.defaultAdvisors(MessageChatMemoryAdvisor
						.builder(chatMemory)
						.build())
				.build();
	}
	
	@GetMapping("/api/{message}")
	public ResponseEntity<String> getAnswer(@PathVariable String message) {
		
		ChatResponse chatResponse = chatClient
			.prompt(message)
			.call()
			.chatResponse();
		
		String response = chatResponse
			.getResult()
			.getOutput()
			.getText();
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/api/embedding")
	public float[] embedding(@RequestParam String text) {
		return embeddingModel.embed(text);
	}
	
	
	@PostMapping("/api/products")
	public List<Document> getPorducts(@RequestParam String text) {
		
		return vectorStore.similaritySearch(text);
	}
	
}
