package com.example.piano.model;

import android.graphics.RectF;

public class Key {
    public int sound;
    public RectF rect;
    public boolean down;

    public Key(int sound, RectF rect, boolean down) {
        this.sound = sound;
        this.rect = rect;
        this.down = down;
    }


}
