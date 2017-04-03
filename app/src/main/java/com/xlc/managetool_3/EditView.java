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

public class EditView extends Activity {

    @BindView(R.id.edit_add_button)
    Button editAddButton;
    @BindView(R.id.edit_delete_button)
    Button editDeleteButton;
    @BindView(R.id.edit_button)
    Button editButton;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.edit_search_button)
    Button editSearchButton;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.edit_name_input)
    EditText editNameInput;
    @BindView(R.id.edit_class_input)
    EditText editClassInput;
    @BindView(R.id.edit_amount_input)
    TextView editAmountInput;
    @BindView(R.id.save_edit)
    Button saveEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.edit_add_button, R.id.edit_delete_button,
            R.id.edit_button, R.id.edit_search_button, R.id.save_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_add_button:
                break;
            case R.id.edit_delete_button:
                break;
            case R.id.edit_button:
                break;
            case R.id.edit_search_button:
                break;
            case R.id.save_edit:
                break;
        }
    }
}
