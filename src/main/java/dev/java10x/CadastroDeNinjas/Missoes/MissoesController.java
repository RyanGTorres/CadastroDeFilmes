package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    //GET -- Mandar um requisição para mostrar missoes
    @GetMapping("/mostar")
    public String mostrarMissoes(){
        return "Missao mostrada com sucesso";
    }

    //POST -- Mandar um requisição para criar missoes
    @PostMapping("/criar")
    public String criarMissao(){
        return "Missao criada com sucesso";
    }

    //PUT -- Mandar um requisição para alterar missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso!";
    }

    //DELETE -- Mandar um requisição para deletar missoes
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missao deletada com sucesso!";
    }
}
