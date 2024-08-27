package tech.pedrolima.DoacaoDePlantas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.pedrolima.DoacaoDePlantas.modelos.Cadastro;
import tech.pedrolima.DoacaoDePlantas.modelos.Planta;

public interface PlantaRepositorio extends JpaRepository<Planta, Long> {
}