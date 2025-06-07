package med.voli.api.medico;

public record DadosListagemMedico(Long id,String nome, String email, String crm, Especialidade especialidade, String ativo ) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getAtivo());
    }

}
