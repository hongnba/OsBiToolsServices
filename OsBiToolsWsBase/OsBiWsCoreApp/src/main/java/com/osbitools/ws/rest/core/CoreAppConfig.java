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

package com.osbitools.ws.rest.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.osbitools.ws.rest.shared.base.common.BaseRestAjpConfig;
import com.osbitools.ws.shared.common.UserService;
import com.osbitools.ws.shared.service.impl.AnonymousUserServiceImpl;

/**
 * Application Configuration
 * 
 */

@Configuration
public class CoreAppConfig extends BaseRestAjpConfig {
  
  @Bean("user_service")
  public UserService anonymousUserService() {
    return new AnonymousUserServiceImpl();
  }
}
