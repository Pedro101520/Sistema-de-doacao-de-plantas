package tech.pedrolima.DoacaoDePlantas.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.pedrolima.DoacaoDePlantas.System.CadastroService;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;
import tech.pedrolima.DoacaoDePlantas.repositorios.CadastroRepositorio;

import java.util.Optional;

@Controller
public class CadastroControle {

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private CadastroRepositorio cadastroRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/cadastroUsuario")
    public ModelAndView cadastrar(Cadastro usuario){
        ModelAndView mv = new ModelAndView("cadastros/cadastro");
        mv.addObject("usuario", usuario);
        mv.addObject("listaNomes", cadastroRepositorio.findAll());
        return mv;
    }

    @PostMapping("/cadastrarUsuario")
    public ModelAndView salvar(Cadastro cadastro, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(cadastro);
        }
        cadastro.setSenha(passwordEncoder.encode(cadastro.getSenha()));
        cadastroRepositorio.saveAndFlush(cadastro);
        return cadastrar(new Cadastro());
    }

    @GetMapping("/editarUsuario/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        Long userId = cadastroService.getIdByEmail();
        if (!id.equals(userId)) {
            return new ModelAndView("redirect:/inicio");
        }
        Optional<Cadastro> cadastro = cadastroRepositorio.findById(id);
        return cadastrar(cadastro.get());
    }
}