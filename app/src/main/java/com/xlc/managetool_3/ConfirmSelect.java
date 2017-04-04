package com.xlc.managetool_3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_select);
        ButterKnife.bind(this);

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
