package br.com.db1.avaliadorSenha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rafael.farias on 28/08/17.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }


}
