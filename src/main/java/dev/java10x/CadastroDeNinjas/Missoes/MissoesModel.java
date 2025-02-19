package dev.java10x.CadastroDeNinjas.Missoes;


import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeMissao;
    private char rankMissao;
    // uma missao pode ter varios ninjas @OneToMany
    @OneToMany(mappedBy = "missoes")
    private List<NinjaModel> ninja;


    public MissoesModel() {
    }

    public MissoesModel(String nomeMissao, char rankMissao) {
        this.nomeMissao = nomeMissao;
        this.rankMissao = rankMissao;
    }

    public String getNomeMissao() {
        return nomeMissao;
    }

    public void setNomeMissao(String nomeMissao) {
        this.nomeMissao = nomeMissao;
    }

    public char getRankMissao() {
        return rankMissao;
    }

    public void setRankMissao(char rankMissao) {
        this.rankMissao = rankMissao;
    }
}
