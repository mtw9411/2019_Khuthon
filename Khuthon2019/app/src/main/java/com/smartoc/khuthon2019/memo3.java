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

public class memo3 extends Fragment {

    private MainActivity mainActivity;
    private ImageView back_button3;
    private ImageView plus_button;
    private RecyclerView food_recycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_memo3, container, false);

        final ArrayList<String> foodlist_title = new ArrayList<>();
        final ArrayList<String> foodlist_loc = new ArrayList<>();
        final ArrayList<String> foodlist_cont = new ArrayList<>();

        food_recycler = view.findViewById(R.id.food_recycler);
        back_button3 = view.findViewById(R.id.memo3_backbutton);
        back_button3.setClickable(true);
        back_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                mainActivity.onFragmentchange_memo(0);
            }
        });

        food_recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        final memo3.food_recyclerAdapter adapter = new memo3.food_recyclerAdapter(foodlist_title, foodlist_loc, foodlist_cont);
        food_recycler.setAdapter(adapter);

        food_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        plus_button = view.findViewById(R.id.plus_button);
        plus_button.setClickable(true);
        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View view = LayoutInflater.from(getContext())
                        .inflate(R.layout.food_dialog, null, false);
                builder.setView(view);


                final Button ButtonSubmit = (Button) view.findViewById(R.id.add_button);
                final EditText input_text1 = (EditText) view.findViewById(R.id.food_title);
                final EditText input_text2 = (EditText) view.findViewById(R.id.food_loc);
                final EditText input_text3 = (EditText) view.findViewById(R.id.food_cont);


                final AlertDialog dialog = builder.create();

                ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String input_t1 = input_text1.getText().toString();
                        String input_t2 = input_text2.getText().toString();
                        String input_t3 = input_text3.getText().toString();

                        foodlist_title.add(foodlist_title.size(), input_t1);
                        foodlist_loc.add(foodlist_loc.size(), input_t2);
                        foodlist_cont.add(foodlist_cont.size(), input_t3);


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

    public class food_recyclerAdapter extends RecyclerView.Adapter<memo3.food_recyclerAdapter.MyViewHolder>{
        private ArrayList<String> fooddataset1;
        private ArrayList<String> fooddataset2;
        private ArrayList<String> fooddataset3;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView food_title;
            public TextView food_cont;
            public TextView food_loc;
            public CardView food_cardview;

            //ViewHolder
            public MyViewHolder(View view) {
                super(view);
                food_title = (TextView) view.findViewById(R.id.food_title_card);
                food_loc = (TextView) view.findViewById(R.id.food_loc_card);
                food_cont = (TextView) view.findViewById(R.id.food_content_card);
                food_cardview = (CardView) view.findViewById(R.id.food_cardview);
            }
        }

        public food_recyclerAdapter(ArrayList<String> myData1, ArrayList<String> myData2, ArrayList<String> myData3){
            this.fooddataset1 = myData1;
            this.fooddataset2 = myData2;
            this.fooddataset3 = myData3;
        }

        @NonNull
        @Override
        public memo3.food_recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.food_cardview, parent, false);


            return new memo3.food_recyclerAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final memo3.food_recyclerAdapter.MyViewHolder holder, int position) {

            holder.food_title.setText(fooddataset1.get(position));
            holder.food_loc.setText(fooddataset2.get(position));
            holder.food_cont.setText(fooddataset3.get(position));

        }

        @Override
        public int getItemCount() {
            return fooddataset1.size();
        }
    }
}
