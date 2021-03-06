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

package com.osbitools.ws.rest.prj.shared;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.osbitools.ws.rest.prj.shared.common.AbstractPrjSharedBaseTestAppConfig;

/**
 * Configuration class for Spring Boot Test Launcher
 * 
 */

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableConfigurationProperties
public class PrjSharedBaseTestAppConfig
    extends AbstractPrjSharedBaseTestAppConfig {

  @Override
  protected String getTestName() {
    return "PrjShared";
  }

}
