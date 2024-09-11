package tech.pedrolima.DoacaoDePlantas.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;
import tech.pedrolima.DoacaoDePlantas.repositorios.CadastroRepositorio;

import java.util.Optional;

@Service
public class CadastroService implements UserDetailsService {

    @Autowired
    private CadastroRepositorio cadastroRepositorio;

    private Long idByEmail;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cadastro cadastro = cadastroRepositorio.findByEmail(email);

//        System.out.println(cadastro.getId());

        if(cadastro != null){
            setIdByEmail(cadastro.getId());
            idByEmail = cadastro.getId();

            var springUser = User.withUsername(cadastro.getEmail())
                    .password(cadastro.getSenha())
                    .build();

            return springUser;
        }
        return null;
    }

    public void setIdByEmail(Long idByEmail){
        this.idByEmail = idByEmail;
    }

    public Long getIdByEmail(){
        return idByEmail;
    }

    public Optional<Cadastro> findById(Long id) {
        return cadastroRepositorio.findById(id);
    }
}