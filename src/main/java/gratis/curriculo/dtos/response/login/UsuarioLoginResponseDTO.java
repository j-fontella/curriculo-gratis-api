package gratis.curriculo.dtos.response.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class UsuarioLoginResponseDTO {
    private Long prk;
    private String nome;
    private String token;
}
