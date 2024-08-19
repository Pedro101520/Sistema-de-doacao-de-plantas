package tech.pedrolima.DoacaoDePlantas.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;
import tech.pedrolima.DoacaoDePlantas.modelos.Planta;

@Controller
public class DoarPlanta {
    @GetMapping("/doarPlanta")
    public ModelAndView mostrarFormularioDoarPlanta() {
        ModelAndView mv = new ModelAndView("pages/doarPlanta");
        mv.addObject("planta", new Planta());
        return mv;
    }
}
