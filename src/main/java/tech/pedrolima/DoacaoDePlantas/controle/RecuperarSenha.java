package tech.pedrolima.DoacaoDePlantas.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;
import tech.pedrolima.DoacaoDePlantas.repositorios.CadastroRepositorio;

@Controller
public class RecuperarSenha {

    @GetMapping("/recuperarSenha")
    public ModelAndView recuperar(){
        ModelAndView mv = new ModelAndView("pages/recuperarSenha");
        return mv;
    }

    @GetMapping("/recuperarSenha/informarEmail")
    public ModelAndView confirmar(Cadastro usuario){
        ModelAndView mv = new ModelAndView("pages/senhaRecuperar");
        mv.addObject("usuario", usuario);
        return mv;
    }

    @GetMapping("/recuperarSenha/informarEmail/atualizarSenha")
    public ModelAndView atualizar(Cadastro usuario){
        ModelAndView mv = new ModelAndView("pages/senhaAtualizar");
        mv.addObject("usuario", usuario);
        return mv;
    }

}
