package com.example.miwokself;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordsAdapter extends ArrayAdapter<Words> {
    private int mcolor;
    private Context mContext;

    public WordsAdapter(Activity context, ArrayList<Words> words, int color){
        super(context,0,words);
        mcolor = color;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Words translation = getItem(position);
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView default_textview = (TextView) listItemView.findViewById(R.id.Default_translation);
        default_textview.setText(translation.getmDefaultTrans());
        TextView mowik_translation = (TextView) listItemView.findViewById(R.id.Mowik_translation);
        mowik_translation.setText(translation.getMiwokTrans());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_for_words);
        if (translation.hasImage()){
            imageView.setImageResource(translation.getmResourceID());
            imageView.setVisibility(View.VISIBLE);
        }
        else{ imageView.setVisibility(View.GONE);}

        View textCont = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mcolor);
        textCont.setBackgroundColor(color);

        return listItemView;

}
}
