package com.globomart.globomartapi.conf;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globomart.globomartapi.client.ProductCatalogClient;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.TimeZone;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import static com.fasterxml.jackson.databind.DeserializationFeature.READ_ENUMS_USING_TO_STRING;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_ENUMS_USING_TO_STRING;

@Configuration
@CommonsLog
public class WebMVCConfigurer extends WebMvcConfigurerAdapter {

    @Value("${product.catalog.base.url}")
    String productCatalogBaseUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/**");
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = (MappingJackson2HttpMessageConverter) converters.stream()
                .filter(it -> MappingJackson2HttpMessageConverter.class.isAssignableFrom(it.getClass()))
                .findAny()
                .orElse(new MappingJackson2HttpMessageConverter());

        ObjectMapper objectMapper = mappingJackson2HttpMessageConverter.getObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.enable(READ_ENUMS_USING_TO_STRING);
        objectMapper.enable(WRITE_ENUMS_USING_TO_STRING);
        converters.add(mappingJackson2HttpMessageConverter);
        super.configureMessageConverters(converters);
    }

    @Bean
    FeignClientFactory FeignClientFactory() {
        return new FeignClientFactory();
    }

    @Bean
    @Autowired
    ProductCatalogClient productCatalogClient(FeignClientFactory feignClientFactory) {
        return feignClientFactory.getInstance(ProductCatalogClient.class, productCatalogBaseUrl);
    }
}
