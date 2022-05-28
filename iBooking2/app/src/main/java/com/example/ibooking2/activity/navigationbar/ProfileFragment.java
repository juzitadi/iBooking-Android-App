package com.example.ibooking2.activity.navigationbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ibooking2.R;
import com.example.ibooking2.activity.ChangePwdActivity;
import com.example.ibooking2.activity.ForhelpActivity;
import com.example.ibooking2.activity.HistoryActivity;
import com.example.ibooking2.activity.LoginActivity;


public class ProfileFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView user_id=getActivity().findViewById(R.id.user_id);
        //get current user
        SharedPreferences sp = getActivity().getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        user_id.setText(sp.getString("loginName",""));

        Button forhelp = getActivity().findViewById(R.id.help);
        Button history =getActivity().findViewById(R.id.history);
        Button change=getActivity().findViewById(R.id.ChangePwd);
        Button back=getActivity().findViewById(R.id.back_login);


        forhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(getContext(), ForhelpActivity.class),1);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), HistoryActivity.class),1);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), ChangePwdActivity.class),1);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), LoginActivity.class),1);
            }
        });

    }
}