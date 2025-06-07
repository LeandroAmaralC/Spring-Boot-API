package med.voli.api.controller;

import jakarta.validation.Valid;
import med.voli.api.endereco.Endereco;
import med.voli.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
//metdodo get sem paginação
//    @GetMapping
//    public List<DadosListagemMedico> listar() {
//       return  repository.findAll().stream().map(DadosListagemMedico:: new).toList();
//    }

    @GetMapping
    public List<DadosListagemMedicoPrincipal> listarDados(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {

        Page<DadosListagemMedico> listarDados =  repository.findAll(paginacao).map(DadosListagemMedico:: new);


        List<DadosListagemMedico> listagemdeativos ;

        listagemdeativos =  listarDados.stream().filter(novaLista -> novaLista.ativo().startsWith("1")).collect(Collectors.toList());

        List<DadosListagemMedicoPrincipal> listagemPrincipal = new ArrayList<>() ;

        for(DadosListagemMedico novaLista: listarDados ){
            DadosListagemMedicoPrincipal dadosListagemMedicoPrincipal = new DadosListagemMedicoPrincipal(novaLista.id(), novaLista.nome(), novaLista.email(), novaLista.crm(), novaLista.especialidade());
            listagemPrincipal.add(dadosListagemMedicoPrincipal);
        }

        System.out.println(listagemdeativos);

        return listagemPrincipal;

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    //deletar de vez do banco
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id) {
//    repository.deleteById(id);
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.excluir();
    }

}
