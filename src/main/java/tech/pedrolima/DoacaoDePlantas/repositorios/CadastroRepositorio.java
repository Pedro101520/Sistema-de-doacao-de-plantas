package tech.pedrolima.DoacaoDePlantas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;

import java.util.Optional;

public interface CadastroRepositorio extends JpaRepository<Cadastro, Long> {

    @Query(value = "SELECT email FROM usuario WHERE email = :email", nativeQuery = true)
    String QueryEmail(@Param("email") String email);

    @Query(value = "SELECT id FROM usuario WHERE email = :email", nativeQuery = true)
    String QueryEmailRecover(@Param("email") String email);

    public Cadastro findByEmail(String email);
}