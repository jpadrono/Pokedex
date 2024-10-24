<<<<<<< HEAD
//  package com.pokedex.pokedex_api.config;

//  import org.springframework.context.annotation.Configuration;
//  import org.springframework.web.servlet.config.annotation.CorsRegistry;
//  import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
=======
package com.pokedex.pokedex_api.config;

 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.CorsRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
>>>>>>> f3b7fc19abf246df12ed0de345b1caa8bc9a2f81

//  @Configuration
//  public class WebConfig implements WebMvcConfigurer {

<<<<<<< HEAD
//      @Override
//      public void addCorsMappings(CorsRegistry registry) {
//          registry.addMapping("/**")
//                  .allowedOrigins("*") // Substitua pelo domínio correto
//                  .allowedMethods("GET", "POST", "PUT", "DELETE")
//                  .allowedHeaders("*")
//                  .allowCredentials(true);
//      }
// }
=======
     @Override
     public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**")
                 .allowedOrigins("http://127.0.0.1:5500") // Substitua pelo domínio correto
                 .allowedMethods("GET", "POST", "PUT", "DELETE")
                 .allowedHeaders("*")
             .allowCredentials(true);
     }
}
>>>>>>> f3b7fc19abf246df12ed0de345b1caa8bc9a2f81
