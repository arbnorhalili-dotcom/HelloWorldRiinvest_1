package com.riinvest.helloworldriinvest_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class UniversityAdapter extends BaseAdapter {
    private Context context;
    public List<UniversityGson> universityGsonList = new ArrayList<>();

    public UniversityAdapter(Context _context)
    {
        context = _context;
    }

    @Override
    public int getCount() {
        return universityGsonList.size();
    }

    @Override
    public Object getItem(int i) {
        return universityGsonList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        UniversityViewHolder viewHolder;
        if(view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.university_row_layout,
                    viewGroup,
                    false);
            viewHolder = new UniversityViewHolder(view);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (UniversityViewHolder) view.getTag();
        }
        viewHolder.getTvUniversityNameRow().setText(universityGsonList.get(i).getName());
        viewHolder.getTvUniversityMainDomainRow().setText(universityGsonList.get(i).getDomains().get(0));
        return view;
    }
}
