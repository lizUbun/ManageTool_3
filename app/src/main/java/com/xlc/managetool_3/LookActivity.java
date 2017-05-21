package com.xlc.managetool_3;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    // add the selected tools into the car
    // 添加被选中的工具到工具清单
    @BindView(R.id.add_car)
    Button addCar;
    // display the amount of tools in the tool's car
    // 显示已经加入到借用清单中的工具数量
    @BindView(R.id.tool_car_amount_display)
    TextView toolCarAmountDisplay;
    //  display the picture of the tool
    // 显示工具图片
    @BindView(R.id.tool_pic_view)
    ImageView toolPicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);
        ButterKnife.bind(this);
        // init the var
        initActivity();
        // init the text view
        initText();
        // show the init data
        // 显示所有工具的第一页
        initGraphic();

        /*
            turn to the view of database
         */
        showDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatabaseListener();
            }
        });

        // all tool show
        // 显示所有工具，同时隐藏软键盘
        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allButtonListener();
            }
        });


        // next page
        // 翻页
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPageListener();
            }
        });
        lastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPageListener();
            }
        });

        // borrow button listener
        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrowButtonListener();
            }
        });

        // when the select operation over
        // 选择完毕跳转到确认页面
        select_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteOverListener();
            }
        });

        // select the data on screen
        // 通过触屏选择数据
        addTextViewListener(lookTextList);


        // image view of the tools
        // touch and show big not touch small
        // 点击图片实现动画
        toolPicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolPicViewListener();
            }
        });



    }

    private void toolPicViewListener() {
        Animation animation = AnimationUtils.loadAnimation(getApplication(), R.anim.tool_pic_dis);
        toolPicView.startAnimation(animation);
    }

    private void selecteOverListener() {
        // clear the empty element
        // 将选择的数组中空的数据去除
        ArrayList<Integer> selectNoEmpty = new ArrayList<Integer>();
        for (int i = 0; i < selected.size(); i++) {
            if (selected.get(i) != 0) {
                selectNoEmpty.add(selected.get(i));
            }
        }
        selected = selectNoEmpty;
        Intent intent = new Intent(LookActivity.this, ConfirmSelect.class);
        startActivity(intent);

    }

    private void borrowButtonListener() {
        Intent intent = new Intent(LookActivity.this, BorrowListAcivity.class);
        startActivity(intent);
    }

    private void lastPageListener() {
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
    }

    private void nextPageListener() {
        // judge the page range
        // 判断page范围
        int max = total / 8;
        page++;
        if (page > max) {
            page = max;
        }
        pageNumber.setText(page + " / " + max);

        // show the data according to the page
        // 根据页码显示数据
        showDataAccordPage(lookTextList, allTools, page);

        // init background color
        //  初始化显示的背景颜色
        initTextViewBackground();
    }

    private void initActivity() {
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
        // 1.get all the tool info from the database
        allTools = (ArrayList<Tools>) DataSupport.findAll(Tools.class);
        // 2. get the amount of list
        total = allTools.size();
    }

    // turn to show all data activity
    // 跳转到显示所有工具的活动
    private void allButtonListener() {
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

    // turn to show data base activity
    // 跳转到显示数据库的活动中
    private void showDatabaseListener() {
        Intent intent = new Intent(LookActivity.this, DatabaseView.class);
        startActivity(intent);
    }

    // show the data
    // 第一次显示初始化的数据
    private void initGraphic() {

        if (AllDataControl.display != null) {
            if (AllDataControl.display.size() >= 8) {
                for (int i = 0; i < 8; i++) {
                    lookTextList.get(i).setText(AllDataControl.display.get(i));
                }
            }
            if (AllDataControl.display.size() < 8) {
                for (int i = 0; i < AllDataControl.display.size(); i++) {
                    lookTextList.get(i).setText(AllDataControl.display.get(i));
                }
            }
        }
        if (AllDataControl.display == null) {
            ArrayList<Tools> tools = (ArrayList) DataSupport.findAll(Tools.class);
            ArrayList<String> content = new ArrayList<>();
            // set the format of tool's display
            //  设置工具显示的格式
            for (int i = 0; i < tools.size(); i++) {
                Tools t = tools.get(i);
                content.add(t.getName() + " 品牌 ： " + t.getBrand()
                + " 型号： " + t.getType()
                + " 材质：" + t.getMaterial()
                + " 制式：" + t.getStandard()
                + " 尺寸：" + t.getSize()
                + " 备注：" + t.getRemark());
                if (i < 8) {
                    lookTextList.get(i).setText(content.get(i));
                }
            }

        }

        // init the selected tools list
        AllDataControl.selectedToolsMap = new HashMap<>();
        AllDataControl.pageSelectedToolsMap = new HashMap<>();

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
            lookTextList.get(i).setText(allTools.get((page - 1) * 8 + i).getName() + " ++++");

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
        pageNumber.setText("1 / " + total / 8);

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
        if (drawable == drawableList.get(1)) {
            drawable = drawableList.get(0);
            v.setBackground(drawable);
//            Toast.makeText(this, "init color is same", Toast.LENGTH_SHORT).show();
        } else if (drawable == drawableList.get(0)) {
            drawable = drawableList.get(1);
            v.setBackground(drawable);
//            Toast.makeText(this, "init color is same 2", Toast.LENGTH_SHORT).show();
        }

        // every times click item , update the select list
        // 每次点击都更新选择工具的容器
        for (int i = 0; i < lookTextList.size(); i++) {
            if (lookTextList.get(i).getBackground()
                    == drawableList.get(1)) {
//                AllDataControl.selected_tools.add(i);
                // 1 : selected
                // 1 : green
                AllDataControl.selectedToolsMap.put(i, 1);
            } else {
                AllDataControl.selectedToolsMap.put(i, 0);
            }
        }

        // as a result of duplication , use the hash map store tools id
        for (int i = 0; i < AllDataControl.selectedToolsMap.size(); i++) {
            // if the text view has been selected
            // 1 代表被选中
            if (AllDataControl.selectedToolsMap.get(i) == 1) {
//                AllDataControl.selectedTools.put(AllDataControl.selectedToolsNumber, i + page * 8 + 1);

                // plan 2
//                AllDataControl.onePageTools.add(AllDataControl.selectedToolsNumber,i + 1);
//                AllDataControl.selectedToolsNumber ++;
            }


        }
//        AllDataControl.selectedPageTools.put(page,AllDataControl.onePageTools);
//        AllDataControl.selectedToolsNumber = 0;
//        Toast.makeText(this, "page : " + page, Toast.LENGTH_SHORT).show();

    }

    /*
            when make sure that select tool over in this page
            当完成了当前页的工具选择
     */
    @OnClick(R.id.add_car)
    public void onViewClicked() {
        //  check the amount of selected tools

        //  clear the background of tools

        //  add the tools into the tool's car

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
