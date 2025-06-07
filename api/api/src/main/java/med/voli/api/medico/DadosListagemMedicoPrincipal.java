package med.voli.api.medico;

public record  DadosListagemMedicoPrincipal(Long id,String nome, String email, String crm, Especialidade especialidade) {

     public DadosListagemMedicoPrincipal(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}