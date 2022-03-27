package gratis.curriculo.models.curriculo;


import gratis.curriculo.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item", schema = "curriculo")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prk;

    @Column
    private String responsavel;

    @Column
    private String descricao;

    @Column
    private LocalDate dataInicial;

    @Column
    private LocalDate dataFinal;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="frk_especificacao", referencedColumnName = "prk")
    private Especificacao especificacao;

    public boolean temHeader(){
        return Objects.nonNull(dataInicial) || Objects.nonNull(dataFinal) || !Utils.isNullOrBlank(responsavel);
    }

    public String getTextoHeader(){
        String header = "";
        boolean temResponsavel = false;
        if(Objects.nonNull(responsavel)){
            temResponsavel = true;
            header += responsavel;
        }
        if(Objects.nonNull(dataInicial)){
            if(temResponsavel){
                header += " - ";
            }
            header += Utils.formataDataFormatoBrasil(dataInicial.toString()) + " - ";
            if(Objects.nonNull(dataFinal)){
                header += Utils.formataDataFormatoBrasil(dataFinal.toString());
            }else{
                header += "Atual";
            }

        }

        return header;
    }
}
