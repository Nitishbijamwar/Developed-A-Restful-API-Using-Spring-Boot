package com.training.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainHandler {

    private static final String ANGULAR_APP = "index";

    @GetMapping(value = "/")
    public String getDefault() {
        return ANGULAR_APP;
    }

    @GetMapping(value = "/sign-in")
    public String signIng() {
        return ANGULAR_APP;
    }

    @GetMapping(value = "/cards")
    public String signOut() {
        return ANGULAR_APP;
    }

    @GetMapping(value = "/home")
    public String home() {
        return ANGULAR_APP;
    }

    @GetMapping(value = "/example")
    public String example() {
        return ANGULAR_APP;
    }

}
