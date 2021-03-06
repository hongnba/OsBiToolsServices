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

package com.osbitools.ws.shared.common;

import org.springframework.context.annotation.Bean;

import com.osbitools.ws.shared.common.UserService;
import com.osbitools.ws.shared.service.RequestLogger;
import com.osbitools.ws.shared.service.impl.AnonymousUserServiceImpl;
import com.osbitools.ws.shared.service.impl.RequestLoggerImpl;

/**
 * Test Application Configuration
 * 
 */

public abstract class AbstractUserServiceTestAppConfig
    extends AbstractHomeDirTestAppConfig {

  @Bean("user_service")
  public UserService anonymousService() {
    return new AnonymousUserServiceImpl();
  }
  
  @Bean("rlog")
  RequestLogger requestLogger() {
    return new RequestLoggerImpl();
  }
}
