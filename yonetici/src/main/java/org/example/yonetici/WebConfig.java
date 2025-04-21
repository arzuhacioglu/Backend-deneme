package org.example.yonetici;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5501")  // Frontend'inizin çalıştığı portu yazın
                .allowedMethods("*")
                .allowedHeaders("*") // İzin verilen HTTP yöntemlerini belirtin
                .allowCredentials(true); // Eğer kullanıcı bilgileri gönderiyorsanız (örneğin cookie), bu özelliği etkinleştirin
    }
}
