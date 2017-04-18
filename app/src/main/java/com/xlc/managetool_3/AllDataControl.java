package com.xlc.managetool_3;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.xlc.tool.ManageTool;

import java.util.ArrayList;
import java.util.HashMap;

public class AllDataControl extends Service {
    Handler handler;

    // define the statement of the data
    //  定义当前数据显示的状态
    public static int data_state = 0;
    public static final int DATA_STATE_INIT = 1;

    // define the display string
    // 定义需要显示的工具条目
    public static ArrayList<String> display;

    // look list activity selected items
    // 在挑选工具中被选中的工具
//    public static ArrayList<Integer> selected_tools;
    // argument 1 ： id 序号，相当于每一页的1到8个显示
    // argument 2 ： 1 选中，相当于选中或者没有选中的标志
    public static HashMap<Integer,Integer> selectedToolsMap;
    // selected items of tools no duplicate
//    public static HashMap<Integer,Integer> selectedTools;
//    public static int selectedToolsNumber;
    // another plan : use the page as the key
//    public static HashMap<Integer,ArrayList<Integer>> selectedPageTools;
//    public static ArrayList<Integer> onePageTools;
    // plan 3
    // save page and one page selected hash map
    public static HashMap<Integer,HashMap<Integer,Integer>> pageSelectedToolsMap;

    public AllDataControl() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // if get the flush data message

                if (msg.what == ManageTool.FLUSH_DATA) {

                }
            }

        };

    }

    @Override
    public IBinder onBind(Intent intent) {
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // if get the flush data message
                if (msg.what == ManageTool.FLUSH_DATA) {

                }
            }
        };
        return null;
    }
}
