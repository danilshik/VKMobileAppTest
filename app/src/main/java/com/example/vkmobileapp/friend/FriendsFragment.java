package com.example.vkmobileapp.friend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vkmobileapp.R;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKBatchRequest;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FriendsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TextView currentUserTV;
    private final FriendAdapter mFriendAdapter = new FriendAdapter();
    VKList<VKApiUser> vkListFriends ;
    VKApiUser currentUser;

    public static FriendsFragment newInstance() {
        return new FriendsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_friends_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        currentUserTV = view.findViewById(R.id.current_login_in_user);

        VKRequest requestLoginCurrentUser = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "first_name"));
        VKRequest requestFriends = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "photo_100", "first_name", "last_name", VKApiConst.COUNT, 5));

        VKBatchRequest batch = new VKBatchRequest(requestFriends, requestLoginCurrentUser);
        batch.executeWithListener(new VKBatchRequest.VKBatchRequestListener() {
            @Override
            public void onComplete(VKResponse[] responses) {
                Log.d("Ответ от сервера Friend", responses[0].responseString);
                try {
                    JSONObject jsonObject = responses[0].json.getJSONObject("response");
                    JSONArray items = jsonObject.getJSONArray ("items");
                    System.out.println(String.valueOf(items));
                    vkListFriends = new VKList<>(items, VKApiUser.class);
                    System.out.println("Размер:   " + vkListFriends.size());

                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.setAdapter(mFriendAdapter);
                    mFriendAdapter.addData(vkListFriends);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("Текущий пользователь Js", responses[1].responseString);
                try {

                    JSONArray jsonArrays = responses[1].json.getJSONArray ("response");
                    VKList<VKApiUser> vkListCurrentUsers = new VKList<>(jsonArrays, VKApiUser.class);
                    currentUser = vkListCurrentUsers.get(0);
                    String fullName = currentUser.first_name + " " + currentUser.last_name;
                    currentUserTV.setText(fullName);
                    System.out.println("Имя текущего пользователя: " + currentUser.first_name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }
}
