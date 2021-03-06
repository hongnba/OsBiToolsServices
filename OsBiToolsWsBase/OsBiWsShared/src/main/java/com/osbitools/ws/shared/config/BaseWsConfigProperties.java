/*
 * Open Source Business Intelligence Tools - http://www.osbitools.com/
 * 
 * Copyright 2014-2018 IvaLab Inc. and by respective contributors (see below).
 * 
 * Released under the LGPL v3 or higher
 * See http://www.gnu.org/licenses/lgpl.txt
 *
 * Date: 2016-26-05
 * 
 * Contributors:
 * 
 */

package com.osbitools.ws.shared.config;

/**
 * Base class for all future Ws Configuratino Properties
 * 
 */
public class BaseWsConfigProperties {

  private Boolean debug;

  /**
   * @return the debug
   */
  public Boolean getDebug() {
    return debug;
  }

  /**
   * @param debug
   *          the debug to set
   */
  public void setDebug(Boolean debug) {
    this.debug = debug;
  }
}
