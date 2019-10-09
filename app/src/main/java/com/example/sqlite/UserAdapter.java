package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<User> users;
    private DatabaseHelper helper;

    public UserAdapter() {
    }

    public UserAdapter(Context context, List<User> users, DatabaseHelper helper) {
        this.context = context;
        this.users = users;
        this.helper = helper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
       final User user= users.get(position);
       holder.name.setText(user.getName());
       holder.age.setText(user.getAge());
       //this part for the delete data from sqlite database
       holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               helper = new DatabaseHelper(context);
               helper.deletedata(user.getId());
               users.remove(position);
               notifyDataSetChanged();

               return false;
           }
       });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,age;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.nameTV);
            age = itemView.findViewById(R.id.ageTV);
        }
    }
}
