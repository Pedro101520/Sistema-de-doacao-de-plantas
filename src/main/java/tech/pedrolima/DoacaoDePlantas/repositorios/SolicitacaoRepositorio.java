package tech.pedrolima.DoacaoDePlantas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.pedrolima.DoacaoDePlantas.modelos.Solicitacao;

public interface SolicitacaoRepositorio extends JpaRepository<Solicitacao, Long>{
}
