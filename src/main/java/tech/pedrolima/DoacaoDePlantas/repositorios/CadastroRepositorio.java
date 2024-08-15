package tech.pedrolima.DoacaoDePlantas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;

public interface CadastroRepositorio extends JpaRepository<Cadastro, Long> {

}
