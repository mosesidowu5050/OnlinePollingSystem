package org.mosesidowu.onlinepollingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.onlinepollingsystem.services.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;



}
