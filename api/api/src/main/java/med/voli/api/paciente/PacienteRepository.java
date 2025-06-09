package med.voli.api.paciente;

import med.voli.api.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
