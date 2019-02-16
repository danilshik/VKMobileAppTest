package com.example.vkmobileapp.friend;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vkmobileapp.R;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

public class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {
    VKList<VKApiUser> friendsList = new VKList<>();

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_friends_item, parent, false);
        return new FriendHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder viewHolder, int i) {
        viewHolder.bind(friendsList.get(i));
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    void addData(@NonNull VKList<VKApiUser> vkList){
        friendsList.addAll(vkList);
        notifyDataSetChanged();
    }
}
