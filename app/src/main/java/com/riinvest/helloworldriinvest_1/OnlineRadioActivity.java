package com.riinvest.helloworldriinvest_1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import android.Manifest;
public class OnlineRadioActivity extends AppCompatActivity {

    ImageView btnPlayStop;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_radio);

        if(ContextCompat.checkSelfPermission(OnlineRadioActivity.this,
                Manifest.permission.READ_PHONE_STATE)== PackageManager.PERMISSION_GRANTED)
        {

        }
        else
        {
            requestPermissions(new String[]{ Manifest.permission.READ_PHONE_STATE },
                    1000);
        }

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
            telephonyManager.registerTelephonyCallback(getMainExecutor(),
                    new CallStateListener());
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

    @RequiresApi(api = Build.VERSION_CODES.S)
    public class CallStateListener extends TelephonyCallback
        implements TelephonyCallback.CallStateListener
    {
        @Override
        public void onCallStateChanged(int i) {
            if(i == TelephonyManager.CALL_STATE_IDLE)
            {
                mediaPlayer.setVolume(1,1);
            }
            else if (i==TelephonyManager.CALL_STATE_OFFHOOK)
            {
                mediaPlayer.setVolume(0,0);
            }
            else
            {
                mediaPlayer.setVolume(0.2f, 0.2f);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000://permission per read_phone_state
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                else {
                    onBackPressed();
                }
                return;
        }
    }
}