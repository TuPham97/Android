package com.example.phongdnd.jsonexample.model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.internal.http.HttpHeaders;

public class UserModel {
    public boolean create(User user) {
        try{
            Map<String,String> values = new HashMap<String,String>();
            values.put("USERNAME",user.getUSERNAME());
            values.put("PASS",user.getPASS());
            values.put("HOTEN",user.getHOTEN());
            values.put("GIOITINH",String.valueOf(user.getGIOITINH()));
            values.put("NGAYSINH",user.getNGAYSINH());
            values.put("SDT",user.getSDT());
            JSONObject jsonObject = new JSONObject(values);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
