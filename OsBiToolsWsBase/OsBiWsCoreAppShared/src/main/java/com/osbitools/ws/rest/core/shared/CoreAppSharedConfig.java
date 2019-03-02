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

package com.osbitools.ws.rest.core.shared;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.context.annotation.SessionScope;

import com.osbitools.ws.core.shared.config.CoreWsConfigProperties;
import com.osbitools.ws.rest.shared.base.common.AbstractBaseAppConfig;
import com.osbitools.ws.shared.service.RequestLogger;
import com.osbitools.ws.shared.service.impl.RequestLoggerImpl;

/**
 * Application Configuration
 * 
 */

@Configuration
public class CoreAppSharedConfig extends AbstractBaseAppConfig {

  @Bean("core_ws_cfg_props")
  @ConfigurationProperties("ws.config")
  public CoreWsConfigProperties coreWsConfigProperties() {
    return new CoreWsConfigProperties();
  }

  @Bean("rlog")
  @SessionScope
  @DependsOn({"log", "user_service"})
  public RequestLogger requestLogger() {
    return new RequestLoggerImpl();
  }
  
  @Override
  protected String getAppName() {
    return "OsBiTools Core App";
  }

}
