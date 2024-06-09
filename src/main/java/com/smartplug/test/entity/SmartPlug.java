package com.smartplug.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("smart_plug")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmartPlug {

    @Id
    private Integer id;
    private String deviceName;
    private String currentPower;
    private String totalPowerConsumption;
    private String state;
}
