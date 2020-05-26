package com.example.Bachat;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    //private onFragmentBtnSelected listener;
    //private onFragmentBtnSelected listener2;
    private static final String TAG = "HomeFragment";
    ImageButton imageButton;
    ImageButton imageButton2;
    Button test_button_2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        imageButton = view.findViewById(R.id.Add_Income);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onButtonSelected();
                Intent intent = new Intent(getActivity(), EarningSecondScreen.class);
                startActivity(intent);
            }
        });
        imageButton2 = view.findViewById(R.id.Add_Expense);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onButtonSelected();
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Adding Expense",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.show();
                Intent intent = new Intent(getActivity(), SecondScreen.class);
                startActivity(intent);
            }
        });

        test_button_2 =view.findViewById(R.id.test_button);
        test_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onButtonSelected();
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Adding Expense",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.show();
                Intent intent = new Intent(getActivity(), ShowAllScreen.class);
                startActivity(intent);
            }
        });
        return view;
    }

    /*@Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onFragmentBtnSelected) {
            listener = (onFragmentBtnSelected) context;
            //Log.d(TAG,"value of listener" + String.valueOf(listener));
        }
        else {
            throw new ClassCastException(context.toString()+"must implement listener");
        }
    }

    public interface onFragmentBtnSelected{
        public void onButtonSelected();
    }*/
}
