package tech.pedrolima.DoacaoDePlantas.controle;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;
import tech.pedrolima.DoacaoDePlantas.repositorios.CadastroRepositorio;

@Controller
public class LoginControle {

    @Autowired
    private CadastroRepositorio cadastroRepositorio;

    @GetMapping("/login")
    public ModelAndView cadastrar(){
        ModelAndView mv = new ModelAndView("logins/login");
        return mv;
    }

//    @PostMapping("/logar")
//    //Aqui estou criando uma variavel do tipo Cadastro (classe modelo) para armazenar as informações de login
//    public String logar(Model model, Cadastro cadParam){
//        //Estou acessando os dados do banco de dados
//        Cadastro cad = this.cadastroRepositorio.login(cadParam.getEmail(), cadParam.getSenha());
//        if(cad != null){
//            return "redirect:/listagemDePlantas";
//        }
//        model.addAttribute("erro", "Usuário ou senha inválidos");
//        return "logins/login";
//    }
}
