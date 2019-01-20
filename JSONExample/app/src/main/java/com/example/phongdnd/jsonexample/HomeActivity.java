package com.example.phongdnd.jsonexample;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phongdnd.jsonexample.Adapter.ViewPagerAdapter;
import com.example.phongdnd.jsonexample.model.Phim;
import com.example.phongdnd.jsonexample.service.APIClientPhim;
import com.example.phongdnd.jsonexample.service.PhimService;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    public int[] images;
    ViewPager viewPager;
    ArrayList<Phim> listphim;
    static int makh;
    public static void setMa(int ma){
        makh=ma;
    }
    ViewPagerAdapter viewPagerAdapter;
    TextView txtTenPhim;
    Button btnDatVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        txtTenPhim = (TextView)findViewById(R.id.txtTenPhim);
        btnDatVe = (Button) findViewById(R.id.btnDatVe);
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(HomeActivity.this,ChiTietPhimActivity.class);
                    intent.putExtra("MaPhim",listphim.get(viewPager.getCurrentItem()).getMAPHIM());
                    intent.putExtra("MaKH",makh);
                    startActivity(intent);

            }
        });
        PhimService phimService = APIClientPhim.getClient().create(PhimService.class);
        Call call = phimService.findAll();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                listphim = (ArrayList<Phim>) response.body();
                images = new int[listphim.size()];
                for(int i=0;i<listphim.size();i++)
                {
                    Resources res = getResources();
                    String a = listphim.get(i).getHINHANH().replace(".jpg","");
                    String img = "@drawable/"+a;
                    int test = res.getIdentifier(img, null, getPackageName());
                    images[i] = test;
                    listphim.get(i).getGIA();
                    listphim.get(i).getMAPHIM();
                    listphim.get(i).getMOTA();
                    listphim.get(i).getNGAYCONGCHIEU();
                    listphim.get(i).getTENPHIM();
                }
                txtTenPhim.setText(listphim.get(0).getTENPHIM());
                viewPagerAdapter = new ViewPagerAdapter(HomeActivity.this,images);
                viewPager.setAdapter(viewPagerAdapter);
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(),"FAILED",Toast.LENGTH_SHORT).show();
            }
        });
        viewPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                txtTenPhim.setText(listphim.get(viewPager.getCurrentItem()).getTENPHIM());
            }
        });
    }

}
