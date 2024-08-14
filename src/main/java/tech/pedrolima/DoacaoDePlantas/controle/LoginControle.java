package tech.pedrolima.DoacaoDePlantas.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginControle {
    @GetMapping("/login")
    public ModelAndView cadastrar(){
        ModelAndView mv = new ModelAndView("logins/login");
//        mv.addObject("usuario", usuario);
        return mv;
    }
}
