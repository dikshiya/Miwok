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

public class PhrasesFragment extends Fragment {

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



    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;// Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.activity_phrases, container, false);
        maudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Words> PhrasesList = new ArrayList<>();
        PhrasesList.add(0, new Words("minto wuksus", "Where are you going?", R.raw.phrase_where_are_you_going));
        PhrasesList.add(1, new Words("tinnә oyaase'nә", "What is your name?", R.raw.phrase_what_is_your_name));
        PhrasesList.add(2, new Words("oyaaset...", "My name is...",  R.raw.phrase_my_name_is));
        PhrasesList.add(3, new Words("michәksәs?", "How are you feeling?", R.raw.phrase_how_are_you_feeling));
        PhrasesList.add(4, new Words("kuchi achit", "I'm feeling good.", R.raw.phrase_im_feeling_good));
        PhrasesList.add(5, new Words("әәnәs'aa?", "Are you coming?", R.raw.phrase_are_you_coming));
        PhrasesList.add(6, new Words("hәә’ әәnәm", "Yes, I'm coming.", R.raw.phrase_yes_im_coming));
        PhrasesList.add(7, new Words("әәnәm", "I'm coming.", R.raw.phrase_im_coming));
        PhrasesList.add(8, new Words("yoowutis", "Let's go", R.raw.phrase_lets_go));
        PhrasesList.add(9, new Words("әnni'nem", "Come here.", R.raw.phrase_come_here));

        RecyclerWordAdapter Adapter = new RecyclerWordAdapter(getActivity(), PhrasesList, R.color.Phrases);
        RecyclerView recyclerView = rootView.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(Adapter);
        Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Words words = PhrasesList.get(position);
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
