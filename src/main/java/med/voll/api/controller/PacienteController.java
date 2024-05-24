package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPacienteDTO> cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dadosPaciente, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(dadosPaciente);
        repository.save(paciente);

        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDTO(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPacienteDTO> detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page <DadosListagemPacienteDTO>> listarTodos(@PageableDefault(size=10, page=0, sort = {"nome"}) Pageable pageable) {
        Page<DadosListagemPacienteDTO> dadosListagemPaciente = repository.findAllByAtivoTrue(pageable).map(DadosListagemPacienteDTO::new);

        return ResponseEntity.ok(dadosListagemPaciente);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPacienteDTO> alterarPaciente(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoPacienteDTO dados) {
        Paciente pacienteBuscado = repository.getReferenceById(id);
        pacienteBuscado.atualizarInformacoes(dados);

        DadosDetalhamentoPacienteDTO dadosAlterados = repository.findById(id).map(DadosDetalhamentoPacienteDTO::new).orElse(null);

        return ResponseEntity.ok(dadosAlterados);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPaciente(@PathVariable Long id) {
        Optional<Paciente> pacienteBuscado = repository.findById(id);
        pacienteBuscado.ifPresent(Paciente::setInativo);

        return ResponseEntity.noContent().build();
    }

}
