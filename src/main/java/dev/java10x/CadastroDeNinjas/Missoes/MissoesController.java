package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
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
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
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
        MissoesModel missoesModel = missoesService.listarNinjasPorId(id);

        if (missoesModel != null){
            return ResponseEntity.ok(missoesModel);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão nao foi encontrada! id: "+id);
        }
    }

    //POST -- Mandar um requisição para criar missoes
    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova missão", description = "Cria uma nova missao no banco de dados")

    @ApiResponses(value= {
            @ApiResponse(responseCode = "201", description = "Missao criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missao")
    })
    public ResponseEntity<String> criarMissoes(MissoesModel missoes){
        MissoesModel missoesModel = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("A Missão foi criada com sucesso: "+missoesModel.getNomeMissao());
    }

    //PUT -- Mandar um requisição para alterar missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso!";
    }

    //DELETE -- Mandar um requisição para deletar missoes
    @DeleteMapping("/deletar/{id}")

    @Operation(summary = "Deleta missao por id", description = "Rota a missao que sera excluida passando o id")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Missao deletada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro ao deletar missao")
    })
    public void deletarMissoes(Long id){
        missoesService.deletarMissoes(id);
    }
}

