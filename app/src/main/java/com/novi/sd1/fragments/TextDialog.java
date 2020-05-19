package com.novi.sd1.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.novi.sd1.EditingActivity;
import com.novi.sd1.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ja.burhanrashid52.photoeditor.PhotoEditor;

public class TextDialog {
    private Dialog dialog;
    private int textColor = Color.parseColor("#FFFFFF");

    public TextDialog(final EditingActivity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(R.layout.text_dialog);
        setDialog(builder.create());
        getDialog().show();

        if (this.getDialog().getWindow() != null) {
            // get the current month name
            SimpleDateFormat dateFormat = new SimpleDateFormat( "MMMM", Locale.getDefault() );
            String currentMonth = dateFormat.format(new Date()).toLowerCase();

            // set the input field
            final EditText editText = getDialog().findViewById(R.id.employee_text);
            editText.setText(activity.getResources().getString(R.string.text_dialog_default, currentMonth));
            editText.setTextColor(textColor);

            // get colors for the user to choose from
            String[] colors = activity.getResources().getStringArray(R.array.text_colors);
            LinearLayout colorLayout = getDialog().findViewById(R.id.color_layout);

            for (final String color : colors) {
                Button colorButton = new Button(activity);
                colorButton.setBackgroundColor(Color.parseColor(color));
                colorLayout.addView(colorButton);

                colorButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setTextColor(Color.parseColor(color));
                        editText.setTextColor(Color.parseColor(color));
                    }
                });
            }

            // add text to photo editor
            Button applyButton = getDialog().findViewById(R.id.add_color_button);
            applyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhotoEditor photoEditor = activity.getmPhotoEditor();
                    photoEditor.addText(editText.getText().toString(), getTextColor());
                    getDialog().cancel();
                }
            });

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

    private void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    private int getTextColor() {
        return textColor;
    }
}
