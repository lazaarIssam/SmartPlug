package com.smartplug.test.service;

import com.smartplug.test.entity.Message;
import com.smartplug.test.entity.MqttBody;
import com.smartplug.test.entity.OnOffBody;
import com.smartplug.test.entity.SmartPlug;
import com.smartplug.test.repository.SmartPlugRepository;
import com.smartplug.test.utils.utilsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class SmartPlugService {

    @Autowired
    private SmartPlugRepository smartPlugRepository;

    @Autowired
    private MqttService mqttService;

    public Flux<SmartPlug> allSmartPlugs(){ return  smartPlugRepository.findAll();}

    public Mono<ResponseEntity<SmartPlug>> smartPlugDetails(Integer id) {
        return smartPlugRepository.findById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    public Mono<String> turnOnOffSmartPlug (OnOffBody body){
        return smartPlugRepository.findById(body.getId())
                .flatMap(smartPlug -> {
                    smartPlug.setState(body.getState());
                    return smartPlugRepository.save(smartPlug);
                })
                .flatMap(data -> {
                    MqttBody mqttBody = new MqttBody();
                    mqttBody.setTopic(utilsConstants.TOPIC+data.getDeviceName()+utilsConstants.SET);
                    mqttBody.setMessage(new Message(body.getState()));
                    return mqttService.changeState(mqttBody);
                })
                .map(savedSmartPlug -> "State updated successfully");
    }
}
