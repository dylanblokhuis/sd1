package com.novi.sd1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * @author Dylan Blokhuis
 * @date 23-5-2020
 * Leerlijn: Software Development Praktijk 1
 */
public class DetailActivity extends AppCompatActivity {
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setPath(getIntent().getStringExtra("path"));

        final ImageView imageView = findViewById(R.id.selected_image);
        Glide
            .with(this)
            .load(getPath())
            .into(imageView);

        ImageButton shareButton = findViewById(R.id.detail_share_button);
        ImageButton deleteButton = findViewById(R.id.detail_delete_button);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getPath());

                Uri photoURI = FileProvider.getUriForFile(
                        DetailActivity.this,
                        getPackageName(),
                        file
                );

                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.putExtra(Intent.EXTRA_STREAM, photoURI);
                shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_body));
                shareIntent.setType("image/png");
                startActivity(Intent.createChooser(shareIntent,getResources().getString(R.string.share_with)));
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DetailActivity.this)
                        .setTitle(getResources().getString(R.string.dialog_delete_title))
                        .setMessage(getResources().getString(R.string.dialog_delete_body))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                deleteCurrentPhoto();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }

    private void deleteCurrentPhoto() {
        File file = new File(getPath());
        boolean deleted = file.delete();

        if (deleted) {
            Intent mainActivityIntent = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(mainActivityIntent);
            finish();
        } else {
            Toast.makeText(DetailActivity.this, getResources().getString(R.string.common_error_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void setPath(String path) {
        this.path = path;
    }

    private String getPath() {
        return path;
    }
}
