package gratis.curriculo.utils;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Faixa {
    private BigDecimal valorCprInicio;
    private BigDecimal valorCprFim;
    private BigDecimal valorCobranca;
    private TipoCobranca tipoCobranca;
}
