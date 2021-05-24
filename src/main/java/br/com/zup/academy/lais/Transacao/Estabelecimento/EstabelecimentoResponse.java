package br.com.zup.academy.lais.Transacao.Cartao;

public class EstabelecimentoResponse {
    public String nome;
    public String cidade;
    public String endereco;

    public Estabelecimento toModel() {
        return new Estabelecimento(nome, cidade, endereco);
    }
}
