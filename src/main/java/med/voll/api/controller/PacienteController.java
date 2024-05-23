package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.DadosAtualizacaoPaciente;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dadosPaciente) {
        repository.save(new Paciente(dadosPaciente));
    }

    @GetMapping("/{id}")
    public DadosListagemPaciente buscaPaciente(@PathVariable Long id) {
        Optional<Paciente> pacienteBuscado = repository.findById(id);

        return pacienteBuscado.map(DadosListagemPaciente::new).orElse(null);
    }

    @GetMapping
    public Page <DadosListagemPaciente> listarTodos(@PageableDefault(size=10, page=0, sort = {"nome"}) Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(DadosListagemPaciente::new);
    }

    @Transactional
    @PutMapping("/{id}")
    public DadosListagemPaciente alterarPaciente(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoPaciente dados) {
        Paciente pacienteBuscado = repository.getReferenceById(id);
        pacienteBuscado.atualizarInformacoes(dados);

        return repository.findById(id).map(DadosListagemPaciente::new).orElse(null);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletaPaciente(@PathVariable Long id) {
        Optional<Paciente> pacienteBuscado = repository.findById(id);
        pacienteBuscado.ifPresent(Paciente::setInativo);
    }

}
