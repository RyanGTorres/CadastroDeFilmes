package dev.java10x.CadastroDeNinjas.Filmes;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class FilmesService {

    private FilmesRepository filmesRepository;
    public FilmesService(FilmesRepository filmesRepository) {
        this.filmesRepository = filmesRepository;
    }

    public List<FilmesModel> listarFilmes(){
        return filmesRepository.findAll();
    }

    public FilmesModel listarFilmesPorId(Long id){
        Optional<FilmesModel> filmesPorId = filmesRepository.findById(id);
        return filmesPorId.orElse(null);
    }

    public FilmesModel criarFilme(@RequestBody FilmesModel filmes){
        return filmesRepository.save(filmes);
    }

    public FilmesModel alterarFilme(Long id, FilmesModel filmesModel) {
        Optional<FilmesModel> filmesExistentes = filmesRepository.findById(id);
        if (filmesExistentes.isPresent()) {
            FilmesModel filme = filmesExistentes.get();

            filmesModel.setId(id);
            filme.setFilme(filmesModel.getFilme());
            filme.setGenero(filmesModel.getGenero());
            filme.setNotaFilme(filmesModel.getNotaFilme());

            return filmesRepository.save(filme);
        }

        return null;
    }

    public void deletarFilmes(Long id){
        filmesRepository.deleteById(id);
    }
    public void salvarMissao(FilmesModel filme) {
        filmesRepository.save(filme);  // Salva a missão no banco de dados
    }


}
