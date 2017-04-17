package com.xlc.managetool_3;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xlc.tool.ManageTool;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;

public class LookActivity extends Activity implements View.OnClickListener {
    int r = Color.WHITE;
    ColorDrawable colorDrawableNow = null;
    Drawable drawable = null;
    Drawable drawableWhite = null;
    Drawable drawableGreen = null;
    ArrayList<Drawable> drawableList = null;
    // turn to data edit activity
    Button showDatabase;
    // show all the tools
    Button allButton;
    // next page
    Button nextPage;
    // last page
    Button lastPage;
    // borrow list check
    // 查看工具借用情况
    Button borrowButton;
    // confirm the borrow request
    // 选择完成
    Button select_over;
    TextView lookText1;
    TextView lookText2;
    TextView lookText3;
    TextView lookText4;
    TextView lookText5;
    TextView lookText6;
    TextView lookText7;
    TextView lookText8;
    ArrayList<TextView> lookTextList;
    TextView pageNumber;
    // the number of page
    // 代表有多少页
    int page = 0;
    // the record of data
    int total = 0;
    // all the tools record
    ArrayList<Tools> allTools;
    // the selected tool
    ArrayList<Integer> selected;
    // the number of selected in one page
    int n;
    // handler mechanism
//    Handler handler;

    // all data control service
    AllDataControl allDataControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);

        // init the database
        SQLiteDatabase db = LitePal.getDatabase();
        showDatabase = (Button) findViewById(R.id.look_activity_show_databse);
        allButton = (Button) findViewById(R.id.look_activity_all_tool);
        colorDrawableNow = (ColorDrawable) getResources().getDrawable(R.drawable.white_text_view);
        // initial drawable and it's array list
        // 初始化背景颜色和其列表
        drawableWhite = getResources().getDrawable(R.drawable.white_text_view);
        drawableGreen = getResources().getDrawable(R.drawable.green_text_view);
        drawableList = new ArrayList<>();
        drawableList.add(drawableWhite);
        drawableList.add(drawableGreen);
        drawable = drawableList.get(0);
        drawableWhite = drawableList.get(1);
        drawableGreen = drawableList.get(1);

        initText();
        // show the init data
        // 显示所有工具的第一页
        initGraphic();

        // 1.get all the tool info from the database
        allTools = (ArrayList<Tools>) DataSupport.findAll(Tools.class);
        // 2. get the amount of list
        total = allTools.size();

        // start the data control service
        startService(new Intent(this,AllDataControl.class));

        /*
            turn to the view of database
         */
        showDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LookActivity.this, DatabaseView.class);
                startActivity(intent);
            }
        });

        // all tool show
        // 显示所有工具，同时隐藏软键盘
        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide the soft keyboard
                // 隐藏软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        getApplicationContext().INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                // show all the data
                // 显示所有数据

                Toast.makeText(LookActivity.this, "total : " + total, Toast.LENGTH_SHORT).show();
//                // 3. get the page number
//                if (total / 8 > 1) {
//                    page = total / 8;
//                }else {
//                    page = 1;
//                }
                // 4. show the first page
                if (total > 8) {
                    for (int i = 0; i < 8; i++) {
                        lookTextList.get(i).setText(allTools.get(i).getName());
                    }
                } else {
                    for (int i = 0; i < total; i++) {
                        lookTextList.get(i).setText(allTools.get(i).getName());
                    }
                }
                // 5. page number
                int max = total / 8;
                pageNumber.setText(page + " / " + max);


            }
        });


        // next page
        // 翻页
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // judge the page range
                // 判断page范围
                int max = total / 8;
                page++;
                if (page > max) {
                    page = max;
                }
                pageNumber.setText(page + " / " + max);
//                // save the selected tools
//                // 保存选中的工具
//                for (int i = 0 ;i < lookTextList.size();i++){
//                    ColorDrawable cd = (ColorDrawable)lookTextList.get(i).getBackground();
//                    if (cd.getColor() == 0x0f0){
//                        Toast.makeText(LookActivity.this, "select : " + i , Toast.LENGTH_SHORT).show();
//                    }
//                }

                // show the data according to the page
                // 根据页码显示数据
                showDataAccordPage(lookTextList, allTools, page);

                // init background color
                //  初始化显示的背景颜色
                initTextViewBackground();
            }
        });

        lastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page--;
                if (page < 1) {
                    page = 1;
                }
                int max = total / 8;
                pageNumber.setText(page + " / " + max);

                // show the data according to the page
                // 根据页码显示数据
                showDataAccordPage(lookTextList, allTools, page);

                // init background color
                initTextViewBackground();

                // send the massage
//                DataThread dataThread = new DataThread();
//                Thread thread = new Thread(dataThread);
//                System.out.println("start thread ...");
//                thread.start();

                // 2
//                Message message = new Message();
//                message.what = ManageTool.FLUSH_DATA_OVER;
//                handler.sendMessage(message);

            }
        });

        // borrow button listener
        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LookActivity.this, BorrowListAcivity.class);
                startActivity(intent);
            }
        });

        // when the select operation over
        // 选择完毕跳转到确认页面
        select_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear the empty element
                // 将选择的数组中空的数据去除
                ArrayList<Integer> selectNoEmpty = new ArrayList<Integer>();
                for (int i = 0 ; i < selected.size();i++){
                    if (selected.get(i) != 0){
                        selectNoEmpty.add(selected.get(i));
                    }
                }
                selected = selectNoEmpty;

                // if had selected the tools
//                if (selected.size() != 0) {
//                    AllDataControl.selected_tools = selected;
//                    Intent intent = new Intent(LookActivity.this, ConfirmSelect.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(LookActivity.this, "请先选择工具", Toast.LENGTH_SHORT).show();
//                }
                if (AllDataControl.selected_tools.size() != 0){
//                    AllDataControl.selected_tools = selected;
                    Intent intent = new Intent(LookActivity.this, ConfirmSelect.class);
                    startActivity(intent);
                }


            }
        });

        // select the data on screen
        // 通过触屏选择数据
        addTextViewListener(lookTextList);

        // handler mechanism
//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                // flush the ui
//                // 如果数据发生改变，收到消息，更新ui
//                if (msg.what == ManageTool.FLUSH_DATA_OVER){
//                    lookText1.setText("data flush over..."   );
//                }
//            }
//        };

    }

    // show the data
    // 第一次显示初始化的数据
    private void initGraphic() {
        // make the data state init
        // 将数据状态置为初始状态
//        AllDataControl.data_state = AllDataControl.DATA_STATE_INIT;
//        Message message = new Message();
//        message.what = AllDataControl.DATA_STATE_INIT;
//        handler = new Handler();
//        handler.sendMessage(message);
        if (AllDataControl.display != null){
            if (AllDataControl.display.size() >= 8){
                for (int i = 0 ;i < 8;i++){
                    lookTextList.get(i).setText(AllDataControl.display.get(i));
                }
            }
            if(AllDataControl.display.size() < 8){
                for (int i=0;i < AllDataControl.display.size();i++){
                    lookTextList.get(i).setText(AllDataControl.display.get(i));
                }
            }
        }
        if (AllDataControl.display == null){
            ArrayList<Tools> tools = (ArrayList)DataSupport.findAll(Tools.class);
            ArrayList<String> content = new ArrayList<>();
            for (int i = 0;i < tools.size();i++){
                content.add(tools.get(i).getName() + "  数量 ： " + tools.get(i).getAmount());
                if (i < 8){
                    lookTextList.get(i).setText(content.get(i));
                }
            }

        }

        // init the selected tools list
        AllDataControl.selected_tools = new ArrayList<>();
        AllDataControl.selectedToolsMap = new HashMap<>();

    }

    // select the tools listener
    private void addTextViewListener(ArrayList<TextView> lookTextList) {
        for (int i = 0; i < lookTextList.size(); i++) {
            lookTextList.get(i).setOnClickListener(this);
        }
    }

    // show the data according to the page
    // 根据页码显示数据
    private void showDataAccordPage(ArrayList<TextView> lookTextList, ArrayList<Tools> allTools, int page) {
        // 0. add the data 8 times in for loop
        for (int i = 0; i < 8; i++) {
            // 1. according to the page select the data
            // 根据页码选择数据
            /*
                page : 1,2,3,4,5,6,
                index: 0,9,17,25
             */
            lookTextList.get(i).setText(allTools.get((page - 1) * 8 + i).getName());

        }

    }

    // add the text view into array list
    // 初始化显示界面
    private void initText() {
        lookText1 = (TextView) findViewById(R.id.look_activity_look_text1);
        lookText2 = (TextView) findViewById(R.id.look_activity_look_text2);
        lookText3 = (TextView) findViewById(R.id.look_activity_look_text3);
        lookText4 = (TextView) findViewById(R.id.look_activity_look_text4);
        lookText5 = (TextView) findViewById(R.id.look_activity_look_text5);
        lookText6 = (TextView) findViewById(R.id.look_activity_look_text6);
        lookText7 = (TextView) findViewById(R.id.look_activity_look_text7);
        lookText8 = (TextView) findViewById(R.id.look_activity_look_text8);
        pageNumber = (TextView) findViewById(R.id.look_activity_look_page);
        lookTextList = new ArrayList<>();
        lookTextList.add(lookText1);
        lookTextList.add(lookText2);
        lookTextList.add(lookText3);
        lookTextList.add(lookText4);
        lookTextList.add(lookText5);
        lookTextList.add(lookText6);
        lookTextList.add(lookText7);
        lookTextList.add(lookText8);

        // button
        nextPage = (Button) findViewById(R.id.look_activity_look_next_page);
        lastPage = (Button) findViewById(R.id.look_activity_look_last_page);
        borrowButton = (Button) findViewById(R.id.look_activity_borrow_button);
        select_over = (Button) findViewById(R.id.look_activity_select_sure);

        // init the background color
        initTextViewBackground();

        // init the selected tool
        // 初始化被选中的工具的索引
        selected = new ArrayList<>();

    }

    // init the text view background into white
    // 将背景颜色设置为白色
    private void initTextViewBackground() {
        for (int i = 0; i < lookTextList.size(); i++) {
//            lookTextList.get(i).setBackgroundColor(Color.WHITE);
//            ColorDrawable colorDrawable = (ColorDrawable)getResources().getDrawable(R.drawable.white_text_view);
//            colorDrawableNow = new ColorDrawable();
//            colorDrawableNow = colorDrawable;
//            lookTextList.get(i).setBackgroundDrawable(colorDrawable);

//            Drawable drawable = getResources().getDrawable(R.drawable.white_text_view);
            drawable = drawableList.get(0);
            lookTextList.get(i).setBackground(drawable);
        }
    }

    // all the text view on click listen
    // 所有显示数据的text view 的点击监听
    @Override
    public void onClick(View v) {
        // change the background color
        // 改变被选择的text view的背景颜色
//
//        ColorDrawable colorDrawableGreen = (ColorDrawable)getResources().getDrawable(R.drawable.green_text_view);
//        ColorDrawable colorDrawableWhite = (ColorDrawable)getResources().getDrawable(R.drawable.white_text_view);
//        if (colorDrawableNow == colorDrawableGreen){
//            colorDrawableNow = colorDrawableWhite;
//        }
//        else if (colorDrawableNow == colorDrawableWhite){
//            colorDrawableNow = colorDrawableGreen;
//        v.setBackgroundDrawable(colorDrawableNow);

//        Drawable drawableWhite = getResources().getDrawable(R.drawable.white_text_view);
//        Drawable drawableGreen = getResources().getDrawable(R.drawable.green_text_view);
        if (drawable == drawableList.get(1)){
            drawable = drawableList.get(0);
            v.setBackground(drawable);
//            Toast.makeText(this, "init color is same", Toast.LENGTH_SHORT).show();
        }else if (drawable == drawableList.get(0)){
            drawable = drawableList.get(1);
            v.setBackground(drawable);
//            Toast.makeText(this, "init color is same 2", Toast.LENGTH_SHORT).show();
        }
//        if (v.getBackground() == drawable){
//            Toast.makeText(this, "background equal the drawable", Toast.LENGTH_SHORT).show();
//        }


//        Toast.makeText(this, "click ... ", Toast.LENGTH_SHORT).show();

////        v.setBackground(drawableGreen);
//        if (v.getBackground() == drawableGreen){
//            v.setBackground(drawableWhite);
//            return;
////            Toast.makeText(this, "green ", Toast.LENGTH_SHORT).show();
//        }
//        if (v.getBackground() == drawableWhite){
//            v.setBackground(drawableGreen);
//            return;
////            Toast.makeText(this, "white ", Toast.LENGTH_SHORT).show();
//        }
//        if (v.getBackground() != drawableGreen && v.getBackground() != drawableWhite){
//            v.setBackground(drawableWhite);
//            return;
//        }

//        if ((ColorDrawable)v.getBackground() == colorDrawableWhite){
//            v.setBackgroundDrawable(colorDrawableGreen);
//            return;
//        }
//        if ((ColorDrawable)v.getBackground() == colorDrawableGreen){
//            v.setBackgroundDrawable(colorDrawableWhite);
//            return;
//        }
//        if ((ColorDrawable)v.getBackground() != colorDrawableWhite){
//            v.setBackgroundDrawable(colorDrawableWhite);
//            return;
//        }


//        if (r == Color.WHITE) {
//            r = Color.GREEN;
//            // selected the tool
//
//            switch (v.getId()) {
//                case R.id.look_activity_look_text1:
//                    n = 1;
//                case R.id.look_activity_look_text2:
//                    n = 2;
//                case R.id.look_activity_look_text3:
//                    n = 3;
//                case R.id.look_activity_look_text4:
//                    n = 4;
//                case R.id.look_activity_look_text5:
//                    n = 5;
//                case R.id.look_activity_look_text6:
//                    n = 6;
//                case R.id.look_activity_look_text7:
//                    n = 7;
//                case R.id.look_activity_look_text8:
//                    n = 8;
//            }
////            selected.add((page - 1) * 8 + n);
//
//        } else if (r == Color.GREEN){
//            // cancer the select
//            // 由绿色变为白色，意味着取消这个工具的选择
////            int index = selected.indexOf((page - 1) * 8 + n);
////            selected.remove(index);
////            for (int i = selected.size()-1; i >=0 ; i--) {
////                if ("b".equals(selected.get(i))) {
////                    selected.remove(i);
////                }
////            }
//            r = Color.WHITE;
//        }
//        v.setBackgroundColor(r);

        // save the selected tools
        // 保存选中的工具
//        int color = 0;
//        for (int i = 0 ;i < lookTextList.size();i++){
//            ColorDrawable cd = (ColorDrawable)lookTextList.get(i).getBackground();
//            color = cd.getColor();
//        }
//
//        Toast.makeText(this, "color : " + color, Toast.LENGTH_SHORT).show();

        // every times click item , update the select list
        // 每次点击都更新选择工具的容器
        for (int i = 0;i < lookTextList.size();i++) {
            if (lookTextList.get(i).getBackground()
                    == drawableList.get(1)){
//                AllDataControl.selected_tools.add(i);
                // 1 : selected
                // 1 : green
                AllDataControl.selectedToolsMap.put(i,1);
            }else {
                AllDataControl.selectedToolsMap.put(i,0);
            }
        }
//        String showSelected = " selected : " ;
        for (int i = 0 ;i < AllDataControl.selectedToolsMap.size();i++){
//            showSelected += AllDataControl.selectedToolsMap.get(i);
            // it's represent it has been selected
            // 1 : selected
            if (AllDataControl.selectedToolsMap.get(i) == 1){
//                AllDataControl.selected_tools.add(i + (page - 1) * 8);
                // for test
                AllDataControl.selected_tools.add(i);
            }
        }
        Toast.makeText(this, "selected tool amount : " + AllDataControl.selected_tools.size()
                , Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, " " + showSelected, Toast.LENGTH_SHORT).show();


    }

    // new thread deal the data
    // 再新的线程里处理数据操作
    class DataThread implements Runnable{
        @Override
        public void run() {
//            Looper.prepare();
//            Message message = new Message();
//            message.what = ManageTool.FLUSH_DATA_OVER;
//            handler.sendMessage(message);
//            System.out.println("run ... ");
//            Handler h = new Handler(){
//                @Override
//                public void handleMessage(Message msg) {
//                    if (msg.what == AllDataControl.DATA_STATE_INIT){
//                        ArrayList<Tools> list = (ArrayList)DataSupport.findAll(Tools.class);
//                        ArrayList<String> displayString = new ArrayList<>();
//                        for (int i = 0 ; i < list.size();i++){
//                            displayString.add(list.get(i).getName() + " 数量 : " + list.get(i).getAmount());
//                        }
//                        AllDataControl.display = displayString;
//                    }
//                }
//            };

        }
    }

}


/*
    2017.4.2
    * implement the borrow function
    * 实现借用功能

    2017.4.15
    * how to delete an element of array list
    * array list 中然后删除一共元素

    2017.4.16
    * use the hash map replace the array list
    * 用hash map代替array list 实现点击选择工具
    * next step deal with the data transfer between
    * look activity and confirm select activity
    * 下一步解决数据共享的问题，在两个activity之间
 */
