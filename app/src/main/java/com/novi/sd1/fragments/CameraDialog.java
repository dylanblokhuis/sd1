package com.novi.sd1.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.novi.sd1.R;

/**
 * @author Dylan Blokhuis
 * @date 14-4-2020
 * Leerlijn: Software Development Praktijk 1
 */
public class CameraDialog {
    private Dialog builder;
    private Button libraryButton;
    private Button cameraButton;

    public CameraDialog(AppCompatActivity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.camera_dialog_title)
                .setView(R.layout.camera_dialog)
                .setNegativeButton(R.string.camera_dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        this.builder = builder.create();
    }

    public void show() {
        this.builder.show();

        this.libraryButton = this.builder.findViewById(R.id.library_button);
        this.cameraButton = this.builder.findViewById(R.id.camera_button);
    }

    public Button getLibraryButton() {
        return libraryButton;
    }

    public Button getCameraButton() {
        return cameraButton;
    }
}
