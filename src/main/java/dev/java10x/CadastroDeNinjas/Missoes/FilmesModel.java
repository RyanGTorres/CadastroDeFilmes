    package dev.java10x.CadastroDeNinjas.Missoes;


    import com.fasterxml.jackson.annotation.JsonIgnore;
    import dev.java10x.CadastroDeNinjas.Ninjas.PessoaModel;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.ToString;

    import java.util.List;

    @Entity
    @Table(name = "tb_filmes")
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @ToString
    public class FilmesModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "filme")
        private String filme;

        @Column(name = "genero")
        private String genero;

        @Column(name = "nota_filme")
        private Double notaFilme;

        // uma missao pode ter varios ninjas @OneToMany
        @OneToMany(mappedBy = "filmes")
        @JsonIgnore
        private List<PessoaModel> ninja;

    }
