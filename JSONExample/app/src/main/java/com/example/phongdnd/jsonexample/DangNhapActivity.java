package com.example.phongdnd.jsonexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phongdnd.jsonexample.model.User;
import com.example.phongdnd.jsonexample.service.APIClientUser;
import com.example.phongdnd.jsonexample.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {

    Button dangkyBtn,dangnhapBtn;
    EditText userTxt,passTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        dangkyBtn = (Button)findViewById(R.id.btndangky);
        dangnhapBtn = (Button)findViewById(R.id.btndangnhap);
        userTxt = (EditText)findViewById(R.id.edittextuser);
        passTxt = (EditText)findViewById(R.id.edittextpass);
    }
    public void clickDangNhap(View view){
        try{
            String _UserName = userTxt.getText().toString();
            String _Pass = passTxt.getText().toString();
            UserService userService = APIClientUser.getClient().create(UserService.class);
            Call call = userService.find(_UserName,_Pass);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    User temp = (User)response.body();
                    Toast.makeText(getApplicationContext(),"COMPLETE",Toast.LENGTH_SHORT).show();
                    int ma = temp.getMAKH();
                    Intent intent = new Intent(DangNhapActivity.this,HomeActivity.class);
                    intent.putExtra("MaKH",ma);
                    HomeActivity.setMa(ma);
                    startActivity(intent);

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
    public void clickDangKy(View view){
        Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
        startActivity(intent);
    }
    public void clickThoat(View view){
        finish();

    }
}
