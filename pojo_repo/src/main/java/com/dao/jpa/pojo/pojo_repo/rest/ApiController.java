/*
 * package com.dao.jpa.pojo.pojo_repo.rest;
 * 
 * import com.dao.jpa.pojo.pojo_repo.entity.Customer; import
 * com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * import jakarta.servlet.http.HttpServletRequest;
 * 
 * import org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * 
 * import java.io.BufferedReader; import java.io.IOException;
 * 
 * @RestController
 * 
 * @RequestMapping("/") public class ApiController {
 * 
 * @PostMapping("/**") public String handleRequest(HttpServletRequest request)
 * throws IOException { // İstek metodu String method = request.getMethod();
 * 
 * // İstek adresi String uri = request.getRequestURI();
 * 
 * // Payload okuma StringBuilder payloadContent = new StringBuilder(); try
 * (BufferedReader reader = request.getReader()) { char[] charBuffer = new
 * char[128]; int bytesRead; while ((bytesRead = reader.read(charBuffer)) != -1)
 * { payloadContent.append(charBuffer, 0, bytesRead); } }
 * 
 * // İçeriğin boş olup olmadığını kontrol et if (payloadContent.length() == 0)
 * { return "Payload is empty"; }
 * 
 * // ObjectMapper kullanmadan önce içeriğin boş olmadığına emin olun String
 * response; try { ObjectMapper objectMapper = new ObjectMapper(); Customer
 * yourObject = objectMapper.readValue(payloadContent.toString(),
 * Customer.class); response = "Method: " + method + "\nURI: " + uri +
 * "\nPayload: " + payloadContent.toString(); // İsteğe özel işlemleri burada
 * gerçekleştirebilirsiniz } catch (IOException e) { // JSON parse hatası
 * response = "Error parsing JSON: " + e.getMessage(); }
 * 
 * return response; } }
 */