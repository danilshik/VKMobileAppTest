package com.example.vkmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.vkmobileapp.friend.FriendsFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class SignInFragment extends Fragment {

    ImageView imageVKSign;

    public static SignInFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in_fragment, container, false);

        imageVKSign = view.findViewById(R.id.vk_sign);
        imageVKSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] scope = new String[]{
                        VKScope.FRIENDS
                };
                VKSdk.login(getActivity(), scope);
                Log.d("Авторизация", "авторизация: ");


            }
        });



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Вход", "вход в метод: ");
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {

            public void onResult(VKAccessToken res) {
                Toast toast = Toast.makeText(getActivity(), res.toString(), Toast.LENGTH_SHORT);
                toast.show();

                FragmentManager fragmentManager = getFragmentManager();
                assert fragmentManager != null;
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FriendsFragment.newInstance())
                        .commit();

            }

            public void onError(VKError error) {
                Toast toast = Toast.makeText(getActivity(), "Что-то пошло не так.", Toast.LENGTH_SHORT);
                toast.show();
            }

        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}
