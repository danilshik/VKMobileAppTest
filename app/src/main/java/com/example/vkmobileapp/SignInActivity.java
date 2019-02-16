package com.example.vkmobileapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.example.vkmobileapp.friend.FriendsFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class SignInActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(VKSdk.isLoggedIn()){
            FragmentManager fragmentManager = getSupportFragmentManager();
            assert fragmentManager != null;
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, FriendsFragment.newInstance())
                    .commit();
        }

    }

    @Override
    protected Fragment getFragment() {
        return SignInFragment.newInstance();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Вход", "вход в метод: ");
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {

            public void onResult(VKAccessToken res) {
                Toast toast = Toast.makeText(getApplicationContext(), "Успешная авторизация", Toast.LENGTH_SHORT);
                toast.show();
                Log.d("Result", res.toString());

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FriendsFragment.newInstance())
                        .commit();

            }

            public void onError(VKError error) {
                Toast toast = Toast.makeText(getApplicationContext(), "Авторизация не удалась", Toast.LENGTH_SHORT);
                toast.show();
            }

        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}
