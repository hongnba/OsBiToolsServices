package com.osbitools.ws.shared.service.impl;

import com.osbitools.ws.shared.common.UserService;

public class AnonymousUserServiceImpl implements UserService {

  @Override
  public String getLoginUser() {
    return "anonymous";
  }

}
