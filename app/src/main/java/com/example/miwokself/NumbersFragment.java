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

public class NumbersFragment extends Fragment {

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



    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;// Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.activity_numbers, container, false);
        maudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Words> numberList = new ArrayList<>();
        numberList.add(0, new Words("lutti", "one", R.mipmap.number_one, R.raw.number_one));
        numberList.add(1, new Words("otiiko", "two", R.mipmap.number_two, R.raw.number_two));
        numberList.add(2, new Words("tolookosu", "three", R.mipmap.number_three, R.raw.number_three));
        numberList.add(3, new Words("oyyisa", "four", R.mipmap.number_four, R.raw.number_four));
        numberList.add(4, new Words("massokka", "five", R.mipmap.number_five, R.raw.number_five));
        numberList.add(5, new Words("temmokka", "six", R.mipmap.number_six, R.raw.number_six));
        numberList.add(6, new Words("kenekaku", "seven", R.mipmap.number_seven, R.raw.number_seven));
        numberList.add(7, new Words("kawinta", "eight", R.mipmap.number_eight, R.raw.number_eight));
        numberList.add(8, new Words("wo'e", "nine", R.mipmap.number_nine, R.raw.number_nine));
        numberList.add(9, new Words("na'aacha", "ten", R.mipmap.number_ten, R.raw.number_ten));

        RecyclerWordAdapter Adapter = new RecyclerWordAdapter(getActivity(), numberList, R.color.Numbers);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(Adapter);
        Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Words words = numberList.get(position);
                releaseMediaPlayer();
                int result = maudioManager.requestAudioFocus(audioListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result==maudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(getActivity(), words.getmSound());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mOncomplete);
            }
        }});
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer(){
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            maudioManager.abandonAudioFocus(audioListener);
        }
    }
    }
