package com.example.miwokself;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerWordAdapter extends RecyclerView.Adapter<RecyclerWordAdapter.WordsVH>{
    private ArrayList<Words> mWords;
    private Context mContext;
    private int mColor;
    private OnItemClickListener OnItem;
    @NonNull
    @Override
    public WordsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WordsVH(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    public RecyclerWordAdapter(Activity context, ArrayList<Words> words, int color){
        mContext = context;
        mColor = color;
        mWords = words;
    }


    @Override
    public void onBindViewHolder(@NonNull WordsVH holder, int position) {
        Words currentWord = mWords.get(position);
        holder.englishtrans.setText(currentWord.getmDefaultTrans());
        holder.miwoktrans.setText(currentWord.getMiwokTrans());
        holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext,mColor));

        if (currentWord.hasImage()){
            holder.ResourceID.setImageResource(currentWord.getmResourceID());
            holder.ResourceID.setVisibility(View.VISIBLE);
        }
        else {
            holder.ResourceID.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OnItem != null) {
                    OnItem.OnItemClick(holder.getAdapterPosition());
                }
            }
        });

    }
    public void setOnItemClickListener(OnItemClickListener OnItemListener){
        this.OnItem=OnItemListener;
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    static class WordsVH extends RecyclerView.ViewHolder {
        private TextView englishtrans;
        private TextView miwoktrans;
        private ImageView ResourceID;
        private RelativeLayout relativeLayout;



        public WordsVH(@NonNull View itemView) {
            super(itemView);
           this.englishtrans = itemView.findViewById(R.id.Default_translation);
           this.miwoktrans = itemView.findViewById(R.id.Mowik_translation);
           this.ResourceID = itemView.findViewById(R.id.image_for_words);
           relativeLayout = itemView.findViewById(R.id.text_container);
        }


    }
}


