package com.dromree.thermopi.gpio;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Component;

/**
 * Controls access to the GPIO pins
 *
 */
@Component
public class GPIOAccess {

    private GpioController gpio;
    private GpioPinDigitalOutput boilerOutput;
    private GpioPinDigitalOutput valveOutput;

    public GPIOAccess(GpioController gpio) {
        this.gpio = gpio;

        boilerOutput = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "BoilerOutput", PinState.LOW);
        valveOutput = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "ValveOutput", PinState.LOW);

        boilerOutput.setShutdownOptions(true, PinState.LOW);
        valveOutput.setShutdownOptions(true, PinState.LOW);
    }

    /**
     * Updates the boiler state if it is changing
     *
     * @param active
     */
    public void setBoilerState(boolean active) {
        if(PinState.LOW.equals(boilerOutput.getState()) && active) {
            boilerOutput.high();
        } else if(PinState.HIGH.equals(boilerOutput.getState()) && !active) {
            boilerOutput.low();
        }
    }

    /**
     * Updates the valve state if it is changing
     *
     * @param active
     */
    public void setValveState(boolean active) {
        if(PinState.LOW.equals(valveOutput.getState()) && active) {
            valveOutput.high();
        } else if(PinState.HIGH.equals(valveOutput.getState()) && !active) {
            valveOutput.low();
        }
    }


}
