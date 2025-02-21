package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    //adicionar ninja
    @PostMapping("/criar")
    public String criarNinja(){return "Ninja criado!";}

    //mostrar todos os ninjas
    @GetMapping("/todos")
    public String mostrarTodosOsNinjas(){return "Mostrar ninja! ";}

    //mostrar todos ninjas por id
    @GetMapping("/todosid")
    public String mostrarTodosOsNinjasPorId(){return "Mostrar ninja! ID ";}

    //alterar dados dos ninjas
    @PutMapping("/alterarID")
    public String alterarNinjasPorId(){return "Alterar ninja! ID ";}

    //deletar
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId(){return "Ninja deletado por id!";}
}
