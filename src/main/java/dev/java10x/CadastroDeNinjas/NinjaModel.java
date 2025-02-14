package dev.java10x.CadastroDeNinjas;


import jakarta.persistence.*;

//Entity transforma um classe em uma entidade do BD
//JPA = Java Persistence API
@Entity
@Table(name = "tb_cadastro")
public class NinjaModel {
<<<<<<< HEAD
    @Idgi
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String email;
    int idade;
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String nome;
    private String email;
    private int idade;
>>>>>>> 5ba4812 (Cria uma entidade no banco de dados a partir do NinjaModel)

    public NinjaModel() {
    }

    public NinjaModel(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
