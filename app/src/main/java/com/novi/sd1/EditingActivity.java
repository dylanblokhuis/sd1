package com.novi.sd1;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

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

                mPhotoEditor.setBrushDrawingMode(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
