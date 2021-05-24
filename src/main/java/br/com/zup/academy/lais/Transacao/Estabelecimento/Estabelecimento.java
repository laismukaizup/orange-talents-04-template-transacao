package br.com.zup.academy.lais.Transacao.Cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estabelecimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public String cidade;
    public String endereco;

    @Deprecated
    public Estabelecimento(){

    }

    public Estabelecimento(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }
}
