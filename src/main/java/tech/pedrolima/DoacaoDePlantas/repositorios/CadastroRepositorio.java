package tech.pedrolima.DoacaoDePlantas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;

import java.util.Optional;

public interface CadastroRepositorio extends JpaRepository<Cadastro, Long> {

//    @Query(value = "SELECT * FROM usuario WHERE email = :email AND senha = :senha", nativeQuery = true)
//    public Cadastro login(String email, String senha);

    public Cadastro findByEmail(String email);

}
