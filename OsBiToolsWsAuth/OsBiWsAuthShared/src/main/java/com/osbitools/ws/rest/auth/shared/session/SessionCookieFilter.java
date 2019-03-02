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

package com.osbitools.ws.rest.auth.shared.session;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.SessionRepositoryFilter;

/**
 * Filter that checks if cookie with security session name set. Returns 403
 * (Access Denied) if not. This will prevent from creating security session with
 * Anonymous role.
 * 
 * @param <S> Session type
 */
public class SessionCookieFilter<S extends ExpiringSession> extends SessionRepositoryFilter<S> {

	private final String cookieSessionName;

	private AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();

	public SessionCookieFilter(SessionRepository<S> sessionRepository, String cookieSessionName,
			CookieSerializer serializer) {
		super(sessionRepository);

		this.cookieSessionName = cookieSessionName;
		CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
		strategy.setCookieSerializer(serializer);
		setHttpSessionStrategy(strategy);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Check for session cookie set in request header
		boolean fset = false;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookieSessionName.equals(cookie.getName())) {
					fset = true;
					break;
				}
			}
		}

		if (!fset)
			accessDeniedHandler.handle(request, response,
					new AccessDeniedException("Security Session cookie not found."));
		else
			super.doFilterInternal(request, response, filterChain);
	}
}
