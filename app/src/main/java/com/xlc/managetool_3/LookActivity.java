package com.xlc.managetool_3;

import android.app.Activity;
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

public class LookActivity extends Activity {


    int r = Color.WHITE;

    Button showDatabase;
    Button allButton;
    TextView lookText1;
    TextView lookText2;
    TextView lookText3;
    TextView lookText4;
    TextView lookText5;
    TextView lookText6;
    TextView lookText7;
    TextView lookText8;
    ArrayList<TextView> lookTextList;

    // the number of page
    // 代表有多少页
    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);

        // init the database
        SQLiteDatabase db = LitePal.getDatabase();
        showDatabase = (Button)findViewById(R.id.show_databse);
        allButton = (Button)findViewById(R.id.all_tool);

        initText();

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
                // 1.get all the tool info from the database
                ArrayList<Tools> allTools = (ArrayList<Tools>)DataSupport.findAll(Tools.class);
                // 2. get the amount of list
                int total = allTools.size();
                Toast.makeText(LookActivity.this, "total : " + total, Toast.LENGTH_SHORT).show();
                // 3. get the page number
                if (total / 8 > 1) {
                    page = total / 8;
                }else {
                    page = 1;
                }
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





            }
        });


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
        lookTextList = new ArrayList<>();
        lookTextList.add(lookText1);
        lookTextList.add(lookText2);
        lookTextList.add(lookText3);
        lookTextList.add(lookText4);
        lookTextList.add(lookText5);
        lookTextList.add(lookText6);
        lookTextList.add(lookText7);
        lookTextList.add(lookText8);

    }


}
