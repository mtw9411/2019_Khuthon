package com.smartoc.khuthon2019;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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

public class memo4 extends Fragment {

    private MainActivity mainActivity;
    private ImageView back_button4;
    private ImageView plus_button;
    private RecyclerView etcrecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_memo4, container, false);

        final ArrayList<String> etclist_title = new ArrayList<>();
        final ArrayList<String> etclist_cont = new ArrayList<>();



        etcrecyclerView = view.findViewById(R.id.etc_recycler);
        back_button4 = view.findViewById(R.id.memo4_backbutton);
        back_button4.setClickable(true);
        back_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                mainActivity.onFragmentchange_memo(0);
            }
        });

        etcrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final memo4.etc_recyclerAdapter adapter = new memo4.etc_recyclerAdapter(etclist_title, etclist_cont);
        etcrecyclerView.setAdapter(adapter);

        etcrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        plus_button = view.findViewById(R.id.etc_plusbutton);
        plus_button.setClickable(true);
        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View view = LayoutInflater.from(getContext())
                        .inflate(R.layout.etc_dialog, null, false);
                builder.setView(view);


                final Button ButtonSubmit = (Button) view.findViewById(R.id.setbutton);
                final EditText input_text1 = (EditText) view.findViewById(R.id.etc_diatitle);
                final EditText input_text2 = (EditText) view.findViewById(R.id.etc_diacont);

                ButtonSubmit.setText("입력");

                final AlertDialog dialog = builder.create();

                ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String input_t1 = input_text1.getText().toString();
                        String input_t2 = input_text2.getText().toString();

                        etclist_title.add(etclist_title.size(), input_t1);
                        etclist_cont.add(etclist_cont.size(), input_t2);


                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });
                adapter.notifyDataSetChanged();
                dialog.show();
            }
        });


        return view;
    }

    public class etc_recyclerAdapter extends RecyclerView.Adapter<memo4.etc_recyclerAdapter.MyViewHolder>{
        private ArrayList<String> etcdataset1;
        private ArrayList<String> etcdataset2;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView etc_title;
            public TextView etc_cont;
            public CardView etc_cardview;

            //ViewHolder
            public MyViewHolder(View view) {
                super(view);
                etc_title = (TextView) view.findViewById(R.id.etc_title);
                etc_cont = (TextView) view.findViewById(R.id.etc_content);
                etc_cardview = (CardView) view.findViewById(R.id.etc_cardview);
            }
        }

        public etc_recyclerAdapter(ArrayList<String> myData1, ArrayList<String> myData2){
            this.etcdataset1 = myData1;
            this.etcdataset2 = myData2;
        }

        @NonNull
        @Override
        public memo4.etc_recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.etc_cardview, parent, false);
            return new memo4.etc_recyclerAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final memo4.etc_recyclerAdapter.MyViewHolder holder, int position) {

            holder.etc_title.setText(etcdataset1.get(position));
            holder.etc_cont.setText(etcdataset2.get(position));


        }

        @Override
        public int getItemCount() {
            return etcdataset1.size();
        }
    }
}
