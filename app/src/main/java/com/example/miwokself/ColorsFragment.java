package com.example.miwokself;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ColorsFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager maudioManager;
    private AudioManager.OnAudioFocusChangeListener audioListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mOncomplete = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };



    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;// Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.activity_colors, container, false);
        maudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Words> colorList = new ArrayList<>();
        colorList.add(0, new Words("weṭeṭṭi","red", R.mipmap.color_red, R.raw.color_red));
        colorList.add(1, new Words("chokokki", "green", R.mipmap.family_mother, R.raw.color_green));
        colorList.add(2, new Words("ṭakaakki", "brown", R.mipmap.family_son,  R.raw.color_brown));
        colorList.add(3, new Words("ṭopoppi", "gray", R.mipmap.family_daughter, R.raw.color_gray));
        colorList.add(4, new Words("kululli", "black", R.mipmap.family_older_brother, R.raw.color_black));
        colorList.add(5, new Words("kelelli", "white", R.mipmap.family_younger_brother, R.raw.color_white));
        colorList.add(6, new Words("ṭopiisә", "dusty yellow", R.mipmap.family_older_sister, R.raw.color_dusty_yellow));
        colorList.add(7, new Words("chiwiiṭә", "mustard yellow", R.mipmap.family_younger_sister, R.raw.color_mustard_yellow));

        RecyclerWordAdapter Adapter = new RecyclerWordAdapter(getActivity(), colorList, R.color.Colors);
        RecyclerView recyclerView = rootView.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(Adapter);


            Adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    Words words = colorList.get(position);
                    releaseMediaPlayer();
                    int result = maudioManager.requestAudioFocus(audioListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if (result==maudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                        mediaPlayer = MediaPlayer.create(getActivity(), words.getmSound());
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(mOncomplete);
                    }
                }
            });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            maudioManager.abandonAudioFocus(audioListener);
        }
    }
}
