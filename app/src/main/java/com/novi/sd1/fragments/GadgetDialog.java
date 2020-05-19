package com.novi.sd1.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.novi.sd1.EditingActivity;
import com.novi.sd1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ja.burhanrashid52.photoeditor.PhotoEditor;

public class GadgetDialog {
    private Dialog dialog;

    public GadgetDialog(final EditingActivity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(R.layout.gadget_dialog);
        setDialog(builder.create());
        getDialog().show();

        if (this.getDialog().getWindow() != null) {
            FlexboxLayout flexboxLayout = getDialog().findViewById(R.id.gadget_layout);
            ArrayList<String> emojis = PhotoEditor.getEmojis(activity);

            for (final String emoji : emojis) {
                TextView emojiText = new TextView(activity);
                emojiText.setTextSize(30);
                emojiText.setText(emoji);
                emojiText.setTextColor(Color.parseColor("#FFFFFF"));
                emojiText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.getmPhotoEditor().addEmoji(emoji);
                        getDialog().cancel();
                    }
                });

                flexboxLayout.addView(emojiText);
            }

            // make the window of the dialog transparent
            this.getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    private void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    private Dialog getDialog() {
        return dialog;
    }
}
