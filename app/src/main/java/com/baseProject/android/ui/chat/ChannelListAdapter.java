package com.baseProject.android.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baseProject.android.data.remote.model.responseModel.chat.ChannelsItem;
import com.baseProject.android.data.remote.model.responseModel.usersForeign.UsersItem;
import com.baseProject.android.databinding.ChannelListLitemBinding;

import java.util.List;
import java.util.Objects;

public class ChannelListAdapter extends RecyclerView.Adapter<ChannelListAdapter.ChannelListViewHolder> {
    private final Context context;
    private final List<ChannelsItem> list;
    private final List<UsersItem> userList;

    public ChannelListAdapter(Context context, List<ChannelsItem> list, List<UsersItem> userList) {
        this.context = context;
        this.list = list;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ChannelListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChannelListLitemBinding binding = ChannelListLitemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ChannelListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelListViewHolder holder, int position) {
        holder.binding.setItem(list.get(position));
        for (UsersItem usersItem : userList) {
            if (Objects.equals(usersItem.getId(), list.get(position).getIdPartner())) {
                holder.binding.setUser(usersItem);
                return;
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected static class ChannelListViewHolder extends RecyclerView.ViewHolder {
        private final ChannelListLitemBinding binding;

        public ChannelListViewHolder(@NonNull ChannelListLitemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
