package com.xlc.managetool_3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xlc.entity.Tools;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    Context mContext;
    Button sure;
    Button clear;
    EditText name;
    EditText pass_word;
    // 1 string is name , 2 string is password
    HashMap<String, String> user;
    @BindView(R.id.debug_text)
    TextView debugText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SQLiteDatabase db = LitePal.getDatabase();
//        LookActivity.initData();
        mContext = getApplicationContext();
        sure = (Button) findViewById(R.id.main_activity_sure);
        clear = (Button) findViewById(R.id.main_activity_clear);

        user = new HashMap<>();
        name = (EditText) findViewById(R.id.main_activity_name);
        pass_word = (EditText) findViewById(R.id.main_activity_pass_word);


        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the input info
                String input_name = name.getText().toString().trim();
                String input_password = pass_word.getText().toString().trim();
                check(input_name, input_password);
            }
        });

        /*
            clear the input
            重新输入
         */
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                pass_word.setText("");
            }
        });

        // clear the data
        // 清除以前的数据
        DataSupport.deleteAll(Tools.class);

        // load the tool list
        loadToolList();
    }

    // load the data in the file tool list
    //  加载工具清单中的工具
    private void loadToolList() {
        try {
            InputStream in = getAssets().open("tool_list.txt");
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
//            StringBuffer buffer = new StringBuffer("");
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                lineToData(str);
//                buffer.append(s + " ");
            }

            // load picture
            initToolPic();

            debugText.setTextSize(28);
//            debugText.setText(buffer);

            int size = DataSupport.findAll(Tools.class).size();
//            debugText.setText(" 工具数量：" + size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initToolPic() {
        // init the photo
        ArrayList<Integer> photoList = new ArrayList<>();
//        photoList.add(R.drawable.t1);
//        photoList.add(R.drawable.t2);
//        photoList.add(R.drawable.t3);
//        photoList.add(R.drawable.t4);
//        photoList.add(R.drawable.t5);


        //获取drawable文件名列表，不包含扩展名
        Field[] fields = R.drawable.class.getDeclaredFields();
        for(Field field:fields){
        	/*获取文件名对应的系统生成的id
        	需指定包路径 getClass().getPackage().getName()
        	指定资源类型drawable*/
        	if (field.getName().startsWith("t") && !field.getName().endsWith("n")) {
                int resID = getResources().getIdentifier(field.getName(),
                        "drawable", getClass().getPackage().getName());
                photoList.add(resID);
                System.out.println("fileName = " + field.getName()
                        + "    resId = " + resID);
                debugText.setText("fileName = " + field.getName()
                        + "    resId = " + resID);
            }
        }

        // add picture for tools
        // 为工具添加图片
        ArrayList<Tools> toolsList = (ArrayList) DataSupport.findAll(Tools.class);
        for (int i = 0;i < photoList.size();i++){
            toolsList.get(i).setPictur_id(photoList.get(i));
            toolsList.get(i).saveThrows();
        }
    }


    // read line by line to deal
    // 读取每一行数据，并且加载到数据库
    private String  lineToData(String str) {
        String[] s = str.split("，");
//        StringBuffer sb = new StringBuffer("");
//        for (int i = 0;i < s.length;i++){
//            sb.append(s[0] + "   ");
//        }
        Tools t = new Tools();
        t.setId_second(Integer.parseInt(s[0]));
        t.setName(s[1]);
        t.setBrand(s[2]);
        t.setType(s[3]);
        t.setMaterial(s[4]);
        t.setStandard(s[5]);
        t.setSize(s[6]);
        t.setPicture(s[7]);
        t.setRemark(s[8]);
        t.save();
//        debugText.setTextSize(28);
//        debugText.setText(sb.toString());
//        debugText.setText(sb);
        return s[0];
    }

    private void check(String input_name, String input_password) {
        // 1 get all user info
        ArrayList<User> users = (ArrayList<User>) DataSupport.findAll(User.class);
        for (int i = 0; i < user.size(); i++) {
            user.put(users.get(i).getName(), users.get(i).getPass_word());
        }
        // 2 check
        // and if succeed then turn to look the tool list
        if (input_name.equals("root") && input_password.equals("root")) {
            Intent intent = new Intent(MainActivity.this, LookActivity.class);
            startActivity(intent);
        } else {
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