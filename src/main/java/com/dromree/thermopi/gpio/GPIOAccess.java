package com.dromree.thermopi.gpio;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Component;

@Component
public class GPIOAccess {

    private final GpioController gpio = GpioFactory.getInstance();
    private final GpioPinDigitalOutput boilerOutput = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "BoilerOutput", PinState.LOW);
    private final GpioPinDigitalOutput valveOutput = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "ValveOutput", PinState.LOW);

    public GPIOAccess() {
        boilerOutput.setShutdownOptions(true, PinState.LOW);
        valveOutput.setShutdownOptions(true, PinState.LOW);
    }

    public void setBoilerState(boolean active) {
        if(PinState.LOW.equals(boilerOutput.getState()) && active) {
            boilerOutput.high();
        } else if(PinState.HIGH.equals(boilerOutput.getState()) && !active) {
            boilerOutput.low();
        }
    }

    public void setValveState(boolean active) {
        if(PinState.LOW.equals(valveOutput.getState()) && active) {
            valveOutput.high();
        } else if(PinState.HIGH.equals(valveOutput.getState()) && !active) {
            valveOutput.low();
        }
    }


}
