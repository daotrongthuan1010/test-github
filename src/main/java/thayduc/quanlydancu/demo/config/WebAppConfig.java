package thayduc.quanlydancu.demo.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Component

public class WebAppConfig  {

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public Cloudinary cloudinaryConfig() {

        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "springtestimageapi");
        config.put("api_key", "646949719412351");
        config.put("api_secret", "fqQ8dEVg-4AvfAE_Idq7kcsWtks");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}
