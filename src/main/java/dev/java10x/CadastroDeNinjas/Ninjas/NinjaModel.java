package dev.java10x.CadastroDeNinjas.Ninjas;


import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Entity transforma um classe em uma entidade do BD
//JPA = Java Persistence API
@Entity
@Table(name = "tb_cadastro")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String nome;

    @Column(unique = true)
    private String email;

    private int idade;

    //@ManyToOne um ninja tem uma unica missao
    @ManyToOne
    @JoinColumn(name = "missoes_id") //Foreing Key  ou chave estrangeira
    private MissoesModel missoes;

}
