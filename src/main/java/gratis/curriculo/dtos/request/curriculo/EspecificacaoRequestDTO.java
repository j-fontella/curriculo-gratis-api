package gratis.curriculo.dtos.request.curriculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class EspecificacaoRequestDTO {

    @NotBlank(message = "Contate o suporte")
    private String categoria;

    @NotNull(message = "Contate o suporte")
    private List<@Valid ItemRequestDTO> itens;
}
