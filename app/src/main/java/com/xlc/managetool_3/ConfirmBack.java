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

public class ConfirmBack extends Activity {

    @BindView(R.id.return_tools_id)
    TextView returnToolsId;
    @BindView(R.id.borrow_tool_time)
    TextView borrowToolTime;
    @BindView(R.id.return_tool_list1)
    TextView returnToolList1;
    @BindView(R.id.return_tool_list4)
    TextView returnToolList4;
    @BindView(R.id.return_tool_list8)
    TextView returnToolList8;
    @BindView(R.id.return_tool_list7)
    TextView returnToolList7;
    @BindView(R.id.return_tool_list6)
    TextView returnToolList6;
    @BindView(R.id.return_tool_list5)
    TextView returnToolList5;
    @BindView(R.id.return_tool_list3)
    TextView returnToolList3;
    @BindView(R.id.return_tool_list2)
    TextView returnToolList2;
    @BindView(R.id.return_tool_page_number)
    TextView returnToolPageNumber;
    @BindView(R.id.return_tool_last_page_button)
    Button returnToolLastPageButton;
    @BindView(R.id.return_tool_next_page_button)
    Button returnToolNextPageButton;
    @BindView(R.id.return_tool_man)
    EditText returnToolMan;
    @BindView(R.id.return_tool_accept_name)
    EditText returnToolAcceptName;
    @BindView(R.id.note)
    EditText note;
    @BindView(R.id.return_tool_ok)
    Button returnToolOk;
    @BindView(R.id.return_cancel)
    Button returnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_back);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.return_tool_last_page_button, R.id.return_tool_next_page_button, R.id.return_tool_ok, R.id.return_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.return_tool_last_page_button:
                break;
            case R.id.return_tool_next_page_button:
                break;
            case R.id.return_tool_ok:
                break;
            case R.id.return_cancel:
                break;
        }
    }
}
