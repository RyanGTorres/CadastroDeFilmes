package dev.java10x.CadastroDeNinjas.Pessoa;

import dev.java10x.CadastroDeNinjas.Filmes.FilmesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;
    private String nome;
    private String email;
    private String imgUrl;
    private int idade;
    private String diretorFavorito;
    private FilmesModel filmes;

}

