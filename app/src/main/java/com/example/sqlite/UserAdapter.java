package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.databinding.ItemUserBinding;

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
        ItemUserBinding binding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_user,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
       final User user= users.get(position);
       holder.binding.nameTV.setText(user.getName());
       holder.binding.ageTV.setText(user.getAge());
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
        private  ItemUserBinding binding;
        public ViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
           this.binding= binding;
        }
    }
}
