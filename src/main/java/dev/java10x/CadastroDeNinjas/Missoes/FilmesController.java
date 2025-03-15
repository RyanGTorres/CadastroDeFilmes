package dev.java10x.CadastroDeNinjas.Missoes;

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
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "Usuario passa um id para lista a parti dele")
            @PathVariable Long id){
        FilmesModel missoesModel = filmesService.listarFilmesPorId(id);

        if (missoesModel != null){
            return ResponseEntity.ok(missoesModel);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão nao foi encontrada! id: "+id);
        }
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos as missoes", description = "Rota para mostrar ao ususario todas missoes")

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Mostrando missões!"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar missões")
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
    public ResponseEntity<?> alterarMissao(@PathVariable Long id, @RequestBody FilmesModel filmesModel){

        FilmesModel missaoModel = filmesService.alterarFilme(id, filmesModel);

        if (missaoModel != null){
            return ResponseEntity.ok(missaoModel);

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
    public void deletarMissoes(Long id){
        filmesService.deletarMissoes(id);
    }
}

