package com.dao.jpa.pojo.pojo_repo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.stereotype.Component;


public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filtre başlatıldığında yapılacak işlemler
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Gelen isteğin URL'sini ve metot tipini al
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String requestUrl = httpRequest.getServletContext().getContextPath() + httpRequest.getServletPath();
            String requestMethod = httpRequest.getMethod();

            // Alınan bilgileri yazdır
            System.out.println("URL: " + requestUrl);
            System.out.println("Method: " + requestMethod);
            
            
            
            
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            // Yanıtın HTTP durum kodunu kontrol et
            int statusCode = httpResponse.getStatus();
            
            // Yönlendirme durum kodu (302) ise işlem yap
            if (statusCode == HttpServletResponse.SC_FOUND) {
                // Yönlendirme URL'sini al
                String redirectUrl = httpResponse.getHeader("Location");
                System.out.println("Redirect URL: " + redirectUrl);
            }
            
        }

        // Filtre zincirini devam ettir
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Filtre yok edildiğinde yapılacak işlemler
    }
}
