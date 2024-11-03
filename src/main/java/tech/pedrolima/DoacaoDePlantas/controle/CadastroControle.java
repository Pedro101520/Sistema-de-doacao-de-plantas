package tech.pedrolima.DoacaoDePlantas.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public ModelAndView salvar(Cadastro cadastro, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(cadastro);
        }

        Long userId = cadastroService.getIdByEmail();

        if (userId != null) {
            Optional<Cadastro> cadastroExistente = cadastroRepositorio.findById(userId);

            if (cadastroExistente.isPresent()) {
                Cadastro cadastroAtualizado = cadastroExistente.get();
                cadastroAtualizado.setNome(cadastro.getNome());
                cadastroAtualizado.setEmail(cadastro.getEmail());
                cadastroAtualizado.setSenha(passwordEncoder.encode(cadastro.getSenha()));
                cadastroRepositorio.saveAndFlush(cadastroAtualizado);
            } else {
                return new ModelAndView("erro", "mensagem", "Cadastro não encontrado.");
            }
        }
        cadastro.setSenha(passwordEncoder.encode(cadastro.getSenha()));
        cadastroRepositorio.saveAndFlush(cadastro);

        cadastrar(new Cadastro());
        return new ModelAndView("redirect:/inicio");
    }

    @GetMapping("/atualizacaoDados")
    public ModelAndView atualizar(Cadastro usuario){
        ModelAndView mv = new ModelAndView("pages/atualizacaoDeDados");
        mv.addObject("usuario", usuario);
        mv.addObject("listaNomes", cadastroRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarUsuario/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        Long userId = cadastroService.getIdByEmail();
        if (!id.equals(userId)) {
            return new ModelAndView("redirect:/aviso");
        }
        Optional<Cadastro> cadastro = cadastroRepositorio.findById(id);
        return atualizar(cadastro.get());
    }

    @PostMapping("/atualizarUsuario")
    public ModelAndView atualizarUsuario(Cadastro cadastro, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();


        if (result.hasErrors()) {
            return atualizar(cadastro);
        }

        Long userId = cadastroService.getIdByEmail();
        if (userId != null) {
            Optional<Cadastro> cadastroExistente = cadastroRepositorio.findById(userId);
            if (cadastroExistente.isPresent()) {
                Cadastro cadastroAtualizado = cadastroExistente.get();
                cadastroAtualizado.setNome(cadastro.getNome());
                cadastroAtualizado.setSobrenome(cadastro.getSobrenome());
                cadastroAtualizado.setEmail(cadastro.getEmail());
                cadastroAtualizado.setTelefone(cadastro.getTelefone());
                cadastroAtualizado.setCEP(cadastro.getCEP());
                cadastroAtualizado.setRua(cadastro.getRua());
                cadastroAtualizado.setBairro(cadastro.getBairro());
                cadastroAtualizado.setNumero(cadastro.getNumero());
                cadastroAtualizado.setCidade(cadastro.getCidade());
                cadastroAtualizado.setEstado(cadastro.getEstado());
                cadastroAtualizado.setSenha(passwordEncoder.encode(cadastro.getSenha()));
                cadastroRepositorio.saveAndFlush(cadastroAtualizado);
            } else {
//                mv.addObject("erro", "Cadastro não encontrado");
                return new ModelAndView("erro", "mensagem", "Cadastro não encontrado.");
            }
        }

        return new ModelAndView("redirect:/listagemDePlantas");
    }
}