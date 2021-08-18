package com.example.game2048.view;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CardView extends FrameLayout {

    private int num = 0;//卡片的数量

    private TextView tv_num;//卡片的文字

    public CardView(Context context) {
        super(context);

        tv_num = new TextView(getContext());//实例化TextView
        tv_num.setTextSize(20); //卡片文字大小
        tv_num.setBackgroundColor(0xffBDB76A);//设置背景
        tv_num.setGravity(Gravity.CENTER);//设置居中

        LayoutParams lp = new LayoutParams(-1,-1);//填充整个容器
        lp.setMargins(10,10,0,0);


        addView(tv_num,lp);//添加View；


        setNum(0);//数量默认值0

    }

     /**
     * 获取卡片数量
      */
    public int getNum() {
        return num;
    }

    /**
     * 设置数字
     */
    public void setNum(int num) {
        this.num = num;
        if (num <= 0) {
            tv_num.setText("");
        } else {
            tv_num.setText(num+"");
        }

        switch (num) {

            case 0:
                tv_num.setBackgroundColor(0xffBDB76A);
                break;
            case 2:
                tv_num.setBackgroundColor(0xFFEEE8AB);
                break;
            case 4:
                tv_num.setBackgroundColor(0xFFC7FFEC);
                break;
            case 8:
                tv_num.setBackgroundColor(0xFFDEA681);
                break;
            case 16:
                tv_num.setBackgroundColor(0xFFBDB76A);
                break;
            case 32:
                tv_num.setBackgroundColor(0xFF00FF80);
                break;
            case 64:
                tv_num.setBackgroundColor(0xFF008573);
                break;
            case 128:
                tv_num.setBackgroundColor(0xFF376956);
                break;
            case 256:
                tv_num.setBackgroundColor(0xFF483C32);
                break;
            case 512:
                tv_num.setBackgroundColor(0xFF495A80);
                break;
            case 1024:
                tv_num.setBackgroundColor(0xFF9966CC);
                break;
            case 2048:
                tv_num.setBackgroundColor(0xFFFD5B78);
                break;

                default:
                    tv_num.setBackgroundColor(0xFF3C3A32);
        }
    }

    /**
     * 判断两个数字是否相同
     * @param card
     * @return
     */
    public boolean equal(CardView card) {

        return this.getNum() == card.getNum();
    }
}
