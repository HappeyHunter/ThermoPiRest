package com.dromree.thermopi.gpio.simulation;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.trigger.GpioTrigger;

import java.util.Collection;

/**
 * Simulated GPIO controller for use when the GPIO is not available (not a Raspberry Pi)
 * Very stripped down.
 */
public class SimulatedGpioController implements GpioController {

    @Override
    public void export(PinMode mode, PinState defaultState, GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void export(PinMode mode, GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isExported(GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unexport(Pin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unexport(GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unexportAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMode(PinMode mode, GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PinMode getMode(GpioPin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isMode(PinMode mode, GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPullResistance(PinPullResistance resistance, GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PinPullResistance getPullResistance(GpioPin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPullResistance(PinPullResistance resistance, GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void high(GpioPinDigitalOutput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isHigh(GpioPinDigital... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void low(GpioPinDigitalOutput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLow(GpioPinDigital... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setState(PinState state, GpioPinDigitalOutput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setState(boolean state, GpioPinDigitalOutput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isState(PinState state, GpioPinDigital... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PinState getState(GpioPinDigital pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void toggle(GpioPinDigitalOutput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void pulse(long milliseconds, GpioPinDigitalOutput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(double value, GpioPinAnalogOutput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getValue(GpioPinAnalog pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addListener(GpioPinListener listener, GpioPinInput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addListener(GpioPinListener[] listeners, GpioPinInput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeListener(GpioPinListener listener, GpioPinInput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeListener(GpioPinListener[] listeners, GpioPinInput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAllListeners() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addTrigger(GpioTrigger trigger, GpioPinInput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addTrigger(GpioTrigger[] triggers, GpioPinInput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeTrigger(GpioTrigger trigger, GpioPinInput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeTrigger(GpioTrigger[] triggers, GpioPinInput... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAllTriggers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, String name, PinMode mode, PinPullResistance resistance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, PinMode mode, PinPullResistance resistance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, String name, PinMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, PinMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, String name, PinMode mode, PinPullResistance resistance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, PinMode mode, PinPullResistance resistance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, String name, PinMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, PinMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin, String name, PinPullResistance resistance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin, PinPullResistance resistance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalInput provisionDigitalInputPin(Pin pin, String name, PinPullResistance resistance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalInput provisionDigitalInputPin(Pin pin, PinPullResistance resistance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalInput provisionDigitalInputPin(Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalInput provisionDigitalInputPin(Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin, String name, PinState defaultState) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin, PinState defaultState) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin, String name, PinState defaultState) {
        return new SimulatedGpioPinDigitalOutput(defaultState, pin, name);
    }

    @Override
    public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin, PinState defaultState) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogInput provisionAnalogInputPin(GpioProvider provider, Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogInput provisionAnalogInputPin(GpioProvider provider, Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogInput provisionAnalogInputPin(Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogInput provisionAnalogInputPin(Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin, String name, double defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin, double defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin, String name, double defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin, double defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin, String name, int defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin, int defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionPwmOutputPin(Pin pin, String name, int defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionPwmOutputPin(Pin pin, int defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionPwmOutputPin(Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionPwmOutputPin(Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider provider, Pin pin, String name, int defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider provider, Pin pin, int defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider provider, Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider provider, Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin pin, String name, int defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin pin, int defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin pin, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPin provisionPin(GpioProvider provider, Pin pin, String name, PinMode mode, PinState defaultState) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPin provisionPin(GpioProvider provider, Pin pin, String name, PinMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPin provisionPin(GpioProvider provider, Pin pin, PinMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPin provisionPin(Pin pin, String name, PinMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPin provisionPin(Pin pin, PinMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setShutdownOptions(GpioPinShutdown options, GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setShutdownOptions(Boolean unexport, GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setShutdownOptions(Boolean unexport, PinState state, GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setShutdownOptions(Boolean unexport, PinState state, PinPullResistance resistance, GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setShutdownOptions(Boolean unexport, PinState state, PinPullResistance resistance, PinMode mode, GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<GpioPin> getProvisionedPins() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPin getProvisionedPin(Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPin getProvisionedPin(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unprovisionPin(GpioPin... pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isShutdown() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException();
    }
}
