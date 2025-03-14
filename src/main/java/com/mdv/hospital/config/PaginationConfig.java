package com.mdv.hospital.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PaginationConfig implements WebMvcConfigurer {

    @Bean
    public PageableHandlerMethodArgumentResolver pageableResolver() {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver() {
            @Override
            public Pageable resolveArgument(
                    MethodParameter methodParameter,
                    ModelAndViewContainer mavContainer,
                    NativeWebRequest webRequest,
                    WebDataBinderFactory binderFactory) {
                Pageable pageable = super.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);
                return PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
            }
        };
        resolver.setFallbackPageable(PageRequest.of(1, 10));
        resolver.setMaxPageSize(100);
        resolver.setPageParameterName("page");
        resolver.setSizeParameterName("size");
        return resolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(pageableResolver());
    }
}
