package com.example.miwokself;

import android.media.MediaPlayer;

public class Words {
    private String mMiwokTrans;
    private String mDefaultTrans;
    private int mResourceID = no_Picture_Provided;
    private static int no_Picture_Provided = -1;
    private int mSound;
    private MediaPlayer mMediaPlayer;

    public Words(String MiwokTrans, String DefaultTrans, int sound){
        mMiwokTrans = MiwokTrans;
        mDefaultTrans = DefaultTrans;
        mSound = sound;
    }

    public Words(String MiwokTrans, String DefaultTrans, int image, int sound){
        mMiwokTrans = MiwokTrans;
        mDefaultTrans = DefaultTrans;
        mResourceID = image;
        mSound = sound;
    }

    public String getMiwokTrans() {
        return mMiwokTrans;
    }

    public String getmDefaultTrans() {
        return mDefaultTrans;
    }

    public int getmResourceID(){return mResourceID;}
    public boolean hasImage()
    {
        return mResourceID!=no_Picture_Provided;
    }

    public int getmSound() {
        return mSound;
    }
}
