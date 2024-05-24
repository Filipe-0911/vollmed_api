package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados, UriComponentsBuilder uriBuilder) {
        Medico medico = new Medico(dados);
        repository.save(medico);

        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO((medico)));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listarTodos(@PageableDefault(size=10, page=0, sort = {"especialidade"}) Pageable pageable) {
        Page<DadosListagemMedicoDTO> page = repository.findAllByAtivoTrue(pageable).map(DadosListagemMedicoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> buscarMedico(@PathVariable Long id) {
        Medico medicoBuscado = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medicoBuscado));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> alterarMedico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoMedicoDTO dados) {
        Medico medicoBuscado = repository.getReferenceById(id);
        medicoBuscado.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medicoBuscado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletaMedico(@PathVariable Long id) {
        Optional<Medico> medicoBuscado = repository.findById(id);
        medicoBuscado.ifPresent(Medico::setInativo);

        return ResponseEntity.noContent().build();
    }

}
