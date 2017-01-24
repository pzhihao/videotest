package com.ailtt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ailtt.bean.Livesource;
import com.ailtt.videotest.R;
import com.ailtt.videotest.SimplePlayerActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/24.
 */

public class TvListAdapter extends BaseAdapter {
    private List<Livesource> livesources;
    private Context context;


    public TvListAdapter(List<Livesource> livesources, Context context) {
        this.livesources = livesources;
        this.context = context;
    }

    @Override
    public int getCount() {
        return livesources.size();
    }

    @Override
    public Object getItem(int position) {
        return livesources.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder=null;
        if (null == convertView){
            viewHolder=new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.tv_item, parent, false);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.thumb_image);
            viewHolder.title= (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.desc= (TextView) convertView.findViewById(R.id.desc);

            convertView.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.title.setText(livesources.get(position).getName());
        viewHolder.desc.setText(livesources.get(position).getVideoUrl());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了条目", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, SimplePlayerActivity.class);
                intent.putExtra("obj",livesources.get(position));
                context.startActivity(intent);
            }
        });

        return convertView;
    }



    class ViewHolder{
        ImageView imageView;
        TextView title;
        TextView desc;
    }
}
