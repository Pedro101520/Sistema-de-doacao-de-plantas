package tech.pedrolima.DoacaoDePlantas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.pedrolima.DoacaoDePlantas.modelos.Solicitacao;

public interface SolicitacaoRepositorio extends JpaRepository<Solicitacao, Long>{
    @Query(value = "SELECT disponivel FROM solicitacao WHERE id_user = :id_user AND id_planta = :id_planta", nativeQuery = true)
    Boolean isPlantaDisponivel(@Param("id_user") Long idUser, @Param("id_planta") Long idPlanta);

}
