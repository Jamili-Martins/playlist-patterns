package com.playlist.decorator;

import java.util.Locale;

public class NoiseGateEffect extends AudioEffect {

    private final double threshold;

    public NoiseGateEffect(AudioTrack wrapped, double threshold) {
        super(wrapped);
        if (threshold < 0.0 || threshold > 1.0) {
            throw new IllegalArgumentException();
        }
        this.threshold = threshold;
    }

    public NoiseGateEffect(AudioTrack wrapped, int threshold) {
        this(wrapped, (double) threshold);
    }

    @Override
    public double[] getSamples() {
        double[] original = wrapped.getSamples();
        double[] processed = new double[original.length];
        for (int i = 0; i < original.length; i++) {
            if (Math.abs(original[i]) < threshold) {
                processed[i] = 0.0;
            } else {
                processed[i] = original[i];
            }
        }
        return processed;
    }

    @Override
    public String getEffectChain() {
        return wrapped.getEffectChain() + String.format(Locale.US, " -> noiseGate(%.2f)", threshold);
    }
}