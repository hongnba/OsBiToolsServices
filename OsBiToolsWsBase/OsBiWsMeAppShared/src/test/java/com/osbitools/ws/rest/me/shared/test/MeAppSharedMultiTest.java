/*
 * Open Source Business Intelligence Tools - http://www.osbitools.com/
 * 
 * Copyright 2014-2018 IvaLab Inc. and by respective contributors (see below).
 * 
 * Released under the LGPL v3 or higher
 * See http://www.gnu.org/licenses/lgpl.txt
 *
 * Date: 2014-10-11
 * 
 * Contributors:
 * 
 */

package com.osbitools.ws.rest.me.shared.test;

import org.junit.BeforeClass;

import com.osbitools.ws.rest.me.shared.common.MeAppSharedTestConstants;
import com.osbitools.ws.rest.prj.rest.shared.rest.AbstractPrjMgrSharedMultiTest;
import com.osbitools.ws.rest.prj.rest.shared.rest.PrjMgrSharedMultiTestUnit;

/**
 * Multi Thread Test for Shared Module
 * 
 */

public class MeAppSharedMultiTest extends AbstractPrjMgrSharedMultiTest {

  @BeforeClass
  public static void setConfigList() {
    PrjMgrSharedMultiTestUnit.GONFIG_TEST = MeAppSharedTestConstants.CONFIG_LIST;
  }
}
