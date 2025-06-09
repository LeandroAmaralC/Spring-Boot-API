package med.voli.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voli.api.medico.Especialidade;

public record DadosCadastroPaciente(
                                    @NotBlank
                                    String nome,
                                    @NotBlank
                                    @Email
                                    String email,
                                    @NotBlank
                                    String telefone,
                                    @NotBlank
                                  //  @Pattern(regexp = "\\d{11,11}" )
                                    String cpf,
                                    @NotNull @Valid DadosEnderecoPaciente dadosEnderecoPaciente
                                    )
{ }
