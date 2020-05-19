package com.novi.sd1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.novi.sd1.fragments.GadgetDialog;
import com.novi.sd1.fragments.TextDialog;

import java.io.File;
import java.io.IOException;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import ja.burhanrashid52.photoeditor.TextStyleBuilder;

public class EditingActivity extends AppCompatActivity {
    private PhotoEditor mPhotoEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);

        String path = getIntent().getStringExtra("URI");
        if (path != null) {
            File file = new File(path);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.fromFile(file));
                PhotoEditorView mPhotoEditorView = findViewById(R.id.photoEditorView);

                mPhotoEditorView.getSource().setImageBitmap(bitmap);

                mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                        .setPinchTextScalable(true)
                        .build();

                addButtonListeners();

//                mPhotoEditor.addText(inputText, colorCode);

//                mPhotoEditor.setBrushDrawingMode(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTextToEditor() {
        new TextDialog(this);
    }

    private void addGadgetsToEditor() {
        new GadgetDialog(this);
    }

    private void addButtonListeners() {
        Button addTextButton = findViewById(R.id.editor_add_text);
        Button addGadgetButton = findViewById(R.id.editor_add_gadget);

        addTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTextToEditor();
            }
        });

        addGadgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGadgetsToEditor();
            }
        });

    }

    public PhotoEditor getmPhotoEditor() {
        return mPhotoEditor;
    }
}
