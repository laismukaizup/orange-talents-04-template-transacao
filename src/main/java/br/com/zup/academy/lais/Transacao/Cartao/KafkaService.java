package br.com.zup.academy.lais.Transacao.Cartao;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

class KafkaService implements Closeable {
    private final KafkaConsumer<String, String> consumer;
    private ConsumerFunction parse;

    KafkaService(String groupId, String topic, ConsumerFunction parse){
        this.parse = parse;
        this.consumer = new KafkaConsumer<>(properties(groupId));
        consumer.subscribe(Collections.singleton(topic));

    }

    void run() {
        while (true) {
            var records = consumer.poll(Duration.ofSeconds(5));
            System.out.println("Encontrei : " + records.count() + " registros");
            if (!records.isEmpty()) {
                for (var record : records) {
                    parse.consume(record);
                }
            }
        }
    }
    private void parse(ConsumerRecord<String,String> record) {
        System.out.println("----------------------------");
        System.out.println("processa transação " + record.key() + " / " + record.value() + " - " + record.partition());

    }

    private static Properties properties(String groupId) {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        return properties;
    }

    @Override
    public void close() {
        consumer.close();
    }
}
