package gratis.curriculo.models.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gratis.curriculo.models.endereco.Endereco;
import gratis.curriculo.models.curriculo.Especificacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Usuario", schema = "login")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prk;

    @Column(columnDefinition = "varchar(100)")
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frkEndereco", referencedColumnName = "prk")
    private Endereco endereco;

    @Column
    private String telefone;

    @Column
    private String idade;

    @Column
    private String estadoCivil;

    @Column
    private String nacionalidade;

    @Column(columnDefinition = "varchar(150)")
    private String email;

    @Column
    private String senha;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_especificacoes", schema = "login")
    private List<Especificacao> especificacoes;




}
