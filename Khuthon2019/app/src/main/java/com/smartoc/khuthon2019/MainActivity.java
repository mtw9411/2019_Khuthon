package com.smartoc.khuthon2019;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private navi_home navi_home = new navi_home();
    private navi_memo navi_memo = new navi_memo();
    private navi_story navi_story = new navi_story();
    private memo1 memo1 = new memo1();
    private memo2 memo2 = new memo2();
    private memo3 memo3 = new memo3();
    private memo4 memo4 = new memo4();

    private BottomNavigationView bottom_navi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        bottom_navi = findViewById(R.id.bottomNavigationView_navi);
        bottom_navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menuitem_bottombar_home:
                        setFrag(0);

                        return true;

                    case R.id.menuitem_bottombar_memo:
                        setFrag(1);

                        return true;

                    case R.id.menuitem_bottombar_story:
                        setFrag(2);

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
                fragmentTransaction.replace(R.id.Frame_navi, navi_home);
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null); //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                break;
            case 1:
                fragmentTransaction.replace(R.id.Frame_navi, navi_memo);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);
                break;
            case 2:
                fragmentTransaction.replace(R.id.Frame_navi, navi_story);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
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
}

