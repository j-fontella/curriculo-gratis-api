package gratis.curriculo.dtos.response.login;

import gratis.curriculo.models.curriculo.Especificacao;
import gratis.curriculo.models.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class UsuarioResponseDTO {
    private Long prk;
    private String nome;
    private Endereco endereco;
    private String telefone;
    private String idade;
    private String estadoCivil;
    private String nacionalidade;
    private String email;
}
