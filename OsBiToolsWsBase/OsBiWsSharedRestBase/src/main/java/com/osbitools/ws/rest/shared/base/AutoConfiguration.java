/*
 * Open Source Business Intelligence Tools - http://www.osbitools.com/
 * 
 * Copyright 2014-2018 IvaLab Inc. and by respective contributors (see below).
 * 
 * Released under the LGPL v3 or higher
 * See http://www.gnu.org/licenses/lgpl.txt
 *
 * Contributors:
 * 
 * Date: 2017-04-19
 * 
 */

package com.osbitools.ws.rest.shared.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * AutoConfiguration class for Shared Rest Base library
 * 
 */

@Configuration
@ComponentScan("com.osbitools.ws.rest.shared.base")
public class AutoConfiguration {

  /**
   * Force JSON mapper convert camelCase into snake_case for all returning DTO
   * 
   * @return Jackson2ObjectMapperBuilder
   */
  @Bean
  @Primary
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    return new Jackson2ObjectMapperBuilder().failOnUnknownProperties(false)
        .propertyNamingStrategy(
            PropertyNamingStrategy.SnakeCaseStrategy.SNAKE_CASE);
  }
}
