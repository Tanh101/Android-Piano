package com.example.piano.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.SparseArray;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class SoundManager {
    private SoundPool mSoundPool;
    private SparseArray mSoundPoolMap;
    private Context mContext;

    private static final int MAX_STREAM = 10;
    private static final int STOP_DELAY_MILIS = 10000;
    private Handler mHandler;

    private static SoundManager instance = null;

    public SoundManager(){
        mSoundPool = new SoundPool(MAX_STREAM, AudioManager.STREAM_MUSIC, 0);

        mSoundPoolMap = new SparseArray();
        mHandler = new Handler() {
            @Override
            public void publish(LogRecord logRecord) {

            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        };
    }

    public static SoundManager getInstance(){
        if(instance == null){
            instance = new SoundManager();
        }
        return instance;
    }

    public void init(Context context) {
        this.mContext = context;

    }
}
