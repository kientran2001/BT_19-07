package com.example.bt_tonghop;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    Cursor cs;

    public ItemAdapter(Cursor cs) {
        this.cs = cs;
    }

    @Override
    public int getCount() {
        return cs.getCount();
    }

    @Override
    public Object getItem(int i) {
        return cs.moveToPosition(i);
    }

    @Override
    public long getItemId(int i) {
        cs.moveToPosition(i);
        return cs.getInt(0);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);

        TextView textName = view.findViewById(R.id.text_name);
        TextView textMSSV = view.findViewById(R.id.text_mssv);
        TextView textEmail = view.findViewById(R.id.text_email);
        TextView textNgsinh = view.findViewById(R.id.text_ngsinh);

        cs.moveToPosition(i);

        String name = cs.getString(1);
        String mssv = cs.getString(2);
        String email = cs.getString(3);
        String ngSinh = cs.getString(4);

        textName.setText(name);
        textMSSV.setText(mssv);
        textEmail.setText(email);
        textNgsinh.setText(ngSinh);

        return view;
    }
}

