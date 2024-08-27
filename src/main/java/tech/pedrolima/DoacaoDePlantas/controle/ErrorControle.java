package tech.pedrolima.DoacaoDePlantas.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorControle {
    @GetMapping("/aviso")
    public String acessarPrincipal(){
        return "pages/error";
    }
}