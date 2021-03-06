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

package com.osbitools.ws.rest.shared.base.utils;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.osbitools.ws.rest.shared.base.common.TestRestConstants;

/**
 * Multi Thread Test Runner
 * 
 */
public class GenericRestMultiTestRunner implements Runnable {

  private ITestInfoProvider _tp;

  public GenericRestMultiTestRunner(ITestInfoProvider testProvider) {
    _tp = testProvider;
  }

  @Override
  public void run() {
    try {
      TestRestConstants.start.await();
    } catch (InterruptedException e) {
      fail(e.getMessage());
    }

    try {
      JUnitCore junit = new JUnitCore();
      Result res = junit.run(_tp.getTestClass());
      int fcnt = res.getFailureCount();

      if (fcnt != 0) {
        TestRestConstants.errCount++;
        System.err.println(fcnt + " errors detected.");
        List<Failure> list = res.getFailures();
        for (int i = 0; i < list.size(); i++)
          System.err.println(
              "\t ERROR #" + i + " - " + list.get(i).getException().getClass().getName() +
                  ": " + list.get(i).getMessage() + ";");

        System.out.println();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      TestRestConstants.errCount++;
    }

    TestRestConstants.done.countDown();
  }

}
