package com.example.android_prueba_ordenamiento.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android_prueba_ordenamiento.R;
import com.example.android_prueba_ordenamiento.activity.activity2.Main2Activity;
import com.example.android_prueba_ordenamiento.database.Database;
import com.example.android_prueba_ordenamiento.entity.Element;

import java.util.ArrayList;

/**
 * Created by Bill on 15/10/2017.
 */

public class AdapterElements extends RecyclerView.Adapter<AdapterElements.MyViewHolder>{
    private ArrayList<Element> elements;

    public AdapterElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.onBind(elements.get(position),getItemCount());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cardView;
        private Element element;
        private Database database;
        private int totalElements;

        MyViewHolder(View itemView) {
            super(itemView);
            database= new Database(itemView.getContext());
            cardView=itemView.findViewById(R.id.cardViewElement);
        }

        public void onBind(Element element,int totalElements){
            this.element=element;
            this.totalElements=totalElements;
            cardView.setBackgroundColor(Color.parseColor(element.getColor()));
            cardView.setOnClickListener(this);
            Log.i("element","order last: "+String.valueOf(element.getOrderLast())+
                    " color: "+String.valueOf(element.getName())+
                    " count: "+String.valueOf(element.getCont())+
                    " index: "+String.valueOf(element.getId()));
        }


        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.cardViewElement) {
                if (database.getElement(element.getId()).getOrderLast() != totalElements) {
                    for (int i = 2; i <= totalElements; i++) {
                        database.updateOrderLatestByOrder(i);
                    }
                    database.updateOrderLatest(element.getId(),totalElements);
                }
                database.updateContLatest(element.getId());
                Intent intent = new Intent(view.getContext(), Main2Activity.class);
                intent.putExtra("color",element.getColor());
                view.getContext().startActivity(intent);
                Log.i("touch",element.getName());
            }
        }
    }
}
