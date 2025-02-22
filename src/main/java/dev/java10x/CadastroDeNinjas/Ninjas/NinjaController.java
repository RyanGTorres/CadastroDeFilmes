package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    //adicionar ninja
    @PostMapping("/criar")
    public String criarNinja(){return "Ninja criado!";}

    //mostrar todos os ninjas
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }

    //mostrar todos ninjas por id
    @GetMapping("/todosID")
    public String mostrarTodosOsNinjasPorId(){return "Mostrar ninja! ID ";}

    //alterar dados dos ninjas
    @PutMapping("/alterarID")
    public String alterarNinjasPorId(){return "Alterar ninja! ID ";}

    //deletar
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId(){return "Ninja deletado por id!";}
}
