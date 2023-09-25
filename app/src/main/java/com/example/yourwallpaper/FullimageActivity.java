package com.example.yourwallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;

public class FullimageActivity extends AppCompatActivity {
    ImageView imageView;
    MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullimage);
        imageView=findViewById(R.id.fullimg);
        materialButton=findViewById(R.id.btn_apply);
        Glide.with(this).load(getIntent().getStringExtra("image")).into(imageView);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackground();
            }

            private void setBackground() {
                Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());

                try {
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(FullimageActivity.this, "Wallpaper added Successfully", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(FullimageActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }
            }
        });
    }
}