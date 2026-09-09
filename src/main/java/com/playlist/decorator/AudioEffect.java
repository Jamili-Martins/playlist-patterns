package com.playlist.decorator;

public abstract class AudioEffect implements AudioTrack {

    protected final AudioTrack wrapped;

    public AudioEffect(AudioTrack wrapped) {
        if (wrapped == null) {
            throw new IllegalArgumentException();
        }
        this.wrapped = wrapped;
    }

    @Override
    public String getTitle() {
        return wrapped.getTitle();
    }
}