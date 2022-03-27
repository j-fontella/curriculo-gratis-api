package gratis.curriculo.models.curriculo;


import gratis.curriculo.models.login.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curriculo", schema = "curriculo")
@Data
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prk;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frk_usuario", referencedColumnName = "prk")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinTable(name = "curriculo_especificacoes", schema = "curriculo")
    private List<Especificacao> especificacoes;

}
