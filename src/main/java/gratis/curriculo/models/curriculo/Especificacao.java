package gratis.curriculo.models.curriculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "especificacao", schema = "curriculo")
@Data
public class Especificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prk;

    @Column
    private String categoria;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "especificacao", orphanRemoval = true)
    private List<Item> itens;

}
