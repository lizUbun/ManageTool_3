package com.xlc.managetool_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BorrowListAcivity extends Activity {

    @BindView(R.id.borrow_list_activity_borrow_back_button)
    Button borrowListActivityBorrowBackButton;
    @BindView(R.id.borrow_list_activity_borrow_page_number_text_view)
    TextView borrowListActivityBorrowPageNumberTextView;
    @BindView(R.id.borrow_list_activity_borrow_list_last_page_button)
    Button borrowListActivityBorrowListLastPageButton;
    @BindView(R.id.borrow_list_activity_borrow_list_next_page_button)
    Button borrowListActivityBorrowListNextPageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_list_acivity);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.borrow_list_activity_borrow_back_button)
    public void onViewClicked() {
        Intent intent = new Intent(BorrowListAcivity.this, ConfirmBack.class);
        startActivity(intent);
    }


    @OnClick({R.id.borrow_list_activity_borrow_list_last_page_button, R.id.borrow_list_activity_borrow_list_next_page_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.borrow_list_activity_borrow_list_last_page_button:
                break;
            case R.id.borrow_list_activity_borrow_list_next_page_button:
                break;
        }
    }
}
