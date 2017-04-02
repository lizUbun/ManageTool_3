package com.xlc.managetool_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BorrowListAcivity extends AppCompatActivity {
    Button borrowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_list_acivity);
        //
        initView();
        borrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BorrowListAcivity.this,ConfirmBack.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        borrowBack = (Button)findViewById(R.id.borrow_back_button);
    }
}
