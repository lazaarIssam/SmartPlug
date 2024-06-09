package com.smartplug.test.controller;

import com.smartplug.test.entity.MqttBody;
import com.smartplug.test.entity.OnOffBody;
import com.smartplug.test.entity.SmartPlug;
import com.smartplug.test.service.MqttService;
import com.smartplug.test.service.SmartPlugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/smartPlug")
public class SmartPlugController {

    @Autowired
    private SmartPlugService smartPlugService;

    @Autowired
    private MqttService mqttService;

    @GetMapping
    public Flux<SmartPlug> getAllSmartPlugs(){
        return smartPlugService.allSmartPlugs();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<SmartPlug>> getSmartPlugDetails(@PathVariable Integer id){
        return smartPlugService.smartPlugDetails(id);
    }

    @PostMapping("/onOff")
    public Mono<String> turnOnOffSmartPlugState (@RequestBody OnOffBody body){
        return smartPlugService.turnOnOffSmartPlug(body);
    }

    @PostMapping("/sendDataMqtt")
    public Mono<String> changeStateSmartPlug (@RequestBody MqttBody body){
        return mqttService.changeState(body);
    }
}
