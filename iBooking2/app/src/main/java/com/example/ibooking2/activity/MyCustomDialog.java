package com.example.ibooking2.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ibooking2.R;
import com.example.ibooking2.object.Room;

import java.text.ParseException;

public class MyCustomDialog extends DialogFragment {
    private static final String TAG="MyCustomDialog";
    private Room room;
    public interface OnInputSelected{
        void sendInput(Boolean input) throws ParseException;
    }
    public OnInputSelected mOnInputSelected;

    private TextView mActionOk, mActionCancel, mrandom_name;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_my_custom, container, false);
        mActionOk = view.findViewById(R.id.action_ok);
        mActionCancel = view.findViewById(R.id.action_cancel);
        mrandom_name=view.findViewById(R.id.random_name);
        Bundle bundle=this.getArguments();

        if(bundle!=null){
            room=(Room)bundle.getSerializable("room");
            System.out.println("room name haha!!!!!!"+room.getName());
            mrandom_name.setText(room.getName());
        }

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input.");

                  Boolean input = true;

                try {
                    mOnInputSelected.sendInput(input);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage() );
        }
    }
}
