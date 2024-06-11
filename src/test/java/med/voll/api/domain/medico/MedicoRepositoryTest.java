package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosEnderecoDTO;
import med.voll.api.domain.paciente.DadosCadastroPacienteDTO;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando único médico cadastrado não está disponível na data.")
    void escolherMedicoAleatorioNaDataCenario1() {
        // Given
        LocalDateTime proxSegundaAs10 = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10)
                .withMinute(0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@voll.med.cliente", "12345678901");
        cadastrarConsulta(medico, paciente, proxSegundaAs10);
        // When
        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioNaData(Especialidade.CARDIOLOGIA, proxSegundaAs10);

        // Then
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria medico quando ele estiver disponível na data.")
    void escolherMedicoAleatorioNaDataCenario2() {
        // Given
        LocalDateTime proxSegundaAs10 = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10)
                .withMinute(0);
        Medico medicoCadastrado = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        // When
        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioNaData(Especialidade.CARDIOLOGIA, proxSegundaAs10);

        // Then
        assertThat(medicoLivre).isEqualTo(medicoCadastrado);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data, null, false));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedicoDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedicoDTO(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPacienteDTO dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPacienteDTO(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEnderecoDTO dadosEndereco() {
        return new DadosEnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}