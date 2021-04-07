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

public class FamilyFragment extends Fragment {

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



    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;// Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.activity_family, container, false);
        maudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Words> FamilyList = new ArrayList<>();
        FamilyList.add(0, new Words("әpә","father", R.mipmap.family_father, R.raw.family_father));
        FamilyList.add(1, new Words("әṭa", "mother", R.mipmap.family_mother, R.raw.family_mother));
        FamilyList.add(2, new Words("angsi", "son", R.mipmap.family_son,  R.raw.family_son));
        FamilyList.add(3, new Words("tune", "daughter", R.mipmap.family_daughter, R.raw.family_daughter));
        FamilyList.add(4, new Words("taachi", "older brother", R.mipmap.family_older_brother, R.raw.family_older_brother));
        FamilyList.add(5, new Words("chalitti", "younger brother", R.mipmap.family_younger_brother, R.raw.family_younger_brother));
        FamilyList.add(6, new Words("tete", "older sister", R.mipmap.family_older_sister, R.raw.family_older_sister));
        FamilyList.add(7, new Words("kolliti", "younger sister", R.mipmap.family_younger_sister, R.raw.family_younger_sister));
        FamilyList.add(8, new Words("ama", "grandmother", R.mipmap.family_grandmother, R.raw.family_grandmother));
        FamilyList.add(9, new Words("paapa", "grandfather", R.mipmap.family_grandfather, R.raw.family_grandfather));

        RecyclerWordAdapter Adapter = new RecyclerWordAdapter(getActivity(), FamilyList, R.color.Family);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(Adapter);
        Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Words words = FamilyList.get(position);
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
