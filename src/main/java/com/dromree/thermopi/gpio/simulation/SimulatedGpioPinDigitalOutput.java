package com.dromree.thermopi.gpio.simulation;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListener;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Simulated GPIO output for use when the GPIO is not available (not a Raspberry Pi)
 * Very stripped down.
 */
public class SimulatedGpioPinDigitalOutput implements GpioPinDigitalOutput {

    private PinState currentState;
    private Pin pin;
    private String name;

    public SimulatedGpioPinDigitalOutput(PinState currentState, Pin pin, String name) {
        this.currentState = currentState;
        this.pin = pin;
        this.name = name;
    }

    @Override
    public void high() {
        currentState = PinState.HIGH;
    }

    @Override
    public void low() {
        currentState = PinState.LOW;
    }

    @Override
    public void toggle() {
        currentState = PinState.LOW.equals(currentState) ? PinState.HIGH: PinState.HIGH;
    }

    @Override
    public Future<?> blink(long delay) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> blink(long delay, PinState blinkState) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> blink(long delay, long duration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> blink(long delay, long duration, PinState blinkState) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> pulse(long duration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> pulse(long duration, Callable<Void> callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> pulse(long duration, boolean blocking) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> pulse(long duration, boolean blocking, Callable<Void> callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> pulse(long duration, PinState pulseState) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> pulse(long duration, PinState pulseState, Callable<Void> callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> pulse(long duration, PinState pulseState, boolean blocking) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> pulse(long duration, PinState pulseState, boolean blocking, Callable<Void> callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setState(PinState state) {
        currentState = state;
    }

    @Override
    public void setState(boolean state) {
        currentState = state ? PinState.HIGH: PinState.HIGH;
    }

    @Override
    public boolean isHigh() {
        return PinState.HIGH.equals(currentState);
    }

    @Override
    public boolean isLow() {
        return PinState.LOW.equals(currentState);
    }

    @Override
    public PinState getState() {
        return currentState;
    }

    @Override
    public boolean isState(PinState state) {
        return currentState.equals(state);
    }

    @Override
    public GpioProvider getProvider() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pin getPin() {
        return pin;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setTag(Object tag) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getTag() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setProperty(String key, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasProperty(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getProperty(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, String> getProperties() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeProperty(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearProperties() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void export(PinMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void export(PinMode mode, PinState defaultState) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unexport() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isExported() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMode(PinMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PinMode getMode() {
        return PinMode.DIGITAL_OUTPUT;
    }

    @Override
    public boolean isMode(PinMode mode) {
        return PinMode.DIGITAL_OUTPUT.equals(mode);
    }

    @Override
    public void setPullResistance(PinPullResistance resistance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PinPullResistance getPullResistance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPullResistance(PinPullResistance resistance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<GpioPinListener> getListeners() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addListener(GpioPinListener... listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addListener(List<? extends GpioPinListener> listeners) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasListener(GpioPinListener... listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeListener(GpioPinListener... listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeListener(List<? extends GpioPinListener> listeners) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAllListeners() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GpioPinShutdown getShutdownOptions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setShutdownOptions(GpioPinShutdown options) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setShutdownOptions(Boolean unexport) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setShutdownOptions(Boolean unexport, PinState state) {}

    @Override
    public void setShutdownOptions(Boolean unexport, PinState state, PinPullResistance resistance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setShutdownOptions(Boolean unexport, PinState state, PinPullResistance resistance, PinMode mode) {
        throw new UnsupportedOperationException();
    }
}
