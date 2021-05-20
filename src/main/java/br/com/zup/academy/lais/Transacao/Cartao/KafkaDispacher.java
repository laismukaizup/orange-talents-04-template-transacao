package br.com.zup.academy.lais.Transacao.Cartao;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

class KafkaDispacher<T> implements Closeable {
    private final KafkaProducer<String, T> producer;

    KafkaDispacher() {
        this.producer = new KafkaProducer<>(properties());
    }

    private Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        return properties;
    }

    void send(String topico, String key, T value) throws ExecutionException, InterruptedException {
        var record = new ProducerRecord<>(topico, key, value);
        Callback callback = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            } else {
                System.out.println("Sucesso enviado " + data.topic() + "::: partição: " + data.partition() + " / offset: " +
                        data.offset() + " / timestamp: " + data.timestamp());
            }
        };
        producer.send(record,callback).get();
    }

    @Override
    public void close(){
        producer.close();
    }
}
