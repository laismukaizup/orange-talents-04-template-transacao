package br.com.zup.academy.lais.Transacao.Cartao;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @PostMapping
    public String cadastrar(@RequestBody @Validated CartaoRequest cartaoRequest) throws ExecutionException, InterruptedException {
        criaMensagem(cartaoRequest);
        return "ok";
    }

    public void criaMensagem(CartaoRequest cartaoRequest) throws ExecutionException, InterruptedException {
        try (var TransacaoDispacher = new KafkaDispacher<CartaoRequest>()) {
            TransacaoDispacher.send("TRANSACAO",  cartaoRequest.id, cartaoRequest);
        }
    }
}