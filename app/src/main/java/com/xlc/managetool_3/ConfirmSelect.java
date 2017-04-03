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

    @BindView(R.id.select_tool_page_number)
    TextView selectToolPageNumber;
    @BindView(R.id.selected_tool_last_page)
    Button selectedToolLastPage;
    @BindView(R.id.selected_tools_next_page)
    Button selectedToolsNextPage;
    @BindView(R.id.selected_tools_list1)
    TextView selectedToolsList1;
    @BindView(R.id.selected_tools_list8)
    TextView selectedToolsList8;
    @BindView(R.id.selected_tools_list7)
    TextView selectedToolsList7;
    @BindView(R.id.selected_tools_list6)
    TextView selectedToolsList6;
    @BindView(R.id.selected_tools_list5)
    TextView selectedToolsList5;
    @BindView(R.id.selected_tools_list4)
    TextView selectedToolsList4;
    @BindView(R.id.selected_tools_list3)
    TextView selectedToolsList3;
    @BindView(R.id.selected_tools_list2)
    TextView selectedToolsList2;
    @BindView(R.id.selected_givie_ok)
    Button selectedGivieOk;
    @BindView(R.id.selected_tool_man_unit_name)
    EditText selectedToolManUnitName;
    @BindView(R.id.give_tools_man_name)
    EditText giveToolsManName;
    @BindView(R.id.selected_give_cancel)
    Button selectedGiveCancel;
    @BindView(R.id.selected_give_time)
    EditText selectedGiveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_select);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.selected_tool_last_page, R.id.selected_tools_next_page, R.id.selected_givie_ok, R.id.selected_give_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.selected_tool_last_page:
                break;
            case R.id.selected_tools_next_page:
                break;
            case R.id.selected_givie_ok:
                break;
            case R.id.selected_give_cancel:
                break;
        }
    }
}
