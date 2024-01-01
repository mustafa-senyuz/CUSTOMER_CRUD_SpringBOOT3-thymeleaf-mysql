
  package com.dao.jpa.pojo.pojo_repo.webconfig;
  
  import org.springframework.context.annotation.Configuration;
  import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
  import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dao.jpa.pojo.pojo_repo.webconfig.CustomInterceptor;

  @Configuration
  public class WebMvcConfig implements WebMvcConfigurer {

      private final CustomInterceptor customInterceptor;

      public WebMvcConfig(CustomInterceptor customInterceptor) {
          this.customInterceptor = customInterceptor;
      }

      @Override
      public void addInterceptors(InterceptorRegistry registry) {
          // CustomInterceptor'ı ekleyin ve hangi URL pattern'lerine uygulanacağını belirtin
          registry.addInterceptor(customInterceptor)
                  .addPathPatterns("/**"); // Tüm URL'ler için uygula, isteğinizi ihtiyaca göre ayarlayabilirsiniz
      }
  }
