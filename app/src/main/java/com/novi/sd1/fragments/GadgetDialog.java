package com.novi.sd1.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.novi.sd1.EditingActivity;
import com.novi.sd1.R;
import com.novi.sd1.adapters.EmojiListAdapter;

import java.util.ArrayList;

import ja.burhanrashid52.photoeditor.PhotoEditor;

public class GadgetDialog {
    private Dialog dialog;

    public GadgetDialog(final EditingActivity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(R.layout.gadget_dialog);
        setDialog(builder.create());
        getDialog().show();

        final GridView gadgetGrid = getDialog().findViewById(R.id.gadget_layout);

        if (this.getDialog().getWindow() != null) {
            // make the window of the dialog transparent
            this.getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            // fill an adapter with a big list of emojis
            final ArrayList<String> emojis = PhotoEditor.getEmojis(activity);
            gadgetGrid.setAdapter(new EmojiListAdapter(getDialog().getContext(), emojis));
            gadgetGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    activity.getmPhotoEditor().addEmoji(emojis.get(position));
                    getDialog().cancel();
                }
            });
        }
    }

    private void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    private Dialog getDialog() {
        return dialog;
    }
}
