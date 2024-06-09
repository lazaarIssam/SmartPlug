package com.smartplug.test.service;

import com.smartplug.test.entity.MqttBody;
import com.smartplug.test.webclient.MqttWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MqttService {

    @Autowired
    private MqttWebClient mqttWebClient;

    public Mono<String> changeState (MqttBody body){
        return mqttWebClient.sendDataToApi(body);
    }
}
