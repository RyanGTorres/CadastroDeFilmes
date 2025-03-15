package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.FilmesModel;
import dev.java10x.CadastroDeNinjas.Missoes.FilmesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pessoas/ui")
public class PessoaControllerUI {
    private final PessoaService pessoaService;
    private final FilmesService filmesService;

    public PessoaControllerUI(PessoaService pessoaService, FilmesService filmesService) {
        this.pessoaService = pessoaService;
        this.filmesService = filmesService;
    }

    @GetMapping("/listar")
    public String listarPessoas(Model model) {
        List<PessoaDTO> pessoas = pessoaService.listarPessoas();
        model.addAttribute("pessoas", pessoas);
        return "listarPessoas";
    }



    @GetMapping("/listar/{id}")
    public String listarPessoasPorId(@PathVariable Long id, Model model) {
        PessoaDTO pessoa = pessoaService.listarPessoasPorId(id);

        if (pessoa != null) {
            model.addAttribute("pessoa", pessoa);
            return "detalhesPessoas";
        }

        model.addAttribute("mensagem", "Ninja não encontrado!");
        return "listarPessoas";
    }


    @GetMapping("/alterar/{id}")
    public String editarPessoa(@PathVariable Long id, Model model) {
        PessoaDTO pessoa = pessoaService.listarPessoasPorId(id);
        List<FilmesModel> todosFilmes = filmesService.listarFilmes();
        model.addAttribute("pessoa", pessoa);
        model.addAttribute("todosFilmes", todosFilmes);
        return "editarPessoas";
    }


    @GetMapping("/deletar/{id}")
    public String deletarPessoaPorId(@PathVariable Long id) {
        pessoaService.deletarPessoaPorId(id);
        return "redirect:/pessoas/ui/listar";
    }

    @PostMapping("/salvar")
    public String salvarPessoa(@ModelAttribute PessoaDTO pessoa, RedirectAttributes redirectAttributes) {
        pessoaService.criarPessoas(pessoa);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/pessoas/ui/listar";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarPessoa(Model model) {
        model.addAttribute("pessoa", new PessoaDTO());
        return "adicionarPessoas";
    }
}
