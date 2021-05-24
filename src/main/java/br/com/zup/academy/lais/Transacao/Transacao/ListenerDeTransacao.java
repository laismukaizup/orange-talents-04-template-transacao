package br.com.zup.academy.lais.Transacao.Cartao;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ListenerDeTransacao {

    @PersistenceContext
    EntityManager manager;

    @Transactional
    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoResponse transacaoResponse) {
        Cartao cartao = manager.find(Cartao.class, transacaoResponse.getCartao().getId());
        Estabelecimento estabelecimento = transacaoResponse.getEstabelecimento().toModel();
        Transacao transacao= transacaoResponse.toModel(cartao, estabelecimento);
        manager.persist(transacao);
        System.out.println("salvo");
    }
}
