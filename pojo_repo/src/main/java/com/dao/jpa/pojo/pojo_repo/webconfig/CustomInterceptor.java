package com.dao.jpa.pojo.pojo_repo.webconfig;
																					
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Scanner;

@Component
public class CustomInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);

    public CustomInterceptor() {
        super();
        logger.info("INTERCEPTOR BEGINNING...");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        logger.info("-----------------------------------------------------------------------------------");
       
        // Temel kimlik doğrulama bilgilerini al
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {

            String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
            // Kullanıcı adı ve şifreyi ayrıştır
            String[] values = credentials.split(":", 2);
            String username = values[0];
            String password = values[1];
            // Kullanıcı adı ve şifreyi logla veya başka bir işlem yap
            logger.info("Username: {}, Password: {}", username, password);
        }
                          
         // Request body'nin içeriğini okumadan InputStream'i almak KISMEN İŞE YARAR 
         // InputStream inputStream = request.getInputStream();
        
 //       processRequest(request); -- BUNU KULLANINCA GÖNDERİLEN JSON ENGELLENİYOR
        
        // Devam et
        return true;
               
    }

    
    public void processRequest(HttpServletRequest request) {
        try {
            // HttpServletRequest'i ContentCachingRequestWrapper ile sarmala
            ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(request);

            // İlk defa okuma işlemi
            String firstRequestBody = convertInputStreamToString(wrapper.getInputStream());
            logger.info("First Request Body Content: {}", firstRequestBody);

            // ContentCachingRequestWrapper'ı kullanarak isteği tekrar okuma işlemi yapmak gerekmez.
            // Bellekte zaten saklanmış durumda.
        } catch (IOException e) {
            logger.error("Error reading request body: {}", e.getMessage());
        }
    }

    
    private String convertInputStreamToString(InputStream inputStream) throws IOException {
    	
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        return content.toString();
    }
    

    
    
    
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        // İstek URI'sini al
        String uri = request.getRequestURI();

        // HTTP metodu al
        String method = request.getMethod();

        // CSRF token'ı al
        String csrfToken = request.getHeader("_csrf");

        // Form parametrelerini al
        Map<String, String[]> formParameters = request.getParameterMap();

        // Form parametrelerini logla
        logger.info("Intercepted Request - URI: http://localhost:8080{} | Method: {} | _csrf Token: {}", uri, method,
                csrfToken);

  
        // Form parametrelerini logla
        logger.info("form-data Param:");

        for (Map.Entry<String, String[]> entry : formParameters.entrySet()) {
            String paramName = entry.getKey();
            String[] paramValues = entry.getValue();
            StringBuilder paramValueStr = new StringBuilder();
            for (String paramValue : paramValues) {
                paramValueStr.append(paramValue).append(", ");
            }
            // Logla
            logger.info("{} : {}", paramName, paramValueStr.toString());
        }
         
    }

 
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.info("-----------------------------------------------------------------------------------");
    }
    
    
}
 