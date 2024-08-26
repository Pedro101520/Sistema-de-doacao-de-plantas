package tech.pedrolima.DoacaoDePlantas.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.pedrolima.DoacaoDePlantas.System.CadastroService;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;
import tech.pedrolima.DoacaoDePlantas.modelos.Planta;

@Controller
public class DoarPlanta {

    @Autowired
    private CadastroService cadastroService;

    @GetMapping("/doarPlanta")
    public ModelAndView mostrarFormularioDoarPlanta() {
        ModelAndView mv = new ModelAndView("pages/doarPlanta");
        mv.addObject("planta", new Planta());

        Long userId = cadastroService.getIdByEmail();
        mv.addObject("userId", userId);
        return mv;
    }
}
