package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
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
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id){
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
    public void deletarMissoes(Long id){
        missoesService.deletarMissoes(id);
    }
}
