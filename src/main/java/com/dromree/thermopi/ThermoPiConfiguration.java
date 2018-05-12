package com.dromree.thermopi;

import com.dromree.thermopi.gpio.simulation.SimulatedGpioController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean configuration for ThermoPi
 *
 */
@Configuration
public class ThermoPiConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ThermoPiConfiguration.class.getName());

    @Bean
    public GpioController getGpioController() {
        GpioController gpio;

        if(ThermoPiConstants.ARM_ARCH.equals(System.getProperty(ThermoPiConstants.OS_ARCH).toLowerCase())) {
            gpio = GpioFactory.getInstance();
        } else {
            logger.debug("Non-ARM architecture detected. Simulating GPIO.");
            gpio = new SimulatedGpioController();
        }

        return gpio;
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper;
    }

}
