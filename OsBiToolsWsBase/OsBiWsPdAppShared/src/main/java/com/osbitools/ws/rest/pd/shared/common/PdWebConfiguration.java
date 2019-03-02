/*
 * Open Source Business Intelligence Tools - http://www.osbitools.com/
 * 
 * Copyright 2014-2018 IvaLab Inc. and by respective contributors (see below).
 * 
 * Released under the LGPL v3 or higher
 * See http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Date: 2018-03-20
 * 
 * Contributors:
 * 
 */

package com.osbitools.ws.rest.pd.shared.common;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.osbitools.ws.wp.shared.mapper.*;
import com.osbitools.ws.rest.shared.base.common.WebConfiguration;
import com.osbitools.ws.wp.shared.binding.WebWidgets;

/**
 * Web Configuration for Page Designer mapper
 * 
 */

@EnableWebMvc
@Configuration
public class PdWebConfiguration extends WebConfiguration {

  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    super.extendMessageConverters(converters);

    SimpleModule module = new SimpleModule();
    module.addSerializer(WebWidgets.class, new WebWidgetsSerializer());
    module.addDeserializer(WebWidgets.class, new WebWidgetsDeSerializer());
    getJsonToHttpMapper().registerModule(module);
  }
}