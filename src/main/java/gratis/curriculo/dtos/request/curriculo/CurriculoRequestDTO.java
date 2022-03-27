package gratis.curriculo.dtos.request.curriculo;


import gratis.curriculo.dtos.request.login.UsuarioRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class CurriculoRequestDTO {

    @NotNull(message = "CCU")
    @Valid
    private UsuarioRequestDTO usuario;

    @NotNull(message = "CCC")
    @Valid
    private List<EspecificacaoRequestDTO> especificacoes;


}
