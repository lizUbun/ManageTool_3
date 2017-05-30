package com.xlc.managetool_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.CarrierConfigManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xlc.entity.SelectedToolCar;
import com.xlc.entity.Tools;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

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
    @BindView(R.id.confirm_select_activity_selected_give_ok)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_select);
        ButterKnife.bind(this);

        // init the text view
        init();
    }

    // init the text view
    // 初始化
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

        // display the selected tools
        // 显示被选中的工具
        LookActivity.displayTools(textViewsGroup, SelectedToolCar.toolListInCar);
    }


    @OnClick({R.id.confirm_select_activity_selected_tool_last_page, R.id.confirm_select_activity_selected_tools_next_page, R.id.confirm_select_activity_selected_give_ok, R.id.confirm_select_activity_selected_give_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm_select_activity_selected_tool_last_page:
                break;
            case R.id.confirm_select_activity_selected_tools_next_page:
                break;
            case R.id.confirm_select_activity_selected_give_ok: giveOk();
                break;
            case R.id.confirm_select_activity_selected_give_cancel:
                break;
        }
    }

    private void giveOk() {
        BorrowProof borrowProof = new BorrowProof();
        int id = DataSupport.findAll(BorrowProof.class).size() ;
        id ++;
        borrowProof.setId(id);
        borrowProof.setBorrower(confirmSelectActivitySelectedToolManUnitName.getText().toString());
        borrowProof.setGiver(confirmSelectActivityGiveToolsManName.getText().toString());
        borrowProof.setBorrowTime(confirmSelectActivitySelectedGiveTime.getText().toString());
        borrowProof.setBorrowState(AllDataControl.BORROW_PROOF_BORROWED);
        borrowProof.saveThrows();
        // debug
//        Toast.makeText(this, "borrow : " + id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ConfirmSelect.this,BorrowListActivity.class);
        startActivity(intent);
    }


}
