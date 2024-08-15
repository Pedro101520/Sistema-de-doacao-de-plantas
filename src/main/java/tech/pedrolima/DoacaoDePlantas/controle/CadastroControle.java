package tech.pedrolima.DoacaoDePlantas.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;

@Controller
public class CadastroControle {
    @GetMapping("/cadastroUsuario")
    public ModelAndView cadastrar(Cadastro usuario){
        ModelAndView mv = new ModelAndView("cadastros/cadastro");
        mv.addObject("usuario", usuario);
        return mv;
    }
}
