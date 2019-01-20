package com.example.phongdnd.jsonexample;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phongdnd.jsonexample.model.Phim;
import com.example.phongdnd.jsonexample.model.Ve;
import com.example.phongdnd.jsonexample.service.APIClientPhim;
import com.example.phongdnd.jsonexample.service.APIClientVe;
import com.example.phongdnd.jsonexample.service.PhimService;
import com.example.phongdnd.jsonexample.service.VeService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatVeActivity extends AppCompatActivity {

    public int[] a=new int[20];
    private ArrayList<Integer> soghe = new ArrayList<Integer>();
    private Button[] v=new Button[20];
    private Button datve;
    private TextView txtTenphim;
    private Spinner spinerSuat;
    private Spinner spinnerRap;
    private Button btnDatve;
    private int index1 =0,index2 =0,dem=0,maphim=0;
    private String ma;
    Ve _ve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);
        txtTenphim=(TextView)findViewById(R.id.txtTenphim);
        spinnerRap=(Spinner)findViewById(R.id.spinnerRap);
        spinerSuat=(Spinner)findViewById(R.id.spinnerSuat);
        btnDatve=(Button)findViewById(R.id.btnDatve);

        //addItemsOnSpinnerRap();

        Intent test = getIntent();
        maphim = (int)test.getSerializableExtra("MaPhim");
        try{
            PhimService phimService = APIClientPhim.getClient().create(PhimService.class);
            Call call = phimService.findphim(maphim);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Phim phim = (Phim) response.body();
                    List<String> list = new ArrayList<String>();
                    txtTenphim.setText(phim.getTENPHIM());
                    String test = "array"+String.valueOf(maphim);
                    int id = getResources().getIdentifier(test,"array",getPackageName());
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DatVeActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(id));
                    spinnerRap.setAdapter(dataAdapter);



                }
                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"FAILED",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        for(int i=0; i<20;i++){
            a[i]=0;
        }

        datve=(Button)findViewById( R.id.btnDatve );
        v[0]=(Button)findViewById(R.id.v1);
        v[1]=(Button)findViewById(R.id.v2);
        v[2]=(Button)findViewById(R.id.v3);
        v[3]=(Button)findViewById(R.id.v4);
        v[4]=(Button)findViewById(R.id.v5);
        v[5]=(Button)findViewById(R.id.v6);
        v[6]=(Button)findViewById(R.id.v7);
        v[7]=(Button)findViewById(R.id.v8);
        v[8]=(Button)findViewById(R.id.v9);
        v[9]=(Button)findViewById(R.id.v10);
        v[10]=(Button)findViewById(R.id.v11);
        v[11]=(Button)findViewById(R.id.v12);
        v[12]=(Button)findViewById(R.id.v13);
        v[13]=(Button)findViewById(R.id.v14);
        v[14]=(Button)findViewById(R.id.v15);
        v[15]=(Button)findViewById(R.id.v16);
        v[16]=(Button)findViewById(R.id.v17);
        v[17]=(Button)findViewById(R.id.v18);
        v[18]=(Button)findViewById(R.id.v19);
        v[19]=(Button)findViewById(R.id.v20);
        taoGhe(soghe);
        checkGhe();
        spinnerRap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                index1 = arg2; // lấy index user chọn
                checkGhe();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) { }

        });
        spinerSuat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                checkGhe();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        datve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date = new Date();
                    Intent temp = getIntent();
                    final int makh = (int) temp.getSerializableExtra("MaKH");
                    for(int i=0;i<a.length;i++)
                    {
                        if(a[i]>0)
                        {
                            dem++;
                            _ve = new Ve();
                            _ve.setMARAP(Integer.parseInt(spinnerRap.getItemAtPosition(index1).toString()));
                            int masuat = (int)spinerSuat.getSelectedItemId();
                            _ve.setMASUAT(masuat+1);
                            _ve.setMAKH(makh);
                            _ve.setNGAYDAT(dateFormat.format(date));
                            _ve.setMAGHE(i+1);
                            VeService veService = APIClientVe.getClient().create(VeService.class);
                            Call call = veService.create(_ve);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    Toast.makeText(getApplicationContext(),"COMPLETE",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(DatVeActivity.this,HomeActivity.class);
                                    intent.putExtra("MaKH",makh);
                                    HomeActivity.setMa(makh);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    Toast.makeText(getApplicationContext(),"FAILED",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                    if(dem==0)
                    {
                        Toast.makeText(getApplicationContext(),"Vui Lòng Chọn Ghế",Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
public  void taoGhe(ArrayList<Integer> soghe){
    if(soghe.size()>0)
    {
        for(int i=0;i<soghe.size();i++)
        {
            if(soghe.get(i)>0)
            {
                a[soghe.get(i)-1] = -1;
            }
        }
    }
    else
    {
        for(int i=0;i<20;i++)
        {
                a[i] = 0;
        }
    }
    for(int i=0; i<20;i++){
        if(a[i]==-1){
            ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFF4E4B4B});
            v[i].setBackgroundTintList(csl);
            v[i].setTextColor(  Color.parseColor( "#c4c2c2") );
            v[i].setEnabled( false );

        }
        else
        {
            ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
            v[i].setBackgroundTintList(csl);
            v[i].setTextColor(  Color.parseColor( "#000000") );
            v[i].setEnabled( true );
        }
    }


    v[0].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[0]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[0].setBackgroundTintList(csl);
                v[0].setTextColor( Color.parseColor( "#ff0000" ) );
                a[0]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[0].setBackgroundTintList(csl);
                v[0].setTextColor( Color.parseColor( "#000000" ) );
                a[0]=0;
            }

        }
    });
    v[1].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[1]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[1].setBackgroundTintList(csl);
                v[1].setTextColor( Color.parseColor( "#ff0000" ) );
                a[1]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[1].setBackgroundTintList(csl);
                v[1].setTextColor( Color.parseColor( "#000000" ) );
                a[1]=0;
            }
        }
    });
    v[2].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[2]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[2].setBackgroundTintList(csl);
                v[2].setTextColor( Color.parseColor( "#ff0000" ) );
                a[2]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[2].setBackgroundTintList(csl);
                v[2].setTextColor( Color.parseColor( "#000000" ) );
                a[2]=0;
            }
        }
    });
    v[3].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[3]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[3].setBackgroundTintList(csl);
                v[3].setTextColor( Color.parseColor( "#ff0000" ) );
                a[3]=1;
            }
            else {

                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[3].setBackgroundTintList(csl);
                v[3].setTextColor( Color.parseColor( "#000000" ) );
                a[3]=0;
            }
        }
    });
    v[4].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[4]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[4].setBackgroundTintList(csl);
                v[4].setTextColor( Color.parseColor( "#ff0000" ) );
                a[4]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[4].setBackgroundTintList(csl);
                v[4].setTextColor( Color.parseColor( "#000000" ) );
                a[4]=0;
            }
        }
    });
    v[5].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[5]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[5].setBackgroundTintList(csl);
                v[5].setTextColor( Color.parseColor( "#ff0000" ) );
                a[5]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[5].setBackgroundTintList(csl);
                v[5].setTextColor( Color.parseColor( "#000000" ) );
                a[5]=0;
            }
        }
    });
    v[6].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[6]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[6].setBackgroundTintList(csl);
                v[6].setTextColor( Color.parseColor( "#ff0000" ) );
                a[6]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[6].setBackgroundTintList(csl);
                v[6].setTextColor( Color.parseColor( "#000000" ) );
                a[6]=0;
            }
        }
    });
    v[7].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[7]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[7].setBackgroundTintList(csl);
                v[7].setTextColor( Color.parseColor( "#ff0000" ) );
                a[7]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[7].setBackgroundTintList(csl);
                v[7].setTextColor( Color.parseColor( "#000000" ) );
                a[7]=0;
            }
        }
    });
    v[8].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[8]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[8].setBackgroundTintList(csl);
                v[8].setTextColor( Color.parseColor( "#ff0000" ) );
                a[8]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[8].setBackgroundTintList(csl);
                v[8].setTextColor( Color.parseColor( "#000000" ) );
                a[8]=0;
            }
        }
    });
    v[9].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[9]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[9].setBackgroundTintList(csl);
                v[9].setTextColor( Color.parseColor( "#ff0000" ) );
                a[9]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[9].setBackgroundTintList(csl);
                v[9].setTextColor( Color.parseColor( "#000000" ) );
                a[9]=0;
            }
        }
    });
    v[10].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[10]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[10].setBackgroundTintList(csl);
                v[10].setTextColor( Color.parseColor( "#ff0000" ) );
                a[10]=1;

            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[10].setBackgroundTintList(csl);
                v[10].setTextColor( Color.parseColor( "#000000" ) );
                a[10]=0;
            }
        }
    });
    v[11].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[11]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[11].setBackgroundTintList(csl);
                v[11].setTextColor( Color.parseColor( "#ff0000" ) );
                a[11]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[11].setBackgroundTintList(csl);
                v[11].setTextColor( Color.parseColor( "#000000" ) );
                a[11]=0;
            }
        }
    });
    v[12].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[12]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[12].setBackgroundTintList(csl);
                v[12].setTextColor( Color.parseColor( "#ff0000" ) );
                a[12]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[12].setBackgroundTintList(csl);
                v[12].setTextColor( Color.parseColor( "#000000" ) );
                a[12]=0;
            }
        }
    });
    v[13].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[13]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[13].setBackgroundTintList(csl);
                v[13].setTextColor( Color.parseColor( "#ff0000" ) );
                a[13]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[13].setBackgroundTintList(csl);
                v[13].setTextColor( Color.parseColor( "#000000" ) );
                a[13]=0;
            }
        }
    });
    v[14].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[14]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[14].setBackgroundTintList(csl);
                v[14].setTextColor( Color.parseColor( "#ff0000" ) );
                a[14]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[14].setBackgroundTintList(csl);
                v[14].setTextColor( Color.parseColor( "#000000" ) );
                a[14]=0;
            }
        }
    });
    v[15].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[15]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[15].setBackgroundTintList(csl);
                v[15].setTextColor( Color.parseColor( "#ff0000" ) );
                a[15]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[15].setBackgroundTintList(csl);
                v[15].setTextColor( Color.parseColor( "#000000" ) );
                a[15]=0;
            }
        }
    });
    v[16].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[16]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[16].setBackgroundTintList(csl);
                v[16].setTextColor( Color.parseColor( "#ff0000" ) );
                a[16]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[16].setBackgroundTintList(csl);
                v[16].setTextColor( Color.parseColor( "#000000" ) );
                a[16]=0;
            }
        }
    });
    v[17].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[17]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[17].setBackgroundTintList(csl);
                v[17].setTextColor( Color.parseColor( "#ff0000" ) );
                a[17]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[17].setBackgroundTintList(csl);
                v[17].setTextColor( Color.parseColor( "#000000" ) );
                a[17]=0;
            }
        }
    });
    v[18].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[18]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[18].setBackgroundTintList(csl);
                v[18].setTextColor( Color.parseColor( "#ff0000" ) );
                a[18]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[18].setBackgroundTintList(csl);
                v[18].setTextColor( Color.parseColor( "#000000" ) );
                a[18]=0;
            }
        }
    });
    v[19].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a[19]==0) {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFA8507});
                v[19].setBackgroundTintList(csl);
                v[19].setTextColor( Color.parseColor( "#ff0000" ) );
                a[19]=1;
            }
            else {
                ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFFFFFFFF});
                v[19].setBackgroundTintList(csl);
                v[19].setTextColor( Color.parseColor( "#000000" ) );
                a[19]=0;
            }
        }
    });

}
public void checkGhe(){
    try{
        VeService veService = APIClientVe.getClient().create(VeService.class);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String test = "array"+String.valueOf(maphim);
        int id = getResources().getIdentifier(test,"array",getPackageName());
        String array[] = getResources().getStringArray(id);
        final int masuat = (int)spinerSuat.getSelectedItemId();
        final String test2 = dateFormat.format(date);
        final int marap = Integer.parseInt(array[index1]);


        final ArrayList<Integer> temp = new ArrayList<Integer>();
        Call call = veService.findAll();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<Ve> listve = (List<Ve>)response.body();
                for(int i=0;i<listve.size();i++)
                {
                    if(listve.get(i).getMAGHE()!=0 && listve.get(i).getNGAYDAT().equals(test2) && listve.get(i).getMARAP() == marap && listve.get(i).getMASUAT() == masuat+1)
                    {
                        temp.add(listve.get(i).getMAGHE());
                    }
                }
                taoGhe(temp);
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

