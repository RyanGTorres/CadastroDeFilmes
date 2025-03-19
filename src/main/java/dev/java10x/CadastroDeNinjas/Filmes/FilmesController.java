package dev.java10x.CadastroDeNinjas.Filmes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class FilmesController {

    private FilmesService filmesService;

    public FilmesController(FilmesService filmesService) {
        this.filmesService = filmesService;
    }

    //GET -- Mandar um requisição para mostrar missoes
    @GetMapping("/listar/{id}")

    @Operation(summary = "Lista missoes por id", description = "Rota uma missao pelo seu id")

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro encontrar missao")
    })
    public ResponseEntity<?> listarFilmesPorId(
            @Parameter(description = "Usuario passa um id para lista a parti dele")
            @PathVariable Long id){
        FilmesModel filmes = filmesService.listarFilmesPorId(id);

        if (filmes != null){
            return ResponseEntity.ok(filmes);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão nao foi encontrada! id: "+id);
        }
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os filmes", description = "Rota para mostrar ao usuario todos os filmes")

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Mostrando filmes!"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar Filmes")
    })
    public ResponseEntity<List<FilmesModel>> listarFilmes(){
        List<FilmesModel> filmes = filmesService.listarFilmes();
        return ResponseEntity.ok(filmes);
    }


    //POST -- Mandar um requisição para criar missoes
    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova missão", description = "Cria uma nova missao no banco de dados")

    @ApiResponses(value= {
            @ApiResponse(responseCode = "201", description = "Missao criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missao")
    })
    public ResponseEntity<String> criarFilmes(FilmesModel filmes){
        FilmesModel filmesModel = filmesService.criarFilme(filmes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("A Missão foi criada com sucesso: "+filmesModel.getFilme());
    }

    //PUT -- Mandar um requisição para alterar missoes
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarFilme(@PathVariable Long id, @RequestBody FilmesModel filmesModel){

        FilmesModel filme = filmesService.alterarFilme(id, filmesModel);

        if (filme != null){
            return ResponseEntity.ok(filme);

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão de id não foi encontrada: "+id);
        }
    }

    //DELETE -- Mandar um requisição para deletar missoes
    @DeleteMapping("/deletar/{id}")

    @Operation(summary = "Deleta missao por id", description = "Rota a missao que sera excluida passando o id")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Missao deletada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro ao deletar missao")
    })
    public ResponseEntity<String> deletarFilmes(@PathVariable Long id){
        filmesService.deletarFilmes(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("A missão foi deletada com sucesso. ID: " + id);
    }
}

