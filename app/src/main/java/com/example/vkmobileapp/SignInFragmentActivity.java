package com.example.vkmobileapp;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class SignInFragmentActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return SignInFragment.newInstance();
    }
}
