package gratis.curriculo.dtos.request.curriculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class ItemRequestDTO {

    private String responsavel;

    @NotBlank(message = "IRDO")
    private String descricao;

    private LocalDate dataInicial;

    private LocalDate dataFinal;
}
