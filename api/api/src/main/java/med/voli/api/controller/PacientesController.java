package med.voli.api.controller;

import jakarta.validation.Valid;
import med.voli.api.medico.DadosListagemMedico;
import med.voli.api.medico.Medico;
import med.voli.api.medico.MedicoRepository;
import med.voli.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }

//    @GetMapping
//    public List<DadosListagemPaciente> listar() {
//    return repository.findAll().stream().map(DadosListagemPaciente::new).toList();
//    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
        //Paciente paciente = repository.getReferenceById(id);
       // paciente.excluir();
    }

}
