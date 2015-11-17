package com.example.zhangweijin.coorappbar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangweijin.coorappbar.R;
import com.example.zhangweijin.coorappbar.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangweijin on 15-11-14.
 */
public class ListAdatpter extends RecyclerView.Adapter {
    private ImageView iv ;
    private TextView tv;
    private List<Person> personList;

    public ListAdatpter(List<Person> personList) {
        this.personList = personList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        Person person = personList.get(i);
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.iv.setImageResource(person.getImageId());
        holder.tv.setText(person.getName());

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public  ImageView iv ;
        public TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.photo);
            tv = (TextView) itemView.findViewById(R.id.name);
        }
    }
    public void addPerson(Person p) {
        if (personList != null) {
            personList.add(p);
        }else {
            personList = new ArrayList<Person>();
            personList.add(p);
        }
        notifyDataSetChanged();
    }
}
