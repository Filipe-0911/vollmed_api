package med.voll.api.domain.consulta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByPacienteIdAndDataBetweenAndCanceladaIsFalse(Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndDataAndCanceladaIsFalse(Long idMedico, LocalDateTime data);

    @Query("select c from Consulta c where c.cancelada = false")
    Page<Consulta> buscaConsultasAtivas(Pageable pageable);
}
