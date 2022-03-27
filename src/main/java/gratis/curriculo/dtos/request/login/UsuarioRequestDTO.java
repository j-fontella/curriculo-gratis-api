package gratis.curriculo.dtos.request.login;

import gratis.curriculo.dtos.request.endereco.EnderecoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class UsuarioRequestDTO {

    private Long prk;

    @NotBlank(message = "O nome deve ser preennchido")
    private String nome;

    @NotNull(message = "O endereço deve ser preennchido")
    @Valid
    private EnderecoRequestDTO endereco;

    private String docRegistro;

    @NotBlank(message = "O email deve ser preennchido")
    @Email(message = "Email inválido")
    private String email;

    private String senha;

    private String novaSenha;

    @NotBlank(message = "A idade deve ser preenchida")
    private String idade;

    @NotBlank(message = "O estado civil deve ser preenchido")
    private String estadoCivil;

    @NotBlank(message = "O telefone deve ser preenchido")
    private String telefone;

    @NotBlank(message = "A nacionalidade deve ser preenchida")
    private String nacionalidade;

}
