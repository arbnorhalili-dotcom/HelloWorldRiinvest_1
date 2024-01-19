package com.riinvest.helloworldriinvest_1;

import android.view.View;
import android.widget.TextView;

public class UniversityViewHolder {
    TextView tvUniversityNameRow, tvUniversityMainDomainRow;

    public UniversityViewHolder(View view)
    {
        tvUniversityNameRow = view.findViewById(R.id.tvUniversityNameRow);
        tvUniversityMainDomainRow = view.findViewById(R.id.tvUniverityMainDomainRow);
    }

    public TextView getTvUniversityNameRow() {
        return tvUniversityNameRow;
    }

    public TextView getTvUniversityMainDomainRow() {
        return tvUniversityMainDomainRow;
    }
}
