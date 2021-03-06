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

package com.osbitools.ws.rest.prj.rest.shared.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.osbitools.ws.rest.prj.shared.config.TestPrjWsConfigProperties;
import com.osbitools.ws.shared.common.UserService;
import com.osbitools.ws.shared.service.impl.AnonymousUserServiceImpl;

/**
 * Project Manager based shared configuration class for Spring Boot Test Launcher.
 * 
 */

public class PrjAppSharedTestAppConfig  {

  @Primary
  @Bean("prj_ws_cfg_props")
  @ConfigurationProperties("test.ws.config")
  public TestPrjWsConfigProperties testPrjWsConfigProperties() {
    return new TestPrjWsConfigProperties();
  }

  @Bean("user_service")
  public UserService anonymousUserService() {
    return new AnonymousUserServiceImpl();
  }
}
