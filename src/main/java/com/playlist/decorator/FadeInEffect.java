package com.playlist.decorator;

public class FadeInEffect extends AudioEffect {

    private final double durationSeconds;

    public FadeInEffect(AudioTrack wrapped, double durationSeconds) {
        super(wrapped);
        if (durationSeconds < 0.0) {
            throw new IllegalArgumentException();
        }
        this.durationSeconds = durationSeconds;
    }

    public FadeInEffect(AudioTrack wrapped, int durationSeconds) {
        this(wrapped, (double) durationSeconds);
    }

    @Override
    public double[] getSamples() {
        double[] original = wrapped.getSamples();
        double[] processed = new double[original.length];
        int total = original.length;
        int fadeLength = (int) Math.min(total, durationSeconds);

        for (int i = 0; i < total; i++) {
            if (i < fadeLength && fadeLength > 0) {
                double factor = (double) i / fadeLength;
                processed[i] = original[i] * factor;
            } else {
                processed[i] = original[i];
            }
        }
        return processed;
    }

    @Override
    public String getEffectChain() {
        if (durationSeconds == (long) durationSeconds) {
            return wrapped.getEffectChain() + String.format(" -> fadeIn(%d)", (long) durationSeconds);
        }
        return wrapped.getEffectChain() + String.format(" -> fadeIn(%.1f)", durationSeconds);
    }
}