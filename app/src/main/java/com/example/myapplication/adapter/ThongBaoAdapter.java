package com.example.myapplication.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.List;

public class ThongBaoAdapter extends ArrayAdapter<String> {

    private List<String> thongbaoList;
    private Context context;

    public ThongBaoAdapter(Context context, List<String> thongbaoList) {
        super(context, 0, thongbaoList);
        this.context = context;
        this.thongbaoList = thongbaoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tb, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewThongBao = convertView.findViewById(R.id.textViewThongBao);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewThongBao.setText(thongbaoList.get(position));

        return convertView;
    }

    static class ViewHolder {
        TextView textViewThongBao;
    }
}
