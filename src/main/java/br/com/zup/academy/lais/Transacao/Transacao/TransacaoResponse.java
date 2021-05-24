package br.com.zup.academy.lais.Transacao.Cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//@Entity
public class TransacaoResponse {
    private String id;
    private BigDecimal valor;
    private LocalDateTime efetivadaEm;
    private CartaoResponse cartao;
    private EstabelecimentoResponse estabelecimento;

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public CartaoResponse getCartao() {
        return cartao;
    }

    public EstabelecimentoResponse getEstabelecimento() {
        return estabelecimento;
    }


    public Transacao toModel(Cartao cartao,Estabelecimento estabelecimento) {

        return new Transacao(id, valor, efetivadaEm, cartao, estabelecimento);
    }
}
