package com.example.pulici;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pulici.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

public class PostAdapter extends FirebaseRecyclerAdapter<Post,PostAdapter.PostViewHolder>
{
    private OnItemClickListner listner;

    public PostAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Post> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull PostAdapter.PostViewHolder postViewHolder, int i, @NonNull @NotNull Post post) {
        postViewHolder.post_title.setText(post.getTopic());
        postViewHolder.post_name.setText(post.getName());
        postViewHolder.post_complaint.setText(post.getComplain());
    }

    @NonNull
    @NotNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        TextView post_title,post_name,post_complaint;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            post_title = itemView.findViewById(R.id.post_title);
            post_name = itemView.findViewById(R.id.post_name);
            post_complaint = itemView.findViewById(R.id.post_complaint);
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