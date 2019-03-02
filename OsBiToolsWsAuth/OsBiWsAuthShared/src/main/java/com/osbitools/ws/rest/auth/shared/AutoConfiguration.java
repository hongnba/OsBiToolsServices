/*
 * Open Source Business Intelligence Tools - http://www.osgitools.com/
 * 
 * Copyright 2014-2018 IvaLab Inc. and by respective contributors (see below).
 * 
 * Released under the LGPL v3 or higher
 * See http://www.gnu.org/licenses/lgpl.txt
 *
 * Date: 2018-01-01
 * 
 * Contributors:
 * 
 */

package com.osbitools.ws.rest.auth.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.SessionRepositoryFilter;

import com.osbitools.ws.rest.auth.common.Constants;
import com.osbitools.ws.rest.auth.shared.session.SessionCookieFilter;

/**
 * AutoConfiguration file for shared library
 * 
 */
@Configuration
@ComponentScan("com.osbitools.ws.rest.auth.shared")
public class AutoConfiguration {

  @Autowired
  CookieSerializer serializer;

  /**
   * Switch into cookie based strategy
   * 
   * @param sessionRepository
   * @return
   */
  @Bean
  public <S extends ExpiringSession> SessionRepositoryFilter<? extends ExpiringSession> springSessionRepositoryFilter(
      SessionRepository<S> sessionRepository) {
    return new SessionCookieFilter<S>(sessionRepository,
        Constants.COOKIE_SESSION_NAME, serializer);
  }
}
