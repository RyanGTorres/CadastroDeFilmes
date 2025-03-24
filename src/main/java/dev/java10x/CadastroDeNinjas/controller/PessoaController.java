package dev.java10x.CadastroDeNinjas.Pessoa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas a quem chama ela")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    //adicionar ninja
   @PostMapping("/criar")
   @Operation(summary = "Cria um novo ninja", description = "Cria um novo ninja e insere no banco de dados")

   @ApiResponses(value= {
           @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso!"),
           @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
   })
    public ResponseEntity<String> criarPessoa(@RequestBody PessoaDTO pessoa){
        PessoaDTO novaPessoa = pessoaService.criarPessoas(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja Criado com sucesso: "+novaPessoa.getNome() + " (ID): "+ novaPessoa.getId());
    }

    //mostrar todos os ninjas
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = "Mostra para o usuario todos os ninjas")

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Mostrando Ninjas!"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar os ninja")
    })
    public ResponseEntity<List<PessoaDTO>> listarPessoa(){
        List<PessoaDTO> pessoa = pessoaService.listarPessoas();
        return ResponseEntity.ok(pessoa);
    }

    //mostrar todos ninjas por id
    @GetMapping("/listarid/{id}")

    @Operation(summary = "Lista ninja por id", description = "Rota um ninja pelo id")

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro ao listar o ninja")
    })

    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id){
        PessoaDTO ninjasPorID = pessoaService.listarPessoasPorId(id);

        if (ninjasPorID != null) {
            return ResponseEntity.ok(ninjasPorID);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja com o id: " + id + " não existe nos nossos registros");
    }
    //alterar dados dos ninjas
    @PutMapping("/alterarid/{id}")
    @Operation(summary = "Altera ninja por id", description = "Aletera um ninja pelo id")

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro ao alterar o ninja")
    })
    public ResponseEntity<?> alterarPessoa(
            @Parameter(description = "Usuario manda o id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisção")
            @RequestBody PessoaDTO ninjaAtualizado){

       PessoaDTO pessoaDTO = pessoaService.alterarPessoa(id, ninjaAtualizado);

       if (pessoaDTO != null){
           return ResponseEntity.ok(pessoaDTO);
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("O ninja não foi encontado: id:"+id);
       }
    }

    //deletar
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta o ninja por id", description = "Rota o ninja que sera excluido passando o id")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro ao deletar o ninja")
    })
    public ResponseEntity<String> deletarNinjaPorId(
            @Parameter(description = "Usuario encaminha o id do ninja que sera excluido")
            @PathVariable Long id){
        if (pessoaService.listarPessoasPorId(id) != null){
            pessoaService.deletarPessoaPorId(id);
            return ResponseEntity.ok("Ninja deletado com sucesso! O id: "+id+ " foi deltado com sucesso");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o id: "+id+ " não foi encontado");
        }
    }
}
