package com.baseProject.android.ui.chat;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.baseProject.android.data.remote.model.responseModel.chat.ChannelsItem;
import com.baseProject.android.data.remote.model.responseModel.usersForeign.UsersItem;
import com.baseProject.android.databinding.ChannelListLitemBinding;
import com.baseProject.android.util.PrefManager;
import com.baseProject.android.util.StringUtil;

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
        sortChannelList();
    }

    private void sortChannelList() {
        for (int i = 1; i < list.size(); i++) {
            ChannelsItem item = list.get(i);
            Boolean pinToTop = item.getPinToTop();
            if (!item.isMoved() && pinToTop != null && pinToTop) {
                item.setMoved(true);
                list.remove(i);
                list.add(0, item);
            }
        }
    }

    @NonNull
    @Override
    public ChannelListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChannelListLitemBinding binding = ChannelListLitemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ChannelListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelListViewHolder holder, int position) {
        ChannelsItem item = list.get(position);
        String lastSenderUserName = "You:";

        holder.binding.setItem(item);
        holder.binding.setSavedMessage("Saved Messages");
        holder.binding.setNoMessages("No Messages");
        if (item.getMessageLast() != null)
            holder.binding.setDate(StringUtil.getDateText(item.getDtaChangeMsg()));
        else
            holder.binding.setDate("");

        for (UsersItem usersItem : userList) {
            if (Objects.equals(usersItem.getId(), item.getIdPartner())) {
                holder.binding.setUser(usersItem);
                TextDrawable drawable = TextDrawable.builder()
                        .buildRound(String.valueOf(usersItem.getName().charAt(0)), Color.RED);
                holder.binding.avatarTextImageView.setImageDrawable(drawable);
                holder.binding.avatarTextImageView.setPadding(0, 0, 0, 0);

                if (!usersItem.getId().equals(PrefManager.getUserID())) {
                    lastSenderUserName = usersItem.getName() + ": ";
                }
                holder.binding.setLastSenderUserName(lastSenderUserName);

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
