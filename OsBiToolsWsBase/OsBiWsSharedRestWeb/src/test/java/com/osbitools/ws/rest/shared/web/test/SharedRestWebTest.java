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

package com.osbitools.ws.rest.shared.web.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.osbitools.ws.rest.shared.web.rest.SharedRestWebTestUnit;

/**
 * Top launcher for single threaded test
 * 
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
    value = { "spring.config.name=test" })
public class SharedRestWebTest extends SharedRestWebTestUnit {

  static final String[][] GONFIG_TEST = new String[][] {
    new String[] { "param1", "{\"param1\":\"one\"}" },
    new String[] { "param1,param2", "{\"param1\":\"one\",\"param2\":\"two\"}" },
    new String[] { "param1,param2,paramxx", "{\"param1\":\"one\",\"param2\":\"two\"}" }
  };

  @Autowired
  private TestRestTemplate _rest;
  
  @Autowired
  private Logger _log;
  
  @LocalServerPort
  private int _port;
  
  @Override
  protected TestRestTemplate getRestTemplate() {
    return _rest;
  }

  @Override
  protected int getPort() {
    return _port;
  }

  @Override
  protected Logger getLogger() {
    return _log;
  }
  
  @Override
  protected String[][] getConfigTest() {
    return GONFIG_TEST;
  }
}
