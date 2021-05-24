package br.com.zup.academy.lais.Transacao.Cartao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    private String id;
    private BigDecimal valor;
    private LocalDateTime efetivadaEm;
    @ManyToOne
    private Cartao cartao;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Estabelecimento estabelecimento;

    @Deprecated
    public Transacao(){

    }
    public Transacao(String id, BigDecimal valor, LocalDateTime efetivadaEm, Cartao cartao, Estabelecimento estabelecimento) {
        this.id = id;
        this.valor = valor;
        this.efetivadaEm = efetivadaEm;
        this.cartao = cartao;
        this.estabelecimento = estabelecimento;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

}
