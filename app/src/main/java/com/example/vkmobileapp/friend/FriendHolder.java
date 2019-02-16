package com.example.vkmobileapp.friend;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vkmobileapp.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.vk.sdk.api.model.VKApiUser;

public class FriendHolder extends RecyclerView.ViewHolder {

    private TextView tvFirstName;
    private TextView tvLastName;
    SimpleDraweeView imageUser;

    public FriendHolder(@NonNull View itemView) {
        super(itemView);
        tvFirstName = itemView.findViewById(R.id.tv_first_name);
        tvLastName = itemView.findViewById(R.id.tv_last_name);

        imageUser = itemView.findViewById(R.id.image_user);

    }

    public void bind(VKApiUser friend) {
        tvLastName.setText(friend.first_name);
        tvFirstName.setText(friend.last_name);
        imageUser.setImageURI(Uri.parse(friend.photo_100));
    }
}
