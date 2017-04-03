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
    Button borrowBack;
    @BindView(R.id.borrow_text3)
    TextView borrowText3;
    @BindView(R.id.borrow_title_id)
    TextView borrowTitleId;
    @BindView(R.id.borrow_title_name)
    TextView borrowTitleName;
    @BindView(R.id.borrow_title_borrower)
    TextView borrowTitleBorrower;
    @BindView(R.id.borrow_title_time)
    TextView borrowTitleTime;
    @BindView(R.id.borrow_title_giver)
    TextView borrowTitleGiver;
    @BindView(R.id.borrow_text1)
    TextView borrowText1;
    @BindView(R.id.borrow_text7)
    TextView borrowText7;
    @BindView(R.id.borrow_text6)
    TextView borrowText6;
    @BindView(R.id.borrow_text8)
    TextView borrowText8;
    @BindView(R.id.borrow_text5)
    TextView borrowText5;
    @BindView(R.id.borrow_text4)
    TextView borrowText4;
    @BindView(R.id.borrow_text2)
    TextView borrowText2;
    @BindView(R.id.borrow_page_number_text_view)
    TextView borrowPageNumberTextView;
    @BindView(R.id.borrow_list_last_page_button)
    Button borrowListLastPageButton;
    @BindView(R.id.borrow_list_next_page_button)
    Button borrowListNextPageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_list_acivity);
        ButterKnife.bind(this);
        //
        initView();
        borrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BorrowListAcivity.this, ConfirmBack.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        borrowBack = (Button) findViewById(R.id.borrow_back_button);
    }

    @OnClick({R.id.borrow_list_last_page_button, R.id.borrow_list_next_page_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.borrow_list_last_page_button:
                break;
            case R.id.borrow_list_next_page_button:
                break;
        }
    }
}
