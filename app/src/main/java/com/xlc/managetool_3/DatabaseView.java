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


    @BindView(R.id.show_database_edit_button)
    Button showDatabaseEditButton;
    @BindView(R.id.show_database_name_button)
    Button showDatabaseNameButton;
    @BindView(R.id.show_database_class_button)
    Button showDatabaseClassButton;
    @BindView(R.id.show_database_amount_button)
    Button showDatabaseAmountButton;
    @BindView(R.id.show_database_id_button)
    Button showDatabaseIdButton;
    @BindView(R.id.show_database_text_view1)
    TextView showDatabaseTextView1;
    @BindView(R.id.show_database_text_view3)
    TextView showDatabaseTextView3;
    @BindView(R.id.show_database_text_view4)
    TextView showDatabaseTextView4;
    @BindView(R.id.show_database_text_view5)
    TextView showDatabaseTextView5;
    @BindView(R.id.show_database_text_view6)
    TextView showDatabaseTextView6;
    @BindView(R.id.show_database_text_view7)
    TextView showDatabaseTextView7;
    @BindView(R.id.show_database_text_view8)
    TextView showDatabaseTextView8;
    @BindView(R.id.show_database_text_view2)
    TextView showDatabaseTextView2;
    @BindView(R.id.show_database_button)
    Button showDatabaseButton;
    @BindView(R.id.show_database_searchView)
    SearchView showDatabaseSearchView;
    @BindView(R.id.show_database_page)
    TextView showDatabasePage;
    @BindView(R.id.show_database_last_page)
    Button showDatabaseLastPage;
    @BindView(R.id.show_database_next_page)
    Button showDatabaseNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.show_database_edit_button)
    public void onViewClicked() {
        Intent intent = new Intent(DatabaseView.this, EditView.class);
        startActivity(intent);
    }

    @OnClick({R.id.show_database_name_button, R.id.show_database_class_button, R.id.show_database_amount_button, R.id.show_database_id_button, R.id.show_database_button, R.id.show_database_last_page, R.id.show_database_next_page})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.show_database_name_button:
                break;
            case R.id.show_database_class_button:
                break;
            case R.id.show_database_amount_button:
                break;
            case R.id.show_database_id_button:
                break;
            case R.id.show_database_button:
                break;
            case R.id.show_database_last_page:
                break;
            case R.id.show_database_next_page:
                break;
        }
    }
}
