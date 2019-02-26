package com.suka.dsc.difableapp.ui.allbooksextend2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.suka.dsc.difableapp.R;
import com.suka.dsc.difableapp.model.BookData;

import java.io.IOException;

public class AllbooksExtend2Activity extends AppCompatActivity {
    private ImageButton btnPlay;
    private BookData bookData;
    private MediaPlayer mp;
    private boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookData = getIntent().getParcelableExtra("book_data");

        renderView();
        setMediaPlayer();
    }

    private void renderView() {
        setContentView(R.layout.blind_all_book_extend_2);

        btnPlay = findViewById(R.id.btn_extend_audio);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp.isPlaying()) {
                    mp.pause();
                    isPaused = true;
                }
                else if (isPaused) {
                    mp.start();
                    isPaused = false;
                } else playMediaFromUrl(bookData.getUrlAudio());
            }
        });

        getSupportActionBar().setTitle(bookData.getBookTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    private void setMediaPlayer() {
        if (mp == null){
            mp = new MediaPlayer();
        } else {
            mp.reset();
        }
    }

    private void playMediaFromUrl(String url){
        if (mp != null) mp.reset();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mp.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null){
            if (mp.isPlaying()){
                mp.stop();
            }
            mp.release();
        }
    }
}
