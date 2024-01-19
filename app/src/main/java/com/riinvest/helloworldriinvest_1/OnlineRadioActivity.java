package com.riinvest.helloworldriinvest_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class OnlineRadioActivity extends AppCompatActivity {

    ImageView btnPlayStop;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_radio);

        btnPlayStop = findViewById(R.id.btnPlayStop);
        btnPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer==null || mediaPlayer.isPlaying()==false)
                {
                    InitializeMediaPlayer();
                    btnPlayStop.setImageResource(R.drawable.baseline_stop_circle_24);
                }
                else
                {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    btnPlayStop.setImageResource(R.drawable.baseline_play_circle_filled_24);
                }
            }
        });
    }

    private void InitializeMediaPlayer()
    {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("http://mediaserv30.live-streams.nl:8086/live");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        mediaPlayer.prepareAsync();
        InitializeTelephonyManager();
    }

    private void InitializeTelephonyManager()
    {
        TelephonyManager telephonyManager =
                (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        {
            //handle me callback - interrupt
        }
        else
        {
            PhoneStateListener phoneStateListener = new PhoneStateListener()
            {
                @Override
                public void onCallStateChanged(int state, String phoneNumber) {
                    if(state == TelephonyManager.CALL_STATE_IDLE)
                    {
                        mediaPlayer.setVolume(1,1);
                    }
                    else if(state == TelephonyManager.CALL_STATE_OFFHOOK)
                    {
                        mediaPlayer.setVolume(0,0);
                    }
                    else {
                        mediaPlayer.setVolume(0.2f, 0.2f);
                    }
                }
            };
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }
}