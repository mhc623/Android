package com.example.calculatortest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NumerationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_a;
    private Button btn_b;
    private Button btn_c;
    private Button btn_d;
    private Button btn_e;
    private Button btn_f;
    //private Button btn_point;

    private Button btn_clean;
    private Button btn_delete;

    private Button btn_two;
    private Button btn_st;

    private EditText meT1;
    private TextView meT2;
    private TextView meT3;

    private int i;
    private int j;
    private String ret1;
    private String ret2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeration);

        intView();
    }

    /**
     * 初始化View
     */
    public void intView() {
        btn_0 = (Button)findViewById(R.id.btn_0);
        btn_0.setOnClickListener(this);
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = (Button)findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_5 = (Button)findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
        btn_6 = (Button)findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
        btn_7 = (Button)findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);
        btn_8 = (Button)findViewById(R.id.btn_8);
        btn_8.setOnClickListener(this);
        btn_9 = (Button)findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);
        /**
         * btn_a = (Button)findViewById(R.id.btn_a);
        btn_a.setOnClickListener(this);
        btn_b = (Button)findViewById(R.id.btn_b);
        btn_b.setOnClickListener(this);
        btn_c = (Button)findViewById(R.id.btn_c);
        btn_c.setOnClickListener(this);
        btn_d = (Button)findViewById(R.id.btn_d);
        btn_d.setOnClickListener(this);
        btn_e = (Button)findViewById(R.id.btn_e);
        btn_e.setOnClickListener(this);
        btn_f = (Button)findViewById(R.id.btn_f);
        btn_f.setOnClickListener(this);
        //btn_point = (Button)findViewById(R.id.btn_point);
        //btn_point.setOnClickListener(this);

         */

        btn_clean = findViewById(R.id.btn_clean);
        btn_clean.setOnClickListener(this);
        btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);

        btn_two = (Button)findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);
        btn_st = (Button)findViewById(R.id.btn_st);
        btn_st.setOnClickListener(this);

        meT1 = (EditText)findViewById(R.id.meT1);
        meT2 = (TextView)findViewById(R.id.meT2);
        meT3 = (TextView)findViewById(R.id.meT3);
    }

    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {

        String str = meT1.getText().toString();
        //String str2 = meT2.getText().toString();
        //String str3 = meT3.getText().toString();

        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                meT1.setText(str+((Button)view).getText());
                break;

            case R.id.btn_clean:
                meT1.setText("");
                meT2.setText("");
                meT3.setText("");
                break;
            case R.id.btn_delete:
                if (!TextUtils.isEmpty(str)) {
                    meT1.setText(str.substring(0,str.length()-1));
                }
                break;

            case R.id.btn_two:
                getBinary();
                break;
            case R.id.btn_st:
                getNexadecimal();
                break;

        }

    }

    /**
     * 十进制转二进制
     */
    private void getBinary() {
        String newStr = meT1.getText().toString();

        if (!TextUtils.isEmpty(newStr)) {

            i = Integer.parseInt(newStr);

            ret1 = Integer.toBinaryString(i);

            meT2.setText(ret1);
        }
    }

    /**
     * 十进制转十六进制
     */
    private void getNexadecimal() {

        String newStr = meT1.getText().toString();

        if (!TextUtils.isEmpty(newStr)) {

            j = Integer.parseInt(newStr);

            ret2 = Integer.toHexString(j);

            meT3.setText(ret2);

        }

        //Integer.toHexString(int i);

    }
}
