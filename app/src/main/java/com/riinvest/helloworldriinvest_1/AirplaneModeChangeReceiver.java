package com.riinvest.helloworldriinvest_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class AirplaneModeChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(isAirplaneModeActive(context)==true)
        {
            Toast.makeText(context, "AirplaneMode activated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "AirplaneMode DEactivated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAirplaneModeActive(Context context)
    {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON,0) != 0;
    }
}
