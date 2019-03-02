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
 * Date: 2018-01-01
 * 
 */

package com.osbitools.ws.rest.prj.rest.shared.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import com.osbitools.ws.rest.prj.shared.config.AbstractPrjWsConfig;

import com.osbitools.ws.rest.prj.shared.config.TestPrjMgrWsConfig;
import com.osbitools.ws.rest.prj.shared.config.TestPrjWsConfigProperties;
import com.osbitools.ws.rest.prj.shared.service.EntityService;
import com.osbitools.ws.rest.prj.shared.service.impl.TestTextEntityServiceImpl;
import com.osbitools.ws.rest.web.common.AbstractSharedWebTestAppConfig;
import com.osbitools.ws.rest.prj.shared.config.PrjWebConfig;
import com.osbitools.ws.rest.prj.shared.config.PrjWebConfigProperties;
import com.osbitools.ws.shared.config.BaseWsConfigProperties;
import com.osbitools.ws.shared.web.config.BaseWebConfig;
import com.osbitools.ws.shared.web.config.BaseWebConfigProperties;

/**
 * Configuration class for Spring Boot Test Launcher
 * 
 */

public abstract class AbstractPrjSharedRestTestAppConfig_
    extends AbstractSharedWebTestAppConfig<TestPrjMgrWsConfig> {

  @Override
  public BaseWebConfig getWebConfig() {
    return new PrjWebConfig();
  }

  @Override
  public BaseWebConfigProperties getWebConfigProperties() {
    return new PrjWebConfigProperties();
  }

  @Override
  public TestPrjMgrWsConfig getBaseWsConfig() {
    return new TestPrjMgrWsConfig();
  }

  @Override
  public BaseWsConfigProperties getWsConfigProperties() throws Exception {
    return new TestPrjWsConfigProperties();
  }

  //**********Beans Definition ******************

  @Bean("prj_ws_cfg_props")
  @ConfigurationProperties("default.ws.config")
  public BaseWsConfigProperties wsConfigProps() throws Exception {
    return getWsConfigProps();
  }

  @Bean("prj_ws_cfg")
  @DependsOn({ "prj_ws_cfg_props", "log" })
  public AbstractPrjWsConfig wsCfgEx() throws Exception {
    return getWsCfg();
  }

  @Bean
  @DependsOn("prj_ws_cfg")
  public EntityService<?> entityService() {
    return new TestTextEntityServiceImpl();
  }
}
