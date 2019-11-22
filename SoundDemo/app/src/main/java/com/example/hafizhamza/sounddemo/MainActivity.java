package com.example.hafizhamza.sounddemo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer m;
    AudioManager audioManager;
    public void Play(View view) {
        m.start();
    }

    public void Pause(View view) {
        m.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m=MediaPlayer.create(this,R.raw.cricket);
        audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);

        int maxvolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVol=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar Volume=(SeekBar)findViewById(R.id.VolumeSeekBar);
        Volume.setMax(maxvolume);
        Volume.setProgress(currentVol);
        Volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final SeekBar Scrub=(SeekBar) findViewById(R.id.ScrubseekBar);
Scrub.setMax(m.getDuration());
        Scrub.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
m.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Scrub.setProgress(m.getCurrentPosition());
            }
        },0,300);
    }


}
