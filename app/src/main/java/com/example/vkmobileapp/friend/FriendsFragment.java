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

import com.example.vkmobileapp.R;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
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
    private final FriendAdapter mFriendAdapter = new FriendAdapter();
    VKList<VKApiUser> vkList;

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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "photo_100", "first_name", "last_name", VKApiConst.COUNT, 5));
        request.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
//
                Log.d("Ответ от сервера", response.responseString);
                try {
                    JSONObject jsonObject = response.json.getJSONObject("response");
                    JSONArray items = jsonObject.getJSONArray ("items");
                    System.out.println(String.valueOf(items));
                    vkList = new VKList<>(items, VKApiUser.class);
                    System.out.println("Размер" +  String.valueOf(vkList.size()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFriendAdapter);
        System.out.println("Размер" +  String.valueOf(vkList.size()));
        mFriendAdapter.addData(vkList);

    }
}
