package com.smartplug.test.webclient;

import com.smartplug.test.entity.MqttBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MqttWebClient {

    @Autowired
    private WebClient webClient;

    @Autowired
    private MqttApiProperties mqttApiProperties;

    public Mono<String> sendDataToApi(MqttBody body) {

        return webClient.post()
                .uri(mqttApiProperties.getBaseUrl()+mqttApiProperties.getSendData())
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }
}
