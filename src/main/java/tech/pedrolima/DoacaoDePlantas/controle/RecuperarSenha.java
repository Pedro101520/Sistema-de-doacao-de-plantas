package tech.pedrolima.DoacaoDePlantas.controle;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;
import tech.pedrolima.DoacaoDePlantas.repositorios.CadastroRepositorio;
import tech.pedrolima.DoacaoDePlantas.utils.EnvioEmail;


@Controller
public class RecuperarSenha {

    @Autowired
    private CadastroRepositorio cadastroRepositorio;

    @Autowired
    private EnvioEmail envioEmail;

    private static int tentativas = 0;
    private static String emailRecover;

    @GetMapping("/recuperarSenha")
    public ModelAndView recuperar() {
        ModelAndView mv = new ModelAndView("pages/recuperarSenha");
        return mv;
    }

    @PostMapping("/recuperarSenha/informarEmail")
    //Aqui pega o valor do campo "username" no formulário HTML e o passa para essa variavel
    public ModelAndView confirmar(@RequestParam("username") String email, Cadastro usuario) {
        ModelAndView mv;
        String emailExistente = cadastroRepositorio.QueryEmail(email);
        if (emailExistente != null) {
            emailRecover = email;
            envioEmail.imprimir(emailExistente);
            mv = new ModelAndView("pages/senhaRecuperar");
            mv.addObject("usuario", usuario);
        } else {
            mv = new ModelAndView("pages/recuperarSenha");
            mv.addObject("erro", "Email não encontrado.");
        }
        return mv;
    }

    @PostMapping("/recuperarSenha/informarEmail/atualizarSenha")
    public ModelAndView informarCodigo(@RequestParam("code") int codigo, Cadastro usuario, RedirectAttributes redirectAttributes, HttpSession session) {

        ModelAndView mv;

        System.out.println(codigo);

        if (codigo == envioEmail.getCodigo()) {
            session.setAttribute("codigoValido", true);
            mv = new ModelAndView("pages/senhaAtualizar");
            mv.addObject("usuario", usuario);
            tentativas = 0;
        } else {
            mv = new ModelAndView("pages/senhaRecuperar");
            System.out.println("Código inválido");
            tentativas++;
            System.out.println("Número de tentativas: " + tentativas);
            if (tentativas == 3) {
                redirectAttributes.addFlashAttribute("alertMessage", "Muitas tentativas erradas. Por favor, verifique o email e tente novamente!");
                tentativas = 0;
                return new ModelAndView("redirect:/recuperarSenha");
            }
        }

        return mv;
    }

    @PostMapping("/alterarSenha")
    public ModelAndView atualizarSenha(@RequestParam("passwordRecover") String novaSenha, HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();


        Cadastro cadastro = cadastroRepositorio.findByEmail(emailRecover);
        if (cadastro != null) {
            cadastro.setSenha(passwordEncoder().encode(novaSenha));
            cadastroRepositorio.save(cadastro);
            mv.setViewName("redirect:/inicio");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar a senha. Usuário não encontrado.");
            mv.setViewName("redirect:/recuperarSenha");
        }

        // Limpar emailRecover após a atualização
        emailRecover = null;

        return mv;
    }

    public  PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
