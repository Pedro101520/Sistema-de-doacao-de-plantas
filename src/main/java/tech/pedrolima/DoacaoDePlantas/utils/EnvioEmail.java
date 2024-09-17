package tech.pedrolima.DoacaoDePlantas.utils;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EnvioEmail {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    private int randomNumber;

    public void imprimir(String email){
        Random random = new Random();
        randomNumber = 10000 + random.nextInt(90000);

        String assunto = "Redefinição de senha";

        String mensagem = "Este é o seu código de redefinição de senha\n" +
                randomNumber;

        //Código de envio de emails, com Spring boot
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);
            setCodigo(randomNumber);
            System.out.println("Email enviado");
        }catch (Exception e){
            System.out.println("Erro ao enviar");
        }
    }

    public void emailDoacao(String email, Long idPlanta, Long idUser){
        String assunto = "Doar Planta";

        String mensagem = "Olá tudo bem?\n\n" +
                "Estou entrando em contato, pois apareceu um interessado em adotar a planta\n" +
                "que você cadastrou no site doaplanta.com\n" +
                "Acesse o link e veja mais informações\n\n" +
                "localhost:8080/doar/" + idPlanta + "/" + idUser;

        //Código de envio de emails, com Spring boot
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);
            System.out.println("Email enviado");
        }catch (Exception e){
            System.out.println("Erro ao enviar");
        }
    }

    public void emailDoacaoNegada(String email){
        String assunto = "Status sobre o seu pedido de adoção de planta";

        String mensagem = "Olá tudo bem?\n\n" +
                "Estou entrando em contato, pois o resposnsável pela planta que você se\n " +
                "interessou, infelizmente negou o seu pedido de adoção\n" +
                "Mas não fique triste, você poderá continuar navegando pelo site e encontrar a sua próxima planta";

        //Código de envio de emails, com Spring boot
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);
            System.out.println("Email enviado");
        }catch (Exception e){
            System.out.println("Erro ao enviar");
        }
    }

    public void emailInfoDoacao(String emailAdotante, String enderecoRetirada, String obs, String emailDoador, String nomePlanta, String numWhatsApp){
        String assunto = "Status sobre o seu pedido de adoção de planta";

        String mensagem = "Olá tudo bem?\n\n" +
                "Estou entrando em contato, pois o resposnsável pela planta que você se\n " +
                "interessou, aceitou o pedido de adoção da sua futura planta. Abaixo vou te fornecer algumas informações de contato:\n\n" +
                "Nome da planta:" + nomePlanta + "\n" +
                "Endereço de retirada: " + enderecoRetirada + "\n" +
                "Observações: " + obs + "\n" +
                "Número de WhatsApp: " + numWhatsApp + "\n" +
                "Email do doador: " + emailDoador + "\n";

        //Código de envio de emails, com Spring boot
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(emailAdotante);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);
            System.out.println("Email enviado");
        }catch (Exception e){
            System.out.println("Erro ao enviar");
        }
    }


    public int getCodigo(){
        return randomNumber;
    }

    public void  setCodigo(int codigo){
        randomNumber = codigo;
    }

}
