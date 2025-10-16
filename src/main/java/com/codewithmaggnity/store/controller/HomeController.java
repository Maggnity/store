package com.codewithmaggnity.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        System.out.println("chamando terra");
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/gerenciar-usuarios")
    public String criarUsuario() {
        return "manage-users";
    }
}
