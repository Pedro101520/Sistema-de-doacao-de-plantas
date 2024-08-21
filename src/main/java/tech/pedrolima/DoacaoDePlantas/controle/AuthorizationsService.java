package tech.pedrolima.DoacaoDePlantas.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.pedrolima.DoacaoDePlantas.repositorios.CadastroRepositorio;

@Service
public class AuthorizationsService implements UserDetailsService {

    @Autowired
    CadastroRepositorio cadastroRepositorio;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return cadastroRepositorio.findByEmail(email);
    }
}
