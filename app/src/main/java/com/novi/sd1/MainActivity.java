package com.novi.sd1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.novi.sd1.fragments.CameraDialog;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraDialog();
            }
        });
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    private void cameraDialog() {
        CameraDialog dialog = new CameraDialog(this);
        dialog.show();

        Button libraryButton = dialog.getLibraryButton();
        Button cameraButton = dialog.getCameraButton();

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        libraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        System.out.println("test");
//        super.onActivityResult(requestCode, resultCode, data);
//        if (RESULT_OK == resultCode) {
//            // Get Extra from the intent
//            Bundle extras = data.getExtras();
//            // Get the returned image from extra
//            Bitmap bmp = (Bitmap) extras.get("data");
//
////            ImageView iv = (ImageView) findViewById(R.id.ReturnedImageView);
////            iv.setImageBitmap(bmp);
//        }
//    }
}