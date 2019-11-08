package com.smartoc.khuthon2019;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class popUp_Date extends DialogFragment {

    public static final String TAG_MONTH="tag_month";

    private memo2 memo2;
    private TextView popUpDate_month, popUpDate_day, popUpDate_finish;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_popup_date, container, false);

        popUpDate_month = view.findViewById(R.id.popUpDate_month);
        popUpDate_day = view.findViewById(R.id.popUpDate_day);
        popUpDate_finish = view.findViewById(R.id.popUpDate_finish);

        popUpDate_finish.setClickable(true);
        popUpDate_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String month = popUpDate_month.getText().toString();
                final String day = popUpDate_day.getText().toString();

                if(month.equals("") | day.equals("")){
                    Toast.makeText(getContext(), "날짜를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Bundle bundle = new Bundle();

                    bundle.putString("month", month);
                    bundle.putString("day", day);

                    memo2.setArguments(bundle);

                    dismiss();
                }
            }
        });

        return view;
    }
}

