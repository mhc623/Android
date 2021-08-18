package com.example.game2048.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import com.example.game2048.GameActivity;

import java.util.ArrayList;
import java.util.List;

public class GameView extends GridLayout {

    //private final static String TAG = "GameView";

    private CardView[][] cards = new CardView[4][4];//设置二维数组记录值

    private List<Point>point = new ArrayList<>();

    //private boolean isAdd;


    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context,AttributeSet attrs,int defStyleAttrs) {
        super(context,attrs,defStyleAttrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {

        //setBackgroundColor(Color.parseColor("#EEE8AB"));//设置背景

        setColumnCount(4);//设置行数


        /**
         * 手势监听
         */
        setOnTouchListener(new OnTouchListener() {

            private float startX, startY, offsetX, offsetY;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {

                    /**
                     * 按下操作
                     */
                    case MotionEvent.ACTION_DOWN:
                        /**
                         * 记录坐标
                         */
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;

                    /**
                     * 抬起操作
                     */
                    case MotionEvent.ACTION_UP:
                        /**
                         * 偏移量
                         */
                        offsetX = motionEvent.getX() - startX;
                        offsetY = motionEvent.getY() - startY;

                        /**
                         * 计算方向
                         * 如果offsetX的绝对值大于offsetY的绝对值，水平方向；
                         */
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {

                            if (offsetX < -5) {
                                //Log.i(TAG, "left");
                                left();
                            } else if (offsetX > 5) {
                                //Log.i(TAG, "right");
                                right();
                            }
                        } else {
                            /**
                             *竖直方向
                             */
                            if (offsetY < -5) {
                                //Log.i(TAG, "top");
                                top();
                            } else if (offsetY > 5) {
                                //Log.i(TAG, "bottom");
                                bottom();
                            }
                        }
                        break;
                }
                return true;
            }
        });

    }

    /**
     * 随机数,开始游戏时随机生成2或者4
     * 生成2的概率为0.9,4为0.1
     */
    private void addRandom() {
        //存放之前清空方块
        point.clear();
        //对所有位置进行遍历
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //避免重复，位置上有值时不添加
                if (cards[j][i].getNum() <= 0) {
                    point.add(new Point(j,i));
                }
            }
        }
        //遍历结束,获得方块
        Point p = point.remove((int)(Math.random() * point.size()));

        cards[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
    }


    /**
     * 开始游戏
     */
    private void startGame() {

        GameActivity.getGameActivity().clearScore();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cards[j][i].setNum(0);
            }
        }
        addRandom();
        addRandom();
    }

    private void left() {
        boolean isAdd = false;
        /**
         * 2种情况
         * 左边为空，直接滑动到最左边；
         * 左边不为空，若值相等，相加滑到最左边；若值不相等两块相连
         */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //往左滑，一行一行遍历
                for (int j2 = j+1; j2 < 4; j2++) {
                    //遍历取到值
                    if (cards[j2][i].getNum() > 0) {
                        //如果当前位置为0,将值放在此位置上
                        if (cards[j][i].getNum() <= 0) {
                            cards[j][i].setNum(cards[j2][i].getNum());
                            //将原来位置清除
                            cards[j2][i].setNum(0);
                            //继续遍历
                            j--;
                            isAdd = true;
                        //当有值并且相等时,乘2,原来位置为空
                        }else if (cards[j][i].equal(cards[j2][i])) {
                            cards[j][i].setNum(cards[j][i].getNum() * 2);
                            cards[j2][i].setNum(0);
                            GameActivity.getGameActivity().addScore(cards[j][i].getNum());
                            isAdd = true;
                        }
                        break;
                    }
                }
            }
        }
        if (isAdd) {
            addRandom();
            endGame();
        }
    }

    private void right() {
        boolean isAdd = false;
        /**
         * 2种情况
         * 左边为空，直接滑动到最左边；
         * 左边不为空，若值相等，相加滑到最左边；若值不相等两块相连
         */
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                //往左滑，一行一行遍历
                for (int j2 = j-1; j2 >= 0; j2--) {
                    //遍历取到值
                    if (cards[j2][i].getNum() > 0) {
                        //如果当前位置为0,将值放在此位置上
                        if (cards[j][i].getNum() <= 0) {
                            cards[j][i].setNum(cards[j2][i].getNum());
                            //将原来位置清除
                            cards[j2][i].setNum(0);
                            //继续遍历
                            j++;
                            isAdd = true;
                            //当有值并且相等时,乘2,原来位置为空
                        }else if (cards[j][i].equal(cards[j2][i])) {
                            cards[j][i].setNum(cards[j][i].getNum() * 2);
                            cards[j2][i].setNum(0);
                            GameActivity.getGameActivity().addScore(cards[j][i].getNum());
                            isAdd = true;
                        }
                        break;
                    }
                }
             }
        }
        if (isAdd) {
            addRandom();
            endGame();
        }
    }

    private void top() {
        boolean isAdd = false;
        /**
         * 2种情况
         * 左边为空，直接滑动到最左边；
         * 左边不为空，若值相等，相加滑到最左边；若值不相等两块相连
         */
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                //往左滑，一行一行遍历
                for (int i2 = i+1; i2 < 4; i2++) {
                    //遍历取到值
                    if (cards[j][i2].getNum() > 0) {
                        //如果当前位置为0,将值放在此位置上
                        if (cards[j][i].getNum() <= 0) {
                            cards[j][i].setNum(cards[j][i2].getNum());
                            //将原来位置清除
                            cards[j][i2].setNum(0);
                            //继续遍历
                            i--;
                            isAdd = true;
                            //当有值并且相等时,乘2,原来位置为空
                        }else if (cards[j][i].equal(cards[j][i2])) {
                            cards[j][i].setNum(cards[j][i].getNum() * 2);
                            cards[j][i2].setNum(0);
                            GameActivity.getGameActivity().addScore(cards[j][i].getNum());
                            isAdd = true;
                        }
                        break;
                    }
                }
            }
        }
        if (isAdd) {
            addRandom();
            endGame();
        }
    }

    private void bottom() {
        boolean isAdd = false;
        /**
         * 2种情况
         * 左边为空，直接滑动到最左边；
         * 左边不为空，若值相等，相加滑到最左边；若值不相等两块相连
         */
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 0; i--) {
                //往左滑，一行一行遍历
                for (int i2 = i-1; i2 >= 0; i2--) {
                    //遍历取到值
                    if (cards[j][i2].getNum() > 0) {
                        //如果当前位置为0,将值放在此位置上
                        if (cards[j][i].getNum() <= 0) {
                            cards[j][i].setNum(cards[j][i2].getNum());
                            //将原来位置清除
                            cards[j][i2].setNum(0);
                            //继续遍历
                            i++;
                            isAdd = true;
                            //当有值并且相等时,乘2,原来位置为空
                        }else if (cards[j][i].equal(cards[j][i2])) {
                            cards[j][i].setNum(cards[j][i].getNum() * 2);
                            cards[j][i2].setNum(0);
                            GameActivity.getGameActivity().addScore(cards[j][i].getNum());
                            isAdd = true;
                        }
                        break;
                    }
                }
            }
        }
        if (isAdd) {
            addRandom();
            endGame();
        }
    }

    /**
     * 确定位置
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w,h)-10)/4;

        int cardHeight = cardWidth;

        addCard(cardWidth,cardHeight);

        startGame();

    }

    /**
     * 添加卡片，参数为宽高
     * @param cardWidth
     * @param cardHeight
     */
    private void addCard(int cardWidth,int cardHeight) {

        CardView card;//创建卡片类

        //循环添加4×4
        for (int i = 0;i < 4;i++) {
            for (int j = 0; j < 4; j++) {
                card = new CardView(getContext());
                card.setNum(0);
                //addView(card,cardWidth,cardHeight);
                cards[j][i] = card;
                addView(card,cardWidth,cardHeight);
            }
        }
    }


    private void endGame() {
        boolean isEnd = true;
        ALL:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cards[j][i].getNum() == 0
                    || (j > 0 && cards[j][i].equals(cards[j-1][i]))//左
                    || (j < 3 && cards[j][i].equals(cards[j+1][i]))//右
                    || (i > 0 && cards[j][i].equals(cards[j][i-1]))//上
                    || (i < 3 && cards[j][i].equals(cards[j][i+1])))//下
                {
                    isEnd = false;
                    break ALL;
                }
            }
        }
        if (isEnd) {
            new AlertDialog.Builder(getContext())
                    .setTitle("游戏结束")
                    .setMessage("是否重新开始？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            startGame();
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                        }
                    }).show();
        }
    }
}
