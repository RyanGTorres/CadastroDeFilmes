package dev.java10x.CadastroDeNinjas.Filmes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmesRepository extends JpaRepository<FilmesModel, Long> {
}
