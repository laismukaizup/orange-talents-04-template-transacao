package br.com.zup.academy.lais.Transacao.Cartao;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumerFunction {
    void consume(ConsumerRecord<String,String> record);
}
