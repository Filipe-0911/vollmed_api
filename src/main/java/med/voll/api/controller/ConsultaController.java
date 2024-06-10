package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name =  "bearer-key")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultas agendaDeConsultas;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public ResponseEntity<Page<DadosConsultaListagem>> listarTodasQueNaoForamCanceladas(@PageableDefault(size=10, page=0, sort = {"data"}) Pageable pageable) {
        Page<DadosConsultaListagem> listaConsultas = consultaRepository.buscaConsultasAtivas(pageable).map(DadosConsultaListagem::new);
        return ResponseEntity.ok(listaConsultas);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendar (@RequestBody @Valid DadosAgendamentoConsulta dados)  {
        DadosDetalhamentoConsulta dadosDetalhamentoConsulta = agendaDeConsultas.agendar(dados);

        return ResponseEntity.ok(dadosDetalhamentoConsulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar (@RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        agendaDeConsultas.cancelar(dadosCancelamentoConsulta);

        return ResponseEntity.noContent().build();
    }
}
