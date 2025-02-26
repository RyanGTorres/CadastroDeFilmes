package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
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
    public MissoesModel listarNinjasPorId(@PathVariable Long id){
        return missoesService.listarNinjasPorId(id);
    }

    //POST -- Mandar um requisição para criar missoes
    @PostMapping("/criar")
    public MissoesModel criarMissoes(MissoesModel missoes){
        return missoesService.criarMissoes(missoes);
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
