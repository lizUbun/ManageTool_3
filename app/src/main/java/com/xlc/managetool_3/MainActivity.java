package com.xlc.managetool_3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends Activity {

    Context mContext;
    Button sure;
    Button clear;
    EditText name;
    EditText pass_word;
    // 1 string is name , 2 string is password
    HashMap<String , String> user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db = LitePal.getDatabase();
//        LookActivity.initData();
        mContext = getApplicationContext();
        sure = (Button)findViewById(R.id.main_activity_sure);
        clear = (Button)findViewById(R.id.main_activity_clear);

        user = new HashMap<>();
        name = (EditText)findViewById(R.id.main_activity_name);
        pass_word = (EditText)findViewById(R.id.main_activity_pass_word);


        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the input info
                String input_name = name.getText().toString().trim();
                String input_password = pass_word.getText().toString().trim();
                check(input_name,input_password);
            }
        });

        /*
            clear the input
         */
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                pass_word.setText("");
            }
        });

        // add the data into tools
        for (int i = 0 ; i < 30 ;i++) {
            Tools tools2 = new Tools();
            tools2.setName("name :   " + i + "     " + System.currentTimeMillis());
            tools2.setAmount(1);
            tools2.saveThrows();

            User user = new User();
            user.setName("user" + i + System.currentTimeMillis());
            user.setPass_word("password");
            user.saveThrows();
        }

        //
//    int i = DataSupport.findAll(Tools.class).size();
//        Toast.makeText(this, "tools has  " + i, Toast.LENGTH_SHORT).show();
//    int j = DataSupport.findAll(User.class).size();
//        Toast.makeText(this, "user has " + j, Toast.LENGTH_SHORT).show();
    }




    private void check(String input_name, String input_password) {
        // 1 get all user info
        ArrayList<User> users = (ArrayList<User>)DataSupport.findAll(User.class);
        for (int i = 0;i < user.size();i++){
            user.put(users.get(i).getName(),users.get(i).getPass_word());
        }
        // 2 check
        // and if succeed then turn to look the tool list
        if (input_name.equals("root") && input_password.equals("root")) {
            Intent intent = new Intent(MainActivity.this, LookActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "请输入正确登陆信息",
                    Toast.LENGTH_SHORT).show();

        }

        // 3 record this login
        User user = new User();
        user.setName(input_name);
        user.setPass_word(input_password);
        user.saveThrows();
    }
}


/*
        2017.3.26
        * construct the database
        4.1
        * add the user login record
        * 加入了用户登陆（无论成功或者失败）的记录
 */