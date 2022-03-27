package gratis.curriculo.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ValidadorFaixa {

    public static void validaFaixa(Faixa faixa){
        validaValorCprInicioFimFaixa(faixa);
    }

    public static void validaFaixas(List<Faixa> faixas){
        if(Objects.isNull(faixas) || faixas.size() < 2){
            throw new FaixaException("Lista");
        }
        Faixa faixaAtual;
        Faixa proximaFaixa;
        for (int i = 0; i < faixas.size(); i++) {
            if(faixas.size() == i+1){
                break;
            }
            faixaAtual = faixas.get(i);
            proximaFaixa = faixas.get(i+1);
            validaValoresFaixa(faixaAtual, proximaFaixa);
        }
    }

    private static void validaValoresFaixa(Faixa faixa1, Faixa faixa2){
        if(!validaValorCprInicioFimFaixa(faixa1) || !validaValorCprInicioFimFaixa(faixa2)){
            throw new FaixaException("O valor final de cada faixa deve ser maior que o inicial");
        }
        if(!validaValorInicioFimEntreFaixas(faixa1, faixa2)){
            throw new FaixaException("Os valores iniciais e finais devem seguir a ordem crescente entre todas as faixas.");
        }
        if(!validaValorRealCobrancaEntreFaixas(faixa1, faixa2)){
            throw new FaixaException("O valor de cobrança não pode ser decrescente entre as faixas, realize o calculo de taxa minima de cada uma.");
        }

    }

    private static boolean validaValorCprInicioFimFaixa(Faixa faixa){
        return faixa.getValorCprFim().compareTo(faixa.getValorCprInicio()) > 0;
    }

    private static boolean validaValorInicioFimEntreFaixas(Faixa faixa1, Faixa faixa2){
        return faixa2.getValorCprInicio().compareTo(faixa1.getValorCprFim()) > 0;
    }

    private static boolean validaValorRealCobrancaEntreFaixas(Faixa faixa1, Faixa faixa2){
        return getValorRealCobranca(faixa2).compareTo(getValorRealCobranca(faixa1)) > 0;
    }


    private static BigDecimal getValorRealCobranca(Faixa faixa){
        return faixa.getTipoCobranca().equals(TipoCobranca.VALOR) ? faixa.getValorCobranca() : faixa.getValorCprInicio().divide(BigDecimal.valueOf(100)).multiply(faixa.getValorCobranca());
    }

}
