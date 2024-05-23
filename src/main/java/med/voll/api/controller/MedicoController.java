package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }
    //Paginação de retorno
    //http://localhost:8080/medicos?size=1&page=2&sort=nome,desc
    //muda tamanho da pagina, qual pagina visualiza e a ordenacao decrescente
    @GetMapping
    public Page<DadosListagemMedico> listarTodos(@PageableDefault(size=10, page=0, sort = {"especialidade"}) Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
    }

    @GetMapping("/{id}")
    public DadosListagemMedico buscarMedico(@PathVariable Long id) {
        Optional<Medico> medicoBuscado = repository.findById(id);
        return medicoBuscado.map(DadosListagemMedico::new).orElse(null);
    }

    @PutMapping("/{id}")
    @Transactional
    public DadosListagemMedico alterarMedico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoMedico dados) {
        Medico medicoBuscado = repository.getReferenceById(id);
        medicoBuscado.atualizarInformacoes(dados);

        return repository.findById(id).map(DadosListagemMedico::new).orElse(null);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletaMedico(@PathVariable Long id) {
        Optional<Medico> medicoBuscado = repository.findById(id);
        medicoBuscado.ifPresent(Medico::setInativo);
    }

}
