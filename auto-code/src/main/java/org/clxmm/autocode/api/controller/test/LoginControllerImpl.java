package org.clxmm.autocode.api.controller.test;

import org.clxmm.autocode.common.spring.ApplicationService;
import org.clxmm.autocode.common.spring.QueryMapping;

@ApplicationService(path = "/login1")
public class LoginControllerImpl implements LoginController {

    @QueryMapping("/login")
    @Override
    public String login(String name) {
        return null;
    }
}
