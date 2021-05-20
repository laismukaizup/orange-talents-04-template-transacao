package br.com.zup.academy.lais.Transacao.Cartao;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class TransacaoService {

    public static void main(String[] args) {
        var transacaoService = new TransacaoService();
        try (var service = new KafkaService(TransacaoService.class.getSimpleName(), "TRANSACAO", transacaoService::parse)) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("----------------------------");
        System.out.println("processa transação " + record.key() + " / " + record.value() + " - " + record.partition());
    }
}
