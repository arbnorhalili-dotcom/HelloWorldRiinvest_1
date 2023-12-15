package com.riinvest.helloworldriinvest_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends BaseAdapter {
    public List<User> userList = new ArrayList<>();
    private Context context;
    public UsersAdapter(Context _context)
    {
        context = _context;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return userList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.row_layout,
                            viewGroup,
                            false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.getTvNameSurnameRow().setText(userList.get(i).getName()+ " "+
                userList.get(i).getSurname());
        viewHolder.getTvEmailRow().setText("Email: "+
                userList.get(i).getEmail());

        if(i%2==0)
        {
            viewHolder.getTvNameSurnameRow().setTextColor(
                    context.getColor(android.R.color.holo_red_dark)
            );
        }
        else
        {
            viewHolder.getTvNameSurnameRow().setTextColor(
                    context.getColor(android.R.color.black)
            );
        }

        return view;
    }
}
