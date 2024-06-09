package com.smartplug.test.webclient;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("mqtt-api")
@Data
public class MqttApiProperties {

    private String baseUrl;
    private String sendData;
}
