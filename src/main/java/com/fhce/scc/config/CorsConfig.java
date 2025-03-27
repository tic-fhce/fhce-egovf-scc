package com.fhce.scc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // Permitir solicitudes desde estos orígenes
        config.setAllowedOrigins(List.of("http://localhost:4200", "http://192.168.1.1"));
        
        // Métodos permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Permitir credenciales (Cookies, Authorization headers, etc.)
        config.setAllowCredentials(true);
        
        // Permitir todos los headers
        config.setAllowedHeaders(List.of("*"));
        
        // Exponer headers personalizados si es necesario
        config.setExposedHeaders(List.of("Authorization", "Content-Type"));
        
        // Definir el tiempo máximo de caché de la configuración en el navegador
        config.setMaxAge(3600L);

        // Aplicar configuración a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}
