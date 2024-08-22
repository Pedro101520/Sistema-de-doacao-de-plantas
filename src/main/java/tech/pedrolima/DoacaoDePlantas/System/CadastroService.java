package tech.pedrolima.DoacaoDePlantas.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;
import tech.pedrolima.DoacaoDePlantas.repositorios.CadastroRepositorio;

@Service
public class CadastroService implements UserDetailsService {

    @Autowired
    private CadastroRepositorio cadastroRepositorio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cadastro cadastro = cadastroRepositorio.findByEmail(email);

        if(cadastro != null){
            var springUser = User.withUsername(cadastro.getEmail())
                    .password(cadastro.getSenha())
                    .build();

            return springUser;
        }
        return null;
    }
}
