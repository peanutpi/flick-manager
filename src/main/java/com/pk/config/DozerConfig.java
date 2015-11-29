package com.pk.config;

import java.io.IOException;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

@Configuration
public class DozerConfig {

    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapperFactoryBean dozerBean() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource[] mappingFiles;
        try {
            mappingFiles = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(
                    "classpath*:/dozer/*-mapping.xml");
            DozerBeanMapperFactoryBean mapperFactory = new DozerBeanMapperFactoryBean();
            mapperFactory.setMappingFiles(mappingFiles);
            return mapperFactory;
        } catch (IOException e) {
            return null;
        }
    }
}
