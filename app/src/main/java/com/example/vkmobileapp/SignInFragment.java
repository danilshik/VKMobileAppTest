package com.example.vkmobileapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SignInFragment extends Fragment {

    Button btnSignIn;

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

        btnSignIn = view.findViewById(R.id.btn_sign_in);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Тут", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }
}
