package com.example.user.at;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public final String preference = "com.example.user.at.preference";
    public final String key = "skinStyle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int skinCode = getPreferenceInt(key);
        int color = getResources().getColor(R.color.colorMint);
        Log.d("test", skinCode + "");
        switch(skinCode){
            case 1:
                setTheme(R.style.AppThemeVer1);
                color = getResources().getColor(R.color.colorMint);
                break;
            case 2:
                setTheme(R.style.AppThemeVer2);
                color = getResources().getColor(R.color.colorBlue);
                break;
            case 3:
                setTheme(R.style.AppThemeVer3);
                color = getResources().getColor(R.color.colorBlack);
                break;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);               //커스텀 타이틀 사용
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);        //커스텀 타이틀을 사용하기 때문에 기존 타이틀 사용 안함
        toolbar.setBackgroundColor(color);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void my_info(View v){            //내정보(네비게이션 드로어 헤더 부분) 클릭 시
        Intent intent = new Intent(this, MyInfoListActivity.class);
        startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {                       //뒤로가기 클릭 시
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {     //드로우어가 열려있다면 닫음
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {         //옵션메뉴 생성
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {           //옵션아이템(홈버튼) 클릭 시
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        *//*if (id == R.id.action_GoHome) {         //홈버튼 클릭 시
            FragmentTest fragmentTest = new FragmentTest();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentTest).commit();     //처음화면으로
        }*//*

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {        //네비게이션 아이템 클릭 시
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;

        if (id == R.id.nav_board) {          //글 클릭 시
            // Handle the camera action
            intent = new Intent(this, BoardActivity.class);
        } else if (id == R.id.nav_message) {    //쪽지 클릭 시
            intent = new Intent(this, LetterMainActivity.class);
        } else if (id == R.id.nav_like) {       //관심 있는 작품 클릭 시
            intent = new Intent(this, LikeActivity.class);
        } else if (id == R.id.nav_help) {       //고객센터 클릭 시
            intent = new Intent(this, HelpActivity.class);
        } else if (id == R.id.nav_setting) {    //설정 클릭 시
            intent = new Intent(this, SettingActivity.class);
        } else if (id == R.id.nav_logout) {     //로그아웃 클릭 시
            intent = new Intent(this, SplashActivity.class);
            finish();
        }
        startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);        //네비게이션 아이템 클릭 후에는 드로우어 닫음
        return true;
    }

    @SuppressLint("ApplySharedPref")
    public void setPreference(String key, int value) {
        SharedPreferences pref = getSharedPreferences(preference, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getPreferenceInt(String key) {
        SharedPreferences pref = getSharedPreferences(preference, MODE_PRIVATE);
        return pref.getInt(key, 1);
    }

    public void changeSkin(View v){
        switch(v.getId()){
            case R.id.btn1:
                setPreference(key, 1);
                break;
            case R.id.btn2:
                setPreference(key, 2);
                break;
            case R.id.btn3:
                setPreference(key, 3);
                break;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
