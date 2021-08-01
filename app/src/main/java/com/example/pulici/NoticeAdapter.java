package com.example.pulici;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pulici.models.Notice;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;

import org.jetbrains.annotations.NotNull;

public class NoticeAdapter extends FirebaseRecyclerAdapter<Notice,NoticeAdapter.NoticeViewHolder>
{
    private OnItemClickListner listner;

    public NoticeAdapter(@NonNull FirebaseRecyclerOptions<Notice> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull NoticeAdapter.NoticeViewHolder noticeViewHolder, int i, @NonNull @NotNull Notice notice) {
        noticeViewHolder.notice_topic.setText(notice.getTopic());
        noticeViewHolder.notice_desc.setText(notice.getDesc());
    }


    @NonNull
    @NotNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_item,parent,false);
        return new NoticeViewHolder(view);
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder{
        TextView notice_topic,notice_desc;



        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            notice_topic = itemView.findViewById(R.id.notice_topic);
            notice_desc = itemView.findViewById(R.id.notice_desc);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION && listner!= null){
                        listner.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });
        }

    }

    public interface OnItemClickListner{
        void onItemClick(DataSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner=listner;
    }

}