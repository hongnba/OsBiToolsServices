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

package com.osbitools.ws.core.shared.proc;

import org.springframework.jdbc.core.JdbcTemplate;

import com.osbitools.ws.base.WsSrvException;

public interface IJdbcTemplateDelegate {

  /**
   * Retrieve DataSource by data source name
   * 
   * @param dsName
   *          Name of data source
   * @return DataSource
   * @throws WsSrvException
   */
  public JdbcTemplate getJdbcTemplate();
}
