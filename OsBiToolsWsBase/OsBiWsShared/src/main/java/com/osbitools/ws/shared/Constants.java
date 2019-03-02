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
 * Date: 2016-26-05
 * 
 */

package com.osbitools.ws.shared;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Default and Project specific constants
 * 
 */

public class Constants {

  // Pattern for Project/Map names
  public static final Pattern ID_PATTERN =
      Pattern.compile("^[A-Za-z0-9_]*[A-Za-z0-9]+[A-Za-z0-9_]*$");

  // Patter for query parameter names
  public static String PARAM_VAL_SUFIX = "osbi_";
  public static Pattern PARAM_VAL = Pattern.compile(PARAM_VAL_SUFIX + ".*");
  
  // Max supported project level
  public static final int MAX_PROJ_LVL = 1;

  //Extension of configuration files.
  public static final String CONFIG_FIILE_EXT = "properties";

  // Default configuration file name as confi.properties
  public static final String WS_CONFIG_FILE_NAME = "config." + CONFIG_FIILE_EXT;

  // Array of known locales
  public static Map<String, Locale> LOCALES = new HashMap<String, Locale>();

  static {
    LOCALES.put("en", Locale.US);
    LOCALES.put("fr", Locale.CANADA_FRENCH);
    LOCALES.put("ru", new Locale("ru", "RU"));
  }

}
