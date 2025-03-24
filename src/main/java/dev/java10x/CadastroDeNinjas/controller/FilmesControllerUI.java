package dev.java10x.CadastroDeNinjas.Filmes;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/filmes/ui")
public class FilmesControllerUI {
    private final FilmesService filmesService;

    public FilmesControllerUI(FilmesService filmesService) {
        this.filmesService = filmesService;
    }

    @GetMapping("/listar")
    public String listarFilmes(Model model) {
        List<FilmesModel> filmes = filmesService.listarFilmes();

        model.addAttribute("filmes", filmes);

        return "listarFilme";
    }

    @GetMapping("/listar/{id}")
    public String listarFilmesPorId(@PathVariable Long id, Model model) {
        FilmesModel filme = filmesService.listarFilmesPorId(id);

        if (filme != null){
            model.addAttribute("filme", filme);
            return "detalhesFilmes";
        }
        model.addAttribute("mensagem", "Missão não encontrada");
        return "listarFilmes";

    }

    @GetMapping("/alterar/{id}")
    public String alterarFilmesId(@PathVariable Long id, Model model) {
        FilmesModel filme = filmesService.listarFilmesPorId(id);
        if (filme != null) {
            model.addAttribute("filme", filme);
            return "editarFilmes";
        }
        model.addAttribute("mensagem", "Ninja não encontrado!");
        return "redirect:/filmes/ui/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarFilmesPorID(@PathVariable Long id){
        filmesService.deletarFilmes(id);
        return "redirect:/filmes/ui/listar";
    }

    // Exibir o formulário de adicionar missão
    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarFilmes(Model model) {
        model.addAttribute("filme", new FilmesModel());
        return "adicionarFilmes";
    }

    // Salvar a nova missão
    @PostMapping("/salvar")
    public String salvarFilmes(@ModelAttribute FilmesModel filme, RedirectAttributes redirectAttributes) {
        filmesService.criarFilme(filme);
        redirectAttributes.addFlashAttribute("mensagem", "Missão adicionada com sucesso!");
        return "redirect:/filmes/ui/listar";
    }


}
