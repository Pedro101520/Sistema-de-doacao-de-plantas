package tech.pedrolima.DoacaoDePlantas.controle;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tech.pedrolima.DoacaoDePlantas.System.CadastroService;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;
import tech.pedrolima.DoacaoDePlantas.modelos.Planta;
import tech.pedrolima.DoacaoDePlantas.repositorios.PlantaRepositorio;
import tech.pedrolima.DoacaoDePlantas.utils.EnvioEmail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class PlantaControle {

    private static String caminhoImagens = "/home/pedro/Documentos/ImagensPlanta/";
    private String imagemPorId;
    private Long idPlanta;

    @Autowired
    private PlantaRepositorio plantaRepositorio;

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private Cadastro cadastro;

    @Autowired
    private EnvioEmail envioEmail;

    @GetMapping("/cadastroPlanta")
    public ModelAndView cadastrar(Planta planta) {
        ModelAndView mv = new ModelAndView("pages/MainScreen");
        mv.addObject("planta", planta);
        return mv;
    }

    @GetMapping("/listagemDePlantas")
    public ModelAndView listar(Planta planta) {
        ModelAndView mv = new ModelAndView("pages/MainScreen");
        mv.addObject("listaImagens", plantaRepositorio.findAll());
        Long userId = cadastroService.getIdByEmail();
        //Mesmo estando em uma classe diferente da qual o /editar está, o thymeleaf copnsegue reconhecer o userId
        //Pois, estou adicionanod o userId ao objeto
        mv.addObject("userId", userId);
        return mv;
    }


    @PostMapping("/cadastrarImagem")
    public ModelAndView salvar(@Valid Planta planta, BindingResult result,
                               @RequestParam("file") MultipartFile arquivo) {
        if (result.hasErrors()) {
            return cadastrar(planta);
        }

        //Parte responsavel por relacionar as tabelas usuario e planta
        Long userId = cadastroService.getIdByEmail();
        Optional<Cadastro> cadastroOptional = cadastroService.findById(userId);

        if (!cadastroOptional.isPresent()) {
            return new ModelAndView("errorPage");
        }
        Cadastro cadastro = cadastroOptional.get();
        planta.setCadastro(cadastro);

        plantaRepositorio.saveAndFlush(planta);

        try {
            if (!arquivo.isEmpty()) {
                String nomeOriginal = arquivo.getOriginalFilename();
                String extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
                String prefixo = planta.getId().toString();
                String nomeModificado = prefixo + nomeOriginal.replaceAll("\\s", "_");
                Path caminho = Paths.get(caminhoImagens + nomeModificado);
                byte[] bytes = arquivo.getBytes();
                Files.write(caminho, bytes);

                // Defina o caminho da imagem na planta e salve novamente
                planta.setCaminhoImg(nomeModificado);
                plantaRepositorio.saveAndFlush(planta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/listagemDePlantas");
    }

    @GetMapping("/listagemDePlantas/plantasDisponiveis/{imagem}")
    @ResponseBody
    public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws Exception{
        File imagemArquivo = new File(caminhoImagens + imagem);
        if(imagem != null || imagem.trim().length() > 0){
            return Files.readAllBytes(imagemArquivo.toPath());
        }
        return null;
    }

    @GetMapping("/listagemDePlantas/informacoesPlanta")
    @ResponseBody
    public byte[] retornarImagemPorID() throws Exception{
        String teste = getImagemPorId();
        File imagemArquivo = new File(caminhoImagens + getImagemPorId());
        if(getImagemPorId() != null || getImagemPorId().trim().length() > 0){
            return Files.readAllBytes(imagemArquivo.toPath());
        }
        return null;
    }

    @GetMapping("/listagemDePlantas/informacoes/{id}")
    public ModelAndView exibirDetalhesPlanta(@PathVariable("id") Long id) {
        setIdPlanta(id);
        Optional<Planta> plantaOptional = plantaRepositorio.findById(id);
        Planta planta = plantaOptional.get();
        String imagem = planta.getCaminhoImg();
        setImagemPorId(imagem);

        ModelAndView mv = new ModelAndView("pages/adotarPlanta");
        Optional<Planta> infoPlanta = plantaRepositorio.findById(id);
        mv.addObject("infoPlanta", infoPlanta);
        return mv;
    }

    @GetMapping("/listagemDePlantas/informacoes/adotarPlanta")
    public ModelAndView adotar() {
        Optional<Planta> plantaOptional = plantaRepositorio.findById(getIdPlanta());
        Planta planta = plantaOptional.get();
        String email = planta.getCadastro().getEmail();

        envioEmail.emailDoacao(email);

        ModelAndView mv = new ModelAndView("pages/MainScreen");
        return mv;
    }

    public String getImagemPorId() {
        return imagemPorId;
    }

    public void setImagemPorId(String imagemPorId) {
        this.imagemPorId = imagemPorId;
    }

    public Long getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(Long idPlanta) {
        this.idPlanta = idPlanta;
    }
}