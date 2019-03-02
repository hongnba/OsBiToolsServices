/*
 * Copyright 2014-2018 IvaLab Inc. and by respective contributors (see below).
 * 
 * Released under the LGPL v3 or higher
 * See http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Date: 2014-11-07
 * 
 * Contributors:
 *
 */

package com.osbitools.ws.rest.core.shared.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.osbitools.ws.shared.common.TestConstants;
import com.osbitools.ws.rest.core.shared.rest.CoreAppSharedMultiTestUnit;
import com.osbitools.ws.rest.shared.base.utils.GenericRestMultiTest;
import com.osbitools.ws.rest.shared.base.utils.GenericRestTestUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, value = {
    "spring.config.name=test" })
public class CoreAppSharedMultiTest extends GenericRestMultiTest {
  
  @Override
  public Class<? extends GenericRestTestUnit> getTestClass() {
    return CoreAppSharedMultiTestUnit.class;
  }

  @Override
  public long getWaitTime() {
    return TestConstants.WAIT_TIME * 30;
  }
}
