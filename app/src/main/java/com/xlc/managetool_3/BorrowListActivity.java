package com.xlc.managetool_3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BorrowListActivity extends Activity {

    @BindView(R.id.borrow_list_activity_borrow_back_button)
    Button borrowListActivityBorrowBackButton;
    @BindView(R.id.borrow_list_activity_borrow_page_number_text_view)
    TextView borrowListActivityBorrowPageNumberTextView;
    @BindView(R.id.borrow_list_activity_borrow_list_last_page_button)
    Button borrowListActivityBorrowListLastPageButton;
    @BindView(R.id.borrow_list_activity_borrow_list_next_page_button)
    Button borrowListActivityBorrowListNextPageButton;
    @BindView(R.id.borrow_list_activity_borrow_text3)
    TextView borrowListActivityBorrowText3;
    @BindView(R.id.borrow_list_activity_borrow_text1)
    TextView borrowListActivityBorrowText1;
    @BindView(R.id.borrow_list_activity_borrow_text7)
    TextView borrowListActivityBorrowText7;
    @BindView(R.id.borrow_list_activity_borrow_text6)
    TextView borrowListActivityBorrowText6;
    @BindView(R.id.borrow_list_activity_borrow_text8)
    TextView borrowListActivityBorrowText8;
    @BindView(R.id.borrow_list_activity_borrow_text5)
    TextView borrowListActivityBorrowText5;
    @BindView(R.id.borrow_list_activity_borrow_text4)
    TextView borrowListActivityBorrowText4;
    @BindView(R.id.borrow_list_activity_borrow_text2)
    TextView borrowListActivityBorrowText2;

    // list of text view
    // 存放显示栏的list
    ArrayList<TextView> textList = new ArrayList<>();
    ArrayList<BorrowProof> borrowProofList;

    // text view listener
    // 文本显示监听器
    View.OnClickListener textListener;

    // text view background color
    // 工具显示文字栏的背景颜色
    ArrayList<Drawable> drawableList = null;
    Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_list_acivity);
        ButterKnife.bind(this);
        borrowProofList = (ArrayList) DataSupport.findAll(BorrowProof.class);

        // init view
        init();

        // debug
//        borrowListActivityBorrowText1.setText(" 名称： " + borrowProofList.get(0).getBorrower());
//        borrowListActivityBorrowText2.setText(" 数量： " + borrowProofList.size());
    }

    private void init() {
        textList.add(borrowListActivityBorrowText1);
        textList.add(borrowListActivityBorrowText2);
        textList.add(borrowListActivityBorrowText3);
        textList.add(borrowListActivityBorrowText4);
        textList.add(borrowListActivityBorrowText5);
        textList.add(borrowListActivityBorrowText6);
        textList.add(borrowListActivityBorrowText7);
        textList.add(borrowListActivityBorrowText8);

        // drawable
        drawableList = new ArrayList<>();
        drawableList.add(getResources().getDrawable(R.drawable.white_text_view));
        drawableList.add(getResources().getDrawable(R.drawable.green_text_view));
        drawable = drawableList.get(0);

        // display the borrow proof
        // 显示借用清单
        displayBorrowProof(textList,borrowProofList);

        // add listener
        // 添加监听器
        textListener = new TextListener();
        setOnClickListener(textList,textListener);

        //
        if (borrowProofList.size() < 8){
            borrowListActivityBorrowPageNumberTextView.setText("1");
        }else {
            borrowListActivityBorrowPageNumberTextView.setText(" " + borrowProofList.size() / 8);
        }
    }

    private void setOnClickListener(ArrayList<TextView> textList, View.OnClickListener textListener) {
        for (int i = 0; i < textList.size();i++){
            textList.get(i).setOnClickListener(textListener);
        }
    }

    // display the borrow proof
    // 显示借用清单
    public static void displayBorrowProof(ArrayList<TextView> display,ArrayList<BorrowProof> content) {
        for (int i = 0 ;i < display.size();i++){
            String str = getFormatBorrowProof(content.get(i));
            display.get(i).setText(str);
        }
    }

    // format the borrow proof display text
    // 格式化借条显示格式
    private static String getFormatBorrowProof(BorrowProof borrowProof) {
        return " id : " + borrowProof.getId() +
                " 借用人/单位 ：" + borrowProof.getBorrower() +
                " 发放人 ：" + borrowProof.getGiver() +
                " 借出时间 ：" + borrowProof.getBorrowTime();
    }

    @OnClick(R.id.borrow_list_activity_borrow_back_button)
    public void onViewClicked() {
        Intent intent = new Intent(BorrowListActivity.this, ConfirmBack.class);
        startActivity(intent);
    }


    @OnClick({R.id.borrow_list_activity_borrow_list_last_page_button, R.id.borrow_list_activity_borrow_list_next_page_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.borrow_list_activity_borrow_list_last_page_button: lastPage();
                break;
            case R.id.borrow_list_activity_borrow_list_next_page_button: nextPage();
                break;
        }
    }

    private void nextPage() {

    }

    private void lastPage() {

    }

    private class TextListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // change the background color
            // 改变被选择的text view的背景颜色
            // 0 : white
            // weather : 天气
            // whether ; 是否
            if (drawable == drawableList.get(1)) {
                drawable = drawableList.get(0);
                v.setBackground(drawable);
            } else if (drawable == drawableList.get(0)) {
                drawable = drawableList.get(1);
                v.setBackground(drawable);
            }
        }
    }
}
