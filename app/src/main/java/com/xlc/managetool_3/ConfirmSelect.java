package com.xlc.managetool_3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmSelect extends Activity {


    @BindView(R.id.confirm_select_activity_selected_tool_last_page)
    Button confirmSelectActivitySelectedToolLastPage;
    @BindView(R.id.confirm_select_activity_selected_tools_next_page)
    Button confirmSelectActivitySelectedToolsNextPage;
    @BindView(R.id.confirm_select_activity_selected_tools_list1)
    TextView confirmSelectActivitySelectedToolsList1;
    @BindView(R.id.confirm_select_activity_selected_tools_list8)
    TextView confirmSelectActivitySelectedToolsList8;
    @BindView(R.id.confirm_select_activity_selected_tools_list7)
    TextView confirmSelectActivitySelectedToolsList7;
    @BindView(R.id.confirm_select_activity_selected_tools_list6)
    TextView confirmSelectActivitySelectedToolsList6;
    @BindView(R.id.confirm_select_activity_selected_tools_list5)
    TextView confirmSelectActivitySelectedToolsList5;
    @BindView(R.id.confirm_select_activity_selected_tools_list4)
    TextView confirmSelectActivitySelectedToolsList4;
    @BindView(R.id.confirm_select_activity_selected_tools_list3)
    TextView confirmSelectActivitySelectedToolsList3;
    @BindView(R.id.confirm_select_activity_selected_tools_list2)
    TextView confirmSelectActivitySelectedToolsList2;
    @BindView(R.id.confirm_select_activity_selected_givie_ok)
    Button confirmSelectActivitySelectedGivieOk;
    @BindView(R.id.confirm_select_activity_selected_tool_man_unit_name)
    EditText confirmSelectActivitySelectedToolManUnitName;
    @BindView(R.id.confirm_select_activity_give_tools_man_name)
    EditText confirmSelectActivityGiveToolsManName;
    @BindView(R.id.confirm_select_activity_selected_give_cancel)
    Button confirmSelectActivitySelectedGiveCancel;
    @BindView(R.id.confirm_select_activity_selected_give_time)
    EditText confirmSelectActivitySelectedGiveTime;

    // display the tools list
    ArrayList<TextView> textViewsGroup;
    // data used to display
    ArrayList<Tools> toolsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_select);
        ButterKnife.bind(this);

        // init the text view
        init();
    }

    // init the text view
    private void init() {
        textViewsGroup = new ArrayList<>();
        textViewsGroup.add(confirmSelectActivitySelectedToolsList1);
        textViewsGroup.add(confirmSelectActivitySelectedToolsList2);
        textViewsGroup.add(confirmSelectActivitySelectedToolsList3);
        textViewsGroup.add(confirmSelectActivitySelectedToolsList4);
        textViewsGroup.add(confirmSelectActivitySelectedToolsList5);
        textViewsGroup.add(confirmSelectActivitySelectedToolsList6);
        textViewsGroup.add(confirmSelectActivitySelectedToolsList7);
        textViewsGroup.add(confirmSelectActivitySelectedToolsList8);
//        toolsList = new ArrayList<>();
//        for(int i = 0 ; i < AllDataControl.selected_tools.size();i++){
////            toolsList.add((Tools)DataSupport.find(Tools.class,
////                    (long)AllDataControl.selected_tools.get(i)));
//
//        }
//        for (int i = 0 ;i < textViewsGroup.size();i++){
////            if (toolsList.get(i) != null) {
////                textViewsGroup.get(i).setText(toolsList.get(i).getName() + " 数量 : " + toolsList.get(i).getAmount());
////                textViewsGroup.get(i)
////                        .setText(AllDataControl.selected_tools.get(i));
////            }
//            //
//            if (AllDataControl.selected_tools.size() > i){
//                textViewsGroup.get(i)
//                        .setText(AllDataControl.selected_tools.get(i));
//            }
//
//        }
//        for (int i = 0 ;i < textViewsGroup.size();i++){
//            if (AllDataControl.selected_tools.size() > i){
//                textViewsGroup.get(i).setText(" has tools ");
//            }else {
//                textViewsGroup.get(i).setText(" null ");
//            }
//        }
//        Toast.makeText(this, "init confirm select "
//                + AllDataControl.selected_tools.size(), Toast.LENGTH_SHORT).show();

//        textViewsGroup.get(1).setText("selected ； " +
//        AllDataControl.selectedToolsMap);

//        textViewsGroup.get(1).setText("selected : " +
//        AllDataControl.selected_tools);
//        int j = 0;
//        for (int i = 0 ;i < AllDataControl.selectedToolsMap.size();i++){
//            if (AllDataControl.selectedToolsMap.get(i) == 1){
//                textViewsGroup.get(j).setText("tools id : " + (i + 1));
//                j++;
//            }
//        }
//        for (long i = 1 ; i < 6;i++) {
//            Tools tool = (Tools) DataSupport.find(Tools.class, i);
//            String displayTool1 = ToolsToStringFormat(tool);
//            textViewsGroup.get((int)i).setText(displayTool1);
//        }
//        textViewsGroup.get(0).setText("selected tools : " + AllDataControl.selectedTools.size());
        String selectedToolId = " ";
//        for (int i = 0 ;i < AllDataControl.selectedTools.size();i++){
//            selectedToolId = selectedToolId + ", " + AllDataControl.selectedTools.get(i);
//        }
        // plan 2
//        for (int i = 0;i < AllDataControl.selectedPageTools.size();i++){
//            for (int j = 0 ; j < AllDataControl.selectedPageTools.get(i).size();j++){
//                selectedToolId = selectedToolId
//                        + AllDataControl.selectedPageTools.get(i).get(j)
//                        + " , ";
//            }
//        }
        // check one page
//        for (int i = 0;i < AllDataControl.onePageTools.size();i++){
//            selectedToolId = selectedToolId
//                    + AllDataControl.onePageTools.get(i) + " ,";
//        }


//        textViewsGroup.get(2).setText("size : " + AllDataControl.onePageTools.size());

//        textViewsGroup.get(7).setText("all data : "
//                + AllDataControl.selectedToolsMap.size());
//        for (int i = 0;i < AllDataControl.selectedToolsMap.size();i++){
//            if (AllDataControl.selectedToolsMap.get(i) == 1){
//                selectedToolId = selectedToolId + i + " ,";
//            }
//        }

//        textViewsGroup.get(1).setText("ids : " + selectedToolId);


//        Set<Integer> key = AllDataControl.pageSelectedToolsMap.keySet();
//        Integer[] keyArray = (Integer[]) key.toArray();
//        textViewsGroup.get(2).setText(" 1 : " + AllDataControl.pageSelectedToolsMap.get(keyArray[0]));
    }

    // tools format conversion
    // 工具格式转换，从对象到文字
    public static String ToolsToStringFormat(Tools tool) {
        String str = " ";
        if (tool !=  null){
            str = "工具名：" + tool.getName() + " 工具数量 ： "
                    + tool.getAmount();
        }
        return str;
    }

    @OnClick({R.id.confirm_select_activity_selected_tool_last_page, R.id.confirm_select_activity_selected_tools_next_page, R.id.confirm_select_activity_selected_givie_ok, R.id.confirm_select_activity_selected_give_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm_select_activity_selected_tool_last_page:
                break;
            case R.id.confirm_select_activity_selected_tools_next_page:
                break;
            case R.id.confirm_select_activity_selected_givie_ok:
                break;
            case R.id.confirm_select_activity_selected_give_cancel:
                break;
        }
    }
}
