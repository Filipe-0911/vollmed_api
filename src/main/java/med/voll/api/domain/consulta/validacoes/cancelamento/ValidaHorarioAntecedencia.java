package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidaHorarioAntecedenciaCancelamento")
public class ValidaHorarioAntecedencia implements ValidadorCancelamentoConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void valida(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        LocalDateTime dataAgora = LocalDateTime.now();
        LocalDateTime dataConsulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta()).getData();

        long diferencaDeHorario = Duration.between(dataAgora, dataConsulta).toHours();

        if (diferencaDeHorario < 24) {
            throw new ValidacaoException("Para cancelar a consulta é necessário 24 horas de antecedência.");

        }
    }
}
