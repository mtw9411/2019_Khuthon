package com.smartoc.khuthon2019;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private navi_memo navi_memo = new navi_memo();
    private navi_story navi_story = new navi_story();
    private memo1 memo1 = new memo1();
    private memo2 memo2 = new memo2();
    private memo3 memo3 = new memo3();
    private memo4 memo4 = new memo4();
    private story1 story1 = new story1();

    private BottomNavigationView bottom_navi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, splashActivity.class);
        startActivity(intent);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("목이아파");

        bottom_navi = findViewById(R.id.bottomNavigationView_navi);
        bottom_navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menuitem_bottombar_memo:
                        setFrag(0);

                        return true;

                    case R.id.menuitem_bottombar_story:
                        setFrag(1);

                        return true;

                }
                return false;
            }
        });
        setFrag(0);
    }

    public void setFrag(int n){    //프래그먼트를 교체하는 작업을 하는 메소드를 만들었습니다
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (n){
            case 0:
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out).replace(R.id.Frame_navi, navi_memo);
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null); //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                break;
            case 1:
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out).replace(R.id.Frame_navi, navi_story);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);
                break;
        }
    }

    public void onFragmentchange_memo(int index){
        if (index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_navi, navi_memo).commit();
        }
        else if (index ==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_navi, memo1).commit();
        }
        else if (index ==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_navi, memo2).commit();
        }
        else if (index ==3){
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_navi, memo3).commit();
        }
        else if (index ==4){
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_navi, memo4).commit();
        }
    }
    public void onFragmentchange_story(int index){
        if (index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_navi, navi_memo).commit();
        }
        else if (index ==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_navi, story1).commit();
        }
    }
}

