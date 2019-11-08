package com.smartoc.khuthon2019;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

        final ArrayList<String> checklist = new ArrayList<>();

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

        listcheckrecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        final listcheck_recyclerAdapter adapter = new listcheck_recyclerAdapter(checklist);
        listcheckrecycler.setAdapter(adapter);
        listcheckrecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        plus_button = view.findViewById(R.id.listplus_button);
        plus_button.setClickable(true);
        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View view = LayoutInflater.from(getContext())
                        .inflate(R.layout.list_edit, null, false);
                builder.setView(view);


                final Button ButtonSubmit = (Button) view.findViewById(R.id.input_button);
                final EditText input_text = (EditText) view.findViewById(R.id.input_Text);

                ButtonSubmit.setText("입력");

                final AlertDialog dialog = builder.create();

                ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String input_t = input_text.getText().toString();
                        checklist.add(checklist.size(), input_t);

                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });
                adapter.notifyDataSetChanged();
                dialog.show();
            }
        });

        adapter.notifyDataSetChanged();


        return view;
    }

//    public class listcheckrecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
//
//        private ArrayList<String> checklistdata = new ArrayList<>();
//
//        @NonNull
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//
//            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_memo_checklist, viewGroup, false);
//            return new RecyclerView.ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
//            String inputtext = checklistdata.get(i);
//            viewHolder.check_name.setText(inputtext);
//            viewHolder.checkbox.setClickable(true);
//            viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    viewHolder.checkbox.setImageResource(R.mipmap.check_after);
//                    notifyDataSetChanged();
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return checklistdata.size();
//        }
//    }

    public class listcheck_recyclerAdapter extends RecyclerView.Adapter<listcheck_recyclerAdapter.MyViewHolder>{
        private ArrayList<String> mDataset;
        private int Type = 0;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView check_name;
            public ImageView checkbox;

            //ViewHolder
            public MyViewHolder(View view) {
                super(view);
                check_name = (TextView) view.findViewById(R.id.check_name);
                checkbox = (ImageView) view.findViewById(R.id.checkbox);
            }
        }

        public listcheck_recyclerAdapter(ArrayList<String> myData){
            this.mDataset = myData;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_memo_checklist, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final listcheck_recyclerAdapter.MyViewHolder holder, int position) {

            holder.check_name.setText(mDataset.get(position));

            //클릭이벤트
            holder.checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Type == 0){
                        holder.checkbox.setImageResource(R.mipmap.check_after);
                        Type = 1;
                    } else if (Type == 1){
                        holder.checkbox.setImageResource(R.mipmap.check_before);
                        Type = 0;
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }
}

