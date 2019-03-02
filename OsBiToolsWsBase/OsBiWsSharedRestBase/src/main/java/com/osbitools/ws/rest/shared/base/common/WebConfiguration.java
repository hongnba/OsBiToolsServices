/*
 * Open Source Business Intelligence Tools - http://www.osbitools.com/
 * 
 * Copyright 2014-2018 IvaLab Inc. and by respective contributors (see below).
 * 
 * Released under the LGPL v3 or higher
 * See http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Date: 2016-06-09
 * 
 * Contributors:
 * 
 */

package com.osbitools.ws.rest.shared.base.common;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * Web Configuration
 * 
 */

@EnableWebMvc
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

  private ObjectMapper _mapper;
  
  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    super.extendMessageConverters(converters);

    // Find MappingJackson2HttpMessageConverter
    for (HttpMessageConverter<?> conv : converters)
      if (conv.getClass().equals(MappingJackson2HttpMessageConverter.class)) {
        _mapper = ((MappingJackson2HttpMessageConverter) conv).
            getObjectMapper();
        
        // Set naming strategy
        _mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        
        // Ignore null fields when converting to JSON
        _mapper.setSerializationInclusion(Include.NON_NULL);
        
        break;
      }
  }
  
  public ObjectMapper getJsonToHttpMapper() {
    return _mapper;
  }
}