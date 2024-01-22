package com.riinvest.helloworldriinvest_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class WiFiModeChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(isWiFiActive(context)==true)
        {
            Toast.makeText(context, "WiFi is ON", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "WiFi is OFF", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isWiFiActive(Context context)
    {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.Global.WIFI_ON,0) != 0;
    }
}
