package com.playlist.decorator;

import java.util.Locale;

public class VolumeEffect extends AudioEffect {

    private final double factor;

    public VolumeEffect(AudioTrack wrapped, double factor) {
        super(wrapped);
        if (factor < 0.0) {
            throw new IllegalArgumentException();
        }
        this.factor = factor;
    }

    @Override
    public double[] getSamples() {
        double[] original = wrapped.getSamples();
        double[] processed = new double[original.length];
        for (int i = 0; i < original.length; i++) {
            double val = original[i] * factor;
            if (val > 1.0) val = 1.0;
            else if (val < -1.0) val = -1.0;
            processed[i] = val;
        }
        return processed;
    }

    @Override
    public String getEffectChain() {
        return wrapped.getEffectChain() + String.format(Locale.US, " -> volume(%.1f)", factor);
    }
}