package com.example.calculatortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 数字按键
     */

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

    private Button btn_point;//小数点
    private Button btn_equal;//等号
    private Button btn_clean;//全清
    private Button btn_delete;//后退一步
    private Button btn_div;//除
    private Button btn_mul;//乘
    private Button btn_add;//加
    private Button btn_sub;//减

    private Button btn_numeration;

    private EditText editText;

    private double ret;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intView();

        btn_numeration = (Button)findViewById(R.id.btn_numeration);
        btn_numeration.setOnClickListener(this);
    }

    /**
    初始化view
     */
    private void intView() {
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

        btn_point = (Button)findViewById(R.id.btn_point);
        btn_point.setOnClickListener(this);

        btn_clean = (Button)findViewById(R.id.btn_clean);
        btn_clean.setOnClickListener(this);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_sub = (Button)findViewById(R.id.btn_sub);
        btn_sub.setOnClickListener(this);
        btn_mul = (Button)findViewById(R.id.btn_mul);
        btn_mul.setOnClickListener(this);
        btn_div = findViewById(R.id.btn_div);
        btn_div.setOnClickListener(this);
        btn_equal = (Button)findViewById(R.id.btn_equal);
        btn_equal.setOnClickListener(this);

        editText = (EditText)findViewById(R.id.editText);

    }

    /**
    点击事件
     */

    public void onClick(View view){

        String str = editText.getText().toString();

        switch (view.getId()){
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
            case R.id.btn_point:


                editText.setText(str + ((Button)view).getText());
                break;

            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:

                editText.setText(str + " " + ((Button)view).getText() + " ");
                break;

            case R.id.btn_clean:
                editText.setText("");
                break;
            case R.id.btn_delete:
                if (!TextUtils.isEmpty(str)) {
                    editText.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.btn_equal:
                getResult();
                break;

            case R.id.btn_numeration:
                Intent intent = new Intent(MainActivity.this,NumerationActivity.class);
                startActivity(intent);
                break;



        }





    }

    private void getResult() {
        String newStr = editText.getText().toString();
        /**
        不能为空,也不能没有运算符
         */
        if (!TextUtils.isEmpty(newStr)) {
            if (!newStr.contains(" ")) {
                return;
            }

            String[] arr = newStr.split(" ");

            String str1 = arr[0];

            String opt = arr[1];

            String str2 = arr[2];

            Log.i("TAG",str1 + ":" + opt + ":" + str2);

            if (!TextUtils.isEmpty(str1) && !TextUtils.isEmpty(str2)) {
                double ret1 = Double.parseDouble(str1);
                double ret2 = Double.parseDouble(str2);

                if (opt.equals("+")) {

                    ret = ret1 + ret2;

                }else if (opt.equals("-")) {

                    if (ret1 > ret2){

                        ret = (ret1 - ret2);

                    }

                }else if (opt.equals("×")) {

                    ret = ret1 * ret2;

                }else if (opt.equals("÷")) {

                    if (ret2 == 0) {

                        ret = 0;

                    } else {

                        ret = ret1 / ret2;

                    }
                }

                if (!newStr.contains(".") && opt.equals("/")) {
                    int i = (int)ret;
                    editText.setText(""+i);
                } else {
                    editText.setText(""+ret);
                }
            }



        }

    }

}

