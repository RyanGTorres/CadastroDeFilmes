package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/missoes/ui")
public class FilmesControllerUI {
    private final FilmesService filmesService;

    public FilmesControllerUI(FilmesService filmesService) {
        this.filmesService = filmesService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        List<FilmesModel> missao = filmesService.listarFilmes();

        model.addAttribute("missoes", missao);

        return "listarMissoes";
    }

    @GetMapping("/listar/{id}")
    public String listarMissoesPorId(@PathVariable Long id, Model model) {
        FilmesModel missao = filmesService.listarFilmesPorId(id);

        if (missao != null){
            model.addAttribute("missao", missao);
            return "detalhesMissoes";
        }
        model.addAttribute("mensagem", "Missão não encontrada");
        return "listarMissoes";

    }

    @GetMapping("/alterar/{id}")
    public String alterarNinjaId(@PathVariable Long id, Model model) {
        FilmesModel missao = filmesService.listarFilmesPorId(id);
        if (missao != null) {
            model.addAttribute("missao", missao);
            return "editarMissoes";
        }
        model.addAttribute("mensagem", "Ninja não encontrado!");
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissaoPorID(@PathVariable Long id){
        filmesService.deletarMissoes(id);
        return "redirect:/missoes/ui/listar";
    }

    // Exibir o formulário de adicionar missão
    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarMissao(Model model) {
        model.addAttribute("missao", new FilmesModel());
        return "adicionarMissoes";
    }

    // Salvar a nova missão
    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute FilmesModel missao, RedirectAttributes redirectAttributes) {
        filmesService.criarFilme(missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missão adicionada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }


}
