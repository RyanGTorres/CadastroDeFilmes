package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {
    public PessoaModel map(PessoaDTO pessoaDTO){
        PessoaModel pessoaModel = new PessoaModel();

        pessoaModel.setId(pessoaDTO.getId());
        pessoaModel.setNome(pessoaDTO.getNome());
        pessoaModel.setEmail(pessoaDTO.getEmail());
        pessoaModel.setIdade(pessoaDTO.getIdade());
        pessoaModel.setFilmes(pessoaDTO.getFilmes());
        pessoaModel.setImgUrl(pessoaDTO.getImgUrl());
        pessoaModel.setDiretorFavorito(pessoaDTO.getDiretorFavorito());

        return pessoaModel;
    }

    public PessoaDTO map (PessoaModel pessoaModel){
        PessoaDTO pessoaDTO = new PessoaDTO();

        pessoaDTO.setId(pessoaModel.getId());
        pessoaDTO.setNome(pessoaModel.getNome());
        pessoaDTO.setEmail(pessoaModel.getEmail());
        pessoaDTO.setIdade(pessoaModel.getIdade());
        pessoaDTO.setFilmes(pessoaModel.getFilmes());
        pessoaDTO.setImgUrl(pessoaModel.getImgUrl());
        pessoaDTO.setDiretorFavorito(pessoaModel.getDiretorFavorito());

        return pessoaDTO;
    }
}
