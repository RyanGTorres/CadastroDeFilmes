package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;


    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    //listar
    public List<PessoaDTO> listarPessoas(){
        List<PessoaModel> ninjas = pessoaRepository.findAll();
        return ninjas.stream()
                .map(pessoaMapper::map)
                .collect(Collectors.toList());
    }

    //listar por id
    public PessoaDTO listarPessoasPorId(Long id){
        Optional<PessoaModel> ninjaPorId = pessoaRepository.findById(id);
        return ninjaPorId.map(pessoaMapper::map)
                .orElse(null);
    }

    //criar
    public PessoaDTO criarPessoas(PessoaDTO pessoaDTO){
        PessoaModel ninja = pessoaMapper.map(pessoaDTO);
        ninja = pessoaRepository.save(ninja);
        return pessoaMapper.map(ninja);
    }

    //deletar
    public void deletarPessoaPorId(Long id){
        pessoaRepository.deleteById(id);
    }

    //alterar ninja
    public PessoaDTO alterarPessoa(Long id, PessoaDTO pessoaDTO){
        Optional<PessoaModel> ninjaExistente = pessoaRepository.findById(id);
        if (ninjaExistente.isPresent()){
            PessoaModel ninjaAtualizado = pessoaMapper.map(pessoaDTO);
            ninjaAtualizado.setId(id);
            PessoaModel ninjaSalvo = pessoaRepository.save(ninjaAtualizado);
            return pessoaMapper.map(ninjaSalvo);
        }
        return null;
    }


}
