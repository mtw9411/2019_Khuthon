package com.smartoc.khuthon2019;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class memo1 extends Fragment {

    private RecyclerView listcheckrecycler;
    private MainActivity mainActivity;
    private ImageView back_button1;
    private ImageView plus_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_memo1, container, false);

        ArrayList<String> checklist = new ArrayList<>();

        listcheckrecycler = view.findViewById(R.id.recycler_checklist);
        back_button1 = view.findViewById(R.id.memo1_backbutton);
        back_button1.setClickable(true);
        back_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                mainActivity.onFragmentchange_memo(0);
            }
        });

        plus_button = view.findViewById(R.id.listplus_button);
        plus_button.setClickable(true);
        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        listcheckrecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        listcheckrecyclerAdapter adapter = new listcheckrecyclerAdapter(checklist);
        listcheckrecycler.setAdapter(adapter);
        listcheckrecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }

    public class listcheckrecyclerAdapter extends RecyclerView.Adapter<listcheckrecyclerAdapter.ViewHolder>{

        private ArrayList<String> checklistdata = new ArrayList<>();

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView checkbox;
            TextView check_name;
            ViewHolder(View itemView) {
                super(itemView) ;

                // 뷰 객체에 대한 참조. (hold strong reference)
                checkbox = itemView.findViewById(R.id.checkbox);
                check_name = itemView.findViewById(R.id.check_name);
            }
        }

        listcheckrecyclerAdapter(ArrayList<String> list){
            list = checklistdata;
        }

        @NonNull
        @Override
        public listcheckrecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            Context context = viewGroup.getContext() ;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

            View view = inflater.inflate(R.layout.recycler_memo_checklist, viewGroup, false) ;
            listcheckrecyclerAdapter.ViewHolder vh = new listcheckrecyclerAdapter.ViewHolder(view) ;

            return vh ;
        }

        @Override
        public void onBindViewHolder(@NonNull listcheckrecyclerAdapter.ViewHolder viewHolder, int i) {
            String inputtext = viewHolder.check_name.getText().toString();
            checklistdata.set(i, inputtext);
        }

        @Override
        public int getItemCount() {
            return checklistdata.size();
        }
    }
}

