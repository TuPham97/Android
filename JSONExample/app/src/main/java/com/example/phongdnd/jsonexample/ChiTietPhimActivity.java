package com.example.phongdnd.jsonexample;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phongdnd.jsonexample.model.Phim;
import com.example.phongdnd.jsonexample.service.APIClientPhim;
import com.example.phongdnd.jsonexample.service.PhimService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietPhimActivity extends AppCompatActivity {

    int maphim;
    ImageView hinh;
    TextView tenPhim,khoiChieu,giaPhim,moTa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent test = getIntent();
        maphim = (int)test.getSerializableExtra("MaPhim");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phim);
        hinh = (ImageView)findViewById(R.id.image);
        tenPhim = (TextView)findViewById(R.id.txttenphim);
        khoiChieu = (TextView)findViewById(R.id.txtkhoichieu);
        giaPhim = (TextView)findViewById(R.id.txtgia);
        moTa = (TextView)findViewById(R.id.txtmota);
        try{
            PhimService phimService = APIClientPhim.getClient().create(PhimService.class);
            Call call = phimService.findphim(maphim);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Phim phim = (Phim) response.body();
                    Resources res = getResources();
                    String a = phim.getHINHANH().replace(".jpg","");
                    String img = "@drawable/"+a;
                    int test = res.getIdentifier(img, null, getPackageName());

                    hinh.setImageResource(test);
                    tenPhim.setText(phim.getTENPHIM());
                    khoiChieu.setText(phim.getNGAYCONGCHIEU());
                    giaPhim.setText(phim.getGIA());
                    moTa.setText(phim.getMOTA());
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"FAILED",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    public void clickDatVe(View view){

        Intent temp = getIntent();
        int makh = (int) temp.getSerializableExtra("MaKH");
        if(makh!=0){
            Intent intent = new Intent(ChiTietPhimActivity.this,DatVeActivity.class);
            intent.putExtra("MaPhim",maphim);
            intent.putExtra("MaKH",makh);
            startActivity(intent);
        }
       else {
            Intent intent = new Intent(ChiTietPhimActivity.this,DangNhapActivity.class);
            startActivity(intent);
        }
    }
    public void clickTrolai(View view){
        finish();
    }

}
