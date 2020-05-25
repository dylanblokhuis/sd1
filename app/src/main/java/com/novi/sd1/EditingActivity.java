package com.novi.sd1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.novi.sd1.fragments.GadgetDialog;
import com.novi.sd1.fragments.TextDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

/**
 * @author Dylan Blokhuis
 * @date 15-4-2020
 * Leerlijn: Software Development Praktijk 1
 */
public class EditingActivity extends AppCompatActivity {
    private PhotoEditor mPhotoEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);

        String path = getIntent().getStringExtra("URI");
        if (path != null) {
            File file = new File(path);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.fromFile(file));
            } catch (IOException e) {
                Toast.makeText(EditingActivity.this, getResources().getString(R.string.file_loading_error), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            PhotoEditorView mPhotoEditorView = findViewById(R.id.photoEditorView);
            mPhotoEditorView.getSource().setImageBitmap(bitmap);
            mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(true)
                .build();

            addButtonListeners();
        }
    }

    private void addButtonListeners() {
        Button addTextButton = findViewById(R.id.editor_add_text);
        Button addGadgetButton = findViewById(R.id.editor_add_gadget);
        Button saveButton = findViewById(R.id.save_button);

        addTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TextDialog(EditingActivity.this);
            }
        });
        addGadgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GadgetDialog(EditingActivity.this);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePhotoEditorPicture();
            }
        });
    }

    private void savePhotoEditorPicture() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";
        File picturesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (picturesDir != null) {
            String path = picturesDir.getAbsolutePath() + "/edited/" + imageFileName;
            getmPhotoEditor().saveAsFile(path, new PhotoEditor.OnSaveListener() {
                @Override
                public void onSuccess(@NonNull String imagePath) {
                    Intent mainActivityIntent = new Intent(EditingActivity.this, MainActivity.class);
                    startActivity(mainActivityIntent);
                }

                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(EditingActivity.this, getResources().getString(R.string.common_error_message), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public PhotoEditor getmPhotoEditor() {
        return mPhotoEditor;
    }
}
