package com.example.ibooking2.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.ibooking2.R;

import java.text.ParseException;

public class MyCustomDialog1 extends AppCompatDialogFragment {
    private static final String TAG = "MyCustomDialog1";

    private ExampleDialogListener listener;

    public interface OnInputSelected {
        void sendInput(Boolean input) throws ParseException;
    }

    public OnInputSelected mOnInputSelected;

    private TextView mActionYes, mActionNo;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_my_custom1, container, false);
        mActionYes = view.findViewById(R.id.action_yes);
        mActionNo = view.findViewById(R.id.action_no);


        mActionNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        mActionYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onYesClicked();
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
    super.onAttach(context);
    try {
        listener = (ExampleDialogListener) context;
    } catch (ClassCastException e) {
        throw new ClassCastException(context.toString()
                + "must implement ExampleDialogListener");
    }
    }

    public interface ExampleDialogListener {
        void onYesClicked();
    }
}
