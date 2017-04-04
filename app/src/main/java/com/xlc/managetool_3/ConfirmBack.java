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


    @BindView(R.id.comfirm_back_activity_return_tools_id)
    TextView comfirmBackActivityReturnToolsId;
    @BindView(R.id.comfirm_back_activity_borrow_tool_time)
    TextView comfirmBackActivityBorrowToolTime;
    @BindView(R.id.comfirm_back_activity_return_tool_list1)
    TextView comfirmBackActivityReturnToolList1;
    @BindView(R.id.comfirm_back_activity_return_tool_list4)
    TextView comfirmBackActivityReturnToolList4;
    @BindView(R.id.comfirm_back_activity_return_tool_list8)
    TextView comfirmBackActivityReturnToolList8;
    @BindView(R.id.comfirm_back_activity_return_tool_list7)
    TextView comfirmBackActivityReturnToolList7;
    @BindView(R.id.comfirm_back_activity_return_tool_list6)
    TextView comfirmBackActivityReturnToolList6;
    @BindView(R.id.comfirm_back_activity_return_tool_list5)
    TextView comfirmBackActivityReturnToolList5;
    @BindView(R.id.comfirm_back_activity_return_tool_list3)
    TextView comfirmBackActivityReturnToolList3;
    @BindView(R.id.comfirm_back_activity_return_tool_list2)
    TextView comfirmBackActivityReturnToolList2;
    @BindView(R.id.comfirm_back_activity_return_tool_page_number)
    TextView comfirmBackActivityReturnToolPageNumber;
    @BindView(R.id.comfirm_back_activity_return_tool_last_page_button)
    Button comfirmBackActivityReturnToolLastPageButton;
    @BindView(R.id.comfirm_back_activity_return_tool_next_page_button)
    Button comfirmBackActivityReturnToolNextPageButton;
    @BindView(R.id.comfirm_back_activity_return_tool_man)
    EditText comfirmBackActivityReturnToolMan;
    @BindView(R.id.comfirm_back_activity_return_tool_accept_name)
    EditText comfirmBackActivityReturnToolAcceptName;
    @BindView(R.id.comfirm_back_activity_note)
    EditText comfirmBackActivityNote;
    @BindView(R.id.comfirm_back_activity_return_tool_ok)
    Button comfirmBackActivityReturnToolOk;
    @BindView(R.id.comfirm_back_activity_return_cancel)
    Button comfirmBackActivityReturnCancel;
    @BindView(R.id.confirm_back_activity_back_time)
    EditText confirmBackActivityBackTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_back);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.comfirm_back_activity_return_tool_last_page_button, R.id.comfirm_back_activity_return_tool_next_page_button, R.id.comfirm_back_activity_return_tool_ok, R.id.comfirm_back_activity_return_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comfirm_back_activity_return_tool_last_page_button:
                break;
            case R.id.comfirm_back_activity_return_tool_next_page_button:
                break;
            case R.id.comfirm_back_activity_return_tool_ok:
                break;
            case R.id.comfirm_back_activity_return_cancel:
                break;
        }
    }
}
