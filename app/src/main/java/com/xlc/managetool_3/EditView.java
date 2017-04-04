package com.xlc.managetool_3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditView extends Activity {


    @BindView(R.id.edit_view_activity_edit_add_button)
    Button editViewActivityEditAddButton;
    @BindView(R.id.edit_view_activity_edit_delete_button)
    Button editViewActivityEditDeleteButton;
    @BindView(R.id.edit_view_activity_edit_button)
    Button editViewActivityEditButton;
    @BindView(R.id.edit_view_activity_edit_search)
    EditText editViewActivityEditSearch;
    @BindView(R.id.edit_view_activity_edit_search_button)
    Button editViewActivityEditSearchButton;
    @BindView(R.id.edit_view_activity_edit_name_input)
    EditText editViewActivityEditNameInput;
    @BindView(R.id.edit_view_activity_edit_class_input)
    EditText editViewActivityEditClassInput;
    @BindView(R.id.edit_view_activity_edit_amount_input)
    EditText editViewActivityEditAmountInput;
    @BindView(R.id.edit_view_activity_save_edit)
    Button editViewActivitySaveEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.edit_view_activity_edit_add_button, R.id.edit_view_activity_edit_delete_button, R.id.edit_view_activity_edit_button, R.id.edit_view_activity_edit_search_button, R.id.edit_view_activity_save_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_view_activity_edit_add_button:
                break;
            case R.id.edit_view_activity_edit_delete_button:
                break;
            case R.id.edit_view_activity_edit_button:
                break;
            case R.id.edit_view_activity_edit_search_button:
                break;
            case R.id.edit_view_activity_save_edit:
                break;
        }
    }
}
