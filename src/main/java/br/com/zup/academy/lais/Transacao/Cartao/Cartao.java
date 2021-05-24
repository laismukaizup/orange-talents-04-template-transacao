package br.com.zup.academy.lais.Transacao.Cartao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cartao {
    @Id
    public String id;

    public String email;

    public Cartao(String id, String email) {
        this.id = id;
        this.email = email;
    }
}
