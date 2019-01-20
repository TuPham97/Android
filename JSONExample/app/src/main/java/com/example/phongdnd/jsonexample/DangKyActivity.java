package com.example.phongdnd.jsonexample;

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

public class DangKyActivity extends AppCompatActivity {
    private Button dangkyBtn;
    private EditText userName,passWord,fullName,sex,dateBirth,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        dangkyBtn = (Button)findViewById(R.id.btndongy);
        userName = (EditText)findViewById(R.id.dkuser);
        passWord = (EditText)findViewById(R.id.dkpass);
        fullName = (EditText)findViewById(R.id.dkhoten);
        sex = (EditText)findViewById(R.id.dkgioitinh);
        dateBirth = (EditText)findViewById(R.id.dkngaysinh);
        phone = (EditText)findViewById(R.id.dksdt);
    }

    public void clickDangKy(View view) {
        try{
            User _user = new User();
            _user.setUSERNAME(userName.getText().toString());
            _user.setPASS(passWord.getText().toString());
            _user.setHOTEN(fullName.getText().toString());
            String check = sex.getText().toString();
            if(check.equals("Nam")) {
                _user.setGIOITINH(true);
            }
            else {
                _user.setGIOITINH(false);
            }

            _user.setNGAYSINH(dateBirth.getText().toString());
            _user.setSDT(phone.getText().toString());

            UserService userService = APIClientUser.getClient().create(UserService.class);
            Call call = userService.create(_user);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Toast.makeText(getApplicationContext(),"COMPLETE",Toast.LENGTH_SHORT).show();
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
}
