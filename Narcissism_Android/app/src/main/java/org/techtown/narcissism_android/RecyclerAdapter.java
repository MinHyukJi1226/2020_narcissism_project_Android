package org.techtown.narcissism_android;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{

    private ArrayList<Data> questiondata = new ArrayList<>();
    private ClickListener listener;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions,null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ItemViewHolder holder,int category) {
        Data d = questiondata.get(category);
        holder.title.setText(d.getTitle());

        if(listener != null){
            holder.itemView.setOnClickListener(v->{
                listener.onClick(d.getQuestionId());
            });
        }
    }
    @Override
    public int getItemCount() {
        return questiondata.size();
    }

    public void addItem(Data data){
        questiondata.add(data);
    }

    public void setListener(ClickListener listener){
        this.listener = listener;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        //private TextView content;
        private ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.questionTitle);
            //content = itemView.findViewById(R.id.questionContent);
            //imageView = itemView.findViewById(R.id.questionImage);

        }
    }
}
