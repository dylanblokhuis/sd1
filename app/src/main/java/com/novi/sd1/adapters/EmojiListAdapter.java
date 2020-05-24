package com.novi.sd1.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * @author Dylan Blokhuis
 * @date 20-5-2020
 * Leerlijn: Software Development Praktijk 1
 */
public class EmojiListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> emojis;

    public EmojiListAdapter(@NonNull Context context, ArrayList<String> emojis) {
        this.context = context;
        this.emojis = emojis;
    }

    @Override
    public int getCount() {
        return emojis.size();
    }

    @Override
    public Object getItem(int position) {
        return emojis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        TextView emojiText = new TextView(context);
        emojiText.setTextSize(30);
        emojiText.setText(emojis.get(position));
        emojiText.setTextColor(Color.parseColor("#FFFFFF"));

        return emojiText;
    }
}
