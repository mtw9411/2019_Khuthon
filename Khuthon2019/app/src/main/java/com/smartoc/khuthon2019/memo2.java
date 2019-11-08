package com.smartoc.khuthon2019;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class memo2 extends Fragment{

    private MainActivity mainActivity;
    private ImageView back_button2, imageView_NewDate;
    private RecyclerView planrecycler;
    private TextView textView2, popUpDate_finish;
    private ArrayList<String> dateList = new ArrayList<>();
    private PlanAdapter PlanAdapter;
    private FirebaseDatabase mdatabase;
    private DatabaseReference dataref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_memo2, container, false);

        mdatabase = FirebaseDatabase.getInstance();
        dataref = mdatabase.getReference("memo");

//        dateList.clear();
        dataref.child("memo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        String date = (String) data.getValue();
                        dateList.add(date);
                    }
                    PlanAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    // "My Plans" RecyclerView 구현
        planrecycler = view.findViewById(R.id.planrecycler);
        // 레이아웃 종류 정의
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        planrecycler.setLayoutManager(layoutManager);

        PlanAdapter = new PlanAdapter(dateList);
        planrecycler.setAdapter(PlanAdapter);

        PlanAdapter.notifyDataSetChanged();

        // 뒤로가기 버튼
        back_button2 = view.findViewById(R.id.memo2_backbutton);
        back_button2.setClickable(true);
        back_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                mainActivity.onFragmentchange_memo(0);
            }
        });

        //날짜 추가하기 버튼
        imageView_NewDate = view.findViewById(R.id.imageView_NewDate);
        imageView_NewDate.setClickable(true);
        imageView_NewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_popup_date, null, false);
                builder.setView(view);

                final EditText popUpDate_month = view.findViewById(R.id.popUpDate_month);
                final EditText popUpDate_day = view.findViewById(R.id.popUpDate_day);
                popUpDate_finish =  view.findViewById(R.id.popUpDate_finish);

                final AlertDialog dialog = builder.create();

                popUpDate_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String month = popUpDate_month.getText().toString();
                        String day = popUpDate_day.getText().toString();
                        dateList.add(month + "월  " + day + "일");
                        dataref.child("memo").setValue(dateList.get(dateList.size()-1));


                        Log.d("################",dateList.get(dateList.size()-1));

                        PlanAdapter.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });
                PlanAdapter.notifyDataSetChanged();
                dialog.show();
            }
        });

        return view;
    }


// 어댑터 연결
    public class PlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private ArrayList<String> mData;
        private ArrayList<ScheduleDTO> scheduleList = new ArrayList<>();
        private ScheduleAdapter scheduleAdapter = new ScheduleAdapter(scheduleList);

        // 아이템 뷰를 저장하는 뷰홀더 클래스.
        public class PlanViewHolder extends RecyclerView.ViewHolder {
            private TextView plan_date;
            private RecyclerView scheduleRecycler;
            private ImageView add_schedule;

            public PlanViewHolder(View itemView) {
                super(itemView);

                // 뷰 객체에 대한 참조. (hold strong reference)
                plan_date = itemView.findViewById(R.id.plan_date);
                add_schedule = itemView.findViewById(R.id.add_schedule);

                scheduleRecycler = itemView.findViewById(R.id.schedule);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                scheduleRecycler.setLayoutManager(layoutManager);

                scheduleAdapter = new ScheduleAdapter(scheduleList);
                scheduleRecycler.setAdapter(scheduleAdapter);

                scheduleAdapter.notifyDataSetChanged();
            }
        }

        // 생성자에서 데이터 리스트 객체를 전달받음.
        PlanAdapter(ArrayList<String> list) {
            mData = list;
        }

        // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder holder;
            View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_memo2_date, parent, false);
            holder = new PlanViewHolder(view);

            return holder ;
        }

        // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final String text = mData.get(position) ;
            ((PlanViewHolder)holder).plan_date.setText(text) ;
            ((PlanViewHolder)holder).add_schedule.setClickable(true);
            ((PlanViewHolder)holder).add_schedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_popup_schedule, null, false);
                    builder.setView(view);

                    final EditText popUpSchedule_hour = view.findViewById(R.id.popUpSchedule_hour);
                    final EditText popUpSchedule_minute = view.findViewById(R.id.popUpSchedule_minute);
                    final EditText popUpSchedule_place = view.findViewById(R.id.popUpSchedule_place);
                    final EditText popUpSchedule_note = view.findViewById(R.id.popUpSchedule_note);
                    final TextView popUpSchedule_finish =  view.findViewById(R.id.popUpSchedule_finish);

                    final AlertDialog dialog = builder.create();

                    popUpSchedule_finish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ScheduleDTO sDTO = new ScheduleDTO();
                            String hour = popUpSchedule_hour.getText().toString();
                            String minute = popUpSchedule_minute.getText().toString();
                            sDTO.setTime(hour+":"+minute);
                            String place = popUpSchedule_place.getText().toString();
                            String note = popUpSchedule_note.getText().toString();
                            sDTO.setPlace(place);
                            sDTO.setNote(note);
                            scheduleList.add(sDTO);
//                            dataref.child("memo").child(text).setValue(sDTO);

                            scheduleAdapter.notifyDataSetChanged();

                            dialog.dismiss();
                        }
                    });
                    scheduleAdapter.notifyDataSetChanged();
                    dialog.show();
                }
            });
            scheduleList.clear();
        }

        // getItemCount() - 전체 데이터 갯수 리턴.
        @Override
        public int getItemCount() {
            return mData.size() ;
        }
    }


    public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<ScheduleDTO> scheduleList;

        // 아이템 뷰를 저장하는 뷰홀더 클래스.
        public class ScheduleViewHolder extends RecyclerView.ViewHolder {
            private TextView plan_time, plan_place, plan_note;

            public ScheduleViewHolder(View itemView) {
                super(itemView);

                // 뷰 객체에 대한 참조. (hold strong reference)
                plan_time = itemView.findViewById(R.id.plan_time);
                plan_place = itemView.findViewById(R.id.plan_place);
                plan_note = itemView.findViewById(R.id.plan_note);
            }
        }

        // 생성자에서 데이터 리스트 객체를 전달받음.
        ScheduleAdapter(ArrayList<ScheduleDTO> list) {
            scheduleList = list;
        }

        // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder holder;
            View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_memo2_schedule, parent, false);
            holder = new ScheduleAdapter.ScheduleViewHolder(view);

            return holder;
        }

        // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ScheduleDTO scheduleDTO = scheduleList.get(position);
            String time = scheduleDTO.getTime();
            String place = scheduleDTO.getPlace();
            String note = scheduleDTO.getNote();

            ((memo2.ScheduleAdapter.ScheduleViewHolder)holder).plan_time.setText(time) ;
            ((memo2.ScheduleAdapter.ScheduleViewHolder)holder).plan_place.setText(place) ;
            ((memo2.ScheduleAdapter.ScheduleViewHolder)holder).plan_note.setText(note) ;
        }

        // getItemCount() - 전체 데이터 갯수 리턴.
        @Override
        public int getItemCount() {
            return scheduleList.size() ;
        }
    }




}
