package com.xushuzhan.passwordbox;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Mr.Xu on 2016/2/22.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener{

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    //定义一个接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , HashMap data);
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (HashMap) v.getTag());
        }
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent,int viewType){


            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        //为创建的View注册点击事件
        view.setOnClickListener(this);
            return holder;


    }

        @Override
        public void onBindViewHolder (MyAdapter.MyViewHolder holder,int position){
        HashMap<String, Object> map = (HashMap<String, Object>) MainActivity.datas.get(position);

        holder.NameText.setText(map.get("name").toString());
        holder.AccountText.setText(map.get("account").toString());
        holder.PasswordText.setText(map.get("password").toString());

        //将数据保存在itemView的Tag中，以便点击时进行获取
            holder.itemView.setTag(map);


    }

        @Override
        public int getItemCount () {
        return MainActivity.datas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView NameText;
        TextView AccountText;
        TextView PasswordText;

        public MyViewHolder(View view){
            super(view);
            NameText= (TextView) view.findViewById(R.id.name);
            AccountText= (TextView) view.findViewById(R.id.account);
            PasswordText= (TextView) view.findViewById(R.id.password);
        }
    }
}
