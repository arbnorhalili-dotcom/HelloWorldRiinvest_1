package com.riinvest.helloworldriinvest_1;

import android.view.View;
import android.widget.TextView;

public class ViewHolder {
    private TextView tvNameSurnameRow;
    private TextView tvEmailRow;

    public TextView getTvNameSurnameRow()
    {
        return tvNameSurnameRow;
    }
    public TextView getTvEmailRow()
    {
        return tvEmailRow;
    }

    public ViewHolder(View view)
    {
        tvNameSurnameRow = view.findViewById(R.id.tvNameSurnameRow);
        tvEmailRow = view.findViewById(R.id.tvEmailRow);
    }
}
