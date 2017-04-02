package com.xlc.managetool_3;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LookActivity extends Activity implements View.OnClickListener {


    int r = Color.WHITE;

    Button showDatabase;
    Button allButton;
    // next page
    Button nextPage;
    // last page
    Button lastPage;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);

        // init the database
        SQLiteDatabase db = LitePal.getDatabase();
        showDatabase = (Button)findViewById(R.id.show_databse);
        allButton = (Button)findViewById(R.id.all_tool);

        initText();


        // 1.get all the tool info from the database
        allTools = (ArrayList<Tools>)DataSupport.findAll(Tools.class);
        // 2. get the amount of list
        total = allTools.size();

        /*
            turn to the view of database
         */
        showDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LookActivity.this,DatabaseView.class);
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
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);

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
                if (total > 8){
                    for (int i = 0 ;i < 8 ; i++){
                        lookTextList.get(i).setText(allTools.get(i).getName());
                    }
                }else{
                    for (int i = 0 ;i < total;i++){
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
                page ++;
                if (page > max){
                    page = max;
                }
                pageNumber.setText(page + " / " + max);
                // show the data according to the page
                // 根据页码显示数据
                showDataAccordPage(lookTextList,allTools,page);

                // init background color
                initTextViewBackground();
            }
        });

        lastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page --;
                if (page < 1){
                    page = 1;
                }
                int max = total / 8;
                pageNumber.setText(page + " / " + max);

                // show the data according to the page
                // 根据页码显示数据
                showDataAccordPage(lookTextList,allTools,page);

                // init background color
                initTextViewBackground();
            }
        });

        // select the data on screen
        // 通过触屏选择数据
        addTextViewListener(lookTextList);


    }

    private void addTextViewListener(ArrayList<TextView> lookTextList) {
        for (int i = 0 ;i < lookTextList.size();i++){
            lookTextList.get(i).setOnClickListener(this);
        }
    }

    // show the data according to the page
    // 根据页码显示数据
    private void showDataAccordPage(ArrayList<TextView> lookTextList, ArrayList<Tools> allTools, int page) {
        // 0. add the data 8 times in for loop
        for (int i = 0;i < 8;i++) {
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
    private void initText() {
        lookText1 = (TextView)findViewById(R.id.look_text1);
        lookText2 = (TextView)findViewById(R.id.look_text2);
        lookText3 = (TextView)findViewById(R.id.look_text3);
        lookText4 = (TextView)findViewById(R.id.look_text4);
        lookText5 = (TextView)findViewById(R.id.look_text5);
        lookText6 = (TextView)findViewById(R.id.look_text6);
        lookText7 = (TextView)findViewById(R.id.look_text7);
        lookText8 = (TextView)findViewById(R.id.look_text8);
        pageNumber = (TextView)findViewById(R.id.look_page);
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
        nextPage = (Button)findViewById(R.id.look_next_page);
        lastPage = (Button)findViewById(R.id.look_last_page);

        // init the background color
        initTextViewBackground();

        // init the selected tool
        // 初始化被选中的工具的索引
        selected = new ArrayList<>();

    }

    private void initTextViewBackground() {
        for (int i = 0 ;i < lookTextList.size();i++){
            lookTextList.get(i).setBackgroundColor(Color.WHITE);
        }
    }


    // all the text view on click listen
    // 所有显示数据的text view 的点击监听
    @Override
    public void onClick(View v) {
        // change the background color
        // 改变被选择的text view的背景颜色
        if (r == Color.WHITE){
            r = Color.GREEN;
            // selected the tool

            switch (v.getId()){
                case R.id.text_view1 : n = 1;
                case R.id.text_view2 : n = 2;
                case R.id.text_view3 : n = 3;
                case R.id.text_view4 : n = 4;
                case R.id.text_view5 : n = 5;
                case R.id.text_view6 : n = 6;
                case R.id.text_view7 : n = 7;
                case R.id.text_view8 : n = 8;
            }
            selected.add((page - 1 ) * 8 + n);

        }else{
            r = Color.WHITE;
        }
        v.setBackgroundColor(r);


    }
}
