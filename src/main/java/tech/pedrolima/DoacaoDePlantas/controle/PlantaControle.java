package tech.pedrolima.DoacaoDePlantas.controle;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tech.pedrolima.DoacaoDePlantas.modelos.Planta;
import tech.pedrolima.DoacaoDePlantas.repositorios.PlantaRepositorio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PlantaControle {

    private static String caminhoImagens = "/home/pedro/Documentos/ImagensPlanta";

    @Autowired
    private PlantaRepositorio plantaRepositorio;

    @GetMapping("/cadastroPlanta")
    public ModelAndView cadastrar(Planta planta) {
        ModelAndView mv = new ModelAndView("logins/login");
        mv.addObject("planta", planta);
        return mv;
    }

    @PostMapping("/cadastrarImagem")
    public ModelAndView salvar(@Valid Planta planta, BindingResult result,
                               @RequestParam("file") MultipartFile arquivo) {
//        if (result.hasErrors()) {
//            return cadastrar(planta);
//        }

        plantaRepositorio.saveAndFlush(planta);

        try {
            if (!arquivo.isEmpty()) {
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(caminhoImagens + String.valueOf(planta.getId()) + arquivo.getOriginalFilename());
                Files.write(caminho, bytes);

                //Aqui estou salvando o do arquivo da imagem no banco de dados
                //Assim que se salva informações no banco de dados usando Spring Boot
                planta.setCaminhoImg(String.valueOf(planta.getId()) + arquivo.getOriginalFilename());

                System.out.println("Sucesso");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Falha");
        }

        return new ModelAndView("redirect:/cadastroPlanta"); // Redirecionando após salvar
    }
}
