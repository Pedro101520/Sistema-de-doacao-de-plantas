package tech.pedrolima.DoacaoDePlantas.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;
import tech.pedrolima.DoacaoDePlantas.repositorios.CadastroRepositorio;
import tech.pedrolima.DoacaoDePlantas.utils.EnvioEmail;

@Controller
public class RecuperarSenha {

    @Autowired
    private CadastroRepositorio cadastroRepositorio;

    @Autowired
    private EnvioEmail envioEmail;

    @GetMapping("/recuperarSenha")
    public ModelAndView recuperar(){
        ModelAndView mv = new ModelAndView("pages/recuperarSenha");
        return mv;
    }

    @GetMapping("/recuperarSenha/informarEmail")
    //Aqui pega o valor do campo "username" no formulário HTML e o passa para essa variavel
    public ModelAndView confirmar(@RequestParam("username") String email, Cadastro usuario){
        ModelAndView mv;
        String emailExistente = cadastroRepositorio.QueryEmail(email);
        if(emailExistente != null){
            envioEmail.imprimir(emailExistente);
            mv = new ModelAndView("pages/senhaRecuperar");
            mv.addObject("usuario", usuario);
        }else{
            mv = new ModelAndView("pages/recuperarSenha");
            mv.addObject("erro", "Email não encontrado.");
        }
        return mv;
    }

    @GetMapping("/recuperarSenha/informarEmail/atualizarSenha")
    public ModelAndView atualizar(Cadastro usuario){
        ModelAndView mv = new ModelAndView("pages/senhaAtualizar");
        mv.addObject("usuario", usuario);
        return mv;
    }

}
