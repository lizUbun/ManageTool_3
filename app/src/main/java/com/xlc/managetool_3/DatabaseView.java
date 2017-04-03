package com.xlc.managetool_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatabaseView extends Activity {

    @BindView(R.id.name_button)
    Button nameButton;
    @BindView(R.id.class_button)
    Button classButton;
    @BindView(R.id.amount_button)
    Button amountButton;
    @BindView(R.id.id_button)
    Button idButton;
    @BindView(R.id.text_view1)
    TextView textView1;
    @BindView(R.id.text_view3)
    TextView textView3;
    @BindView(R.id.text_view4)
    TextView textView4;
    @BindView(R.id.text_view5)
    TextView textView5;
    @BindView(R.id.text_view6)
    TextView textView6;
    @BindView(R.id.text_view7)
    TextView textView7;
    @BindView(R.id.text_view8)
    TextView textView8;
    @BindView(R.id.text_view2)
    TextView textView2;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.page)
    TextView page;
    @BindView(R.id.last_page)
    Button lastPage;
    @BindView(R.id.next_page)
    Button nextPage;
    private Button editMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view);
        ButterKnife.bind(this);
        editMode = (Button) findViewById(R.id.edit_button);
        editMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatabaseView.this, EditView.class);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.name_button, R.id.class_button, R.id.amount_button, R.id.id_button, R.id.button, R.id.last_page, R.id.next_page})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.name_button:
                break;
            case R.id.class_button:
                break;
            case R.id.amount_button:
                break;
            case R.id.id_button:
                break;
            case R.id.button:
                break;
            case R.id.last_page:
                break;
            case R.id.next_page:
                break;
        }
    }
}
