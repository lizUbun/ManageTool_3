package com.xlc.managetool_3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xlc.entity.SelectedToolCar;
import com.xlc.entity.Tools;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
     list the need borrow tools
     列出需要借用的工具
 */
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
    // page now
    // 当前页数
    int page = 1;
    //  total page
    //  总共页数
    int totalPage = 1;
    // borrow tool
    // 借用工具
    ArrayList<Tools> borrowTools = SelectedToolCar.toolListInCar;
    @BindView(R.id.borrow_list_activity_look_borrow_detail)
    Button borrowListActivityLookBorrowDetail;
    @BindView(R.id.borrow_proof_tool_detail)
    TextView borrowProofToolDetail;

    // which one has been selected , id
    // 哪一个被选择，由序号确定 ( 0 - 8 )
    public static int selectedProof = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_list_acivity);
        ButterKnife.bind(this);
        borrowProofList = (ArrayList) DataSupport.findAll(BorrowProof.class);

        // init view
        // 初始化
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
//        displayBorrowProof(textList,borrowProofList,borrowTools);
        showBorrowProofList(textList, borrowProofList, 1);

        // add listener
        // 添加监听器
        textListener = new TextListener();
        setOnClickListener(textList, textListener);

        //  calculate the page
        //  计算页数
        if (borrowProofList.size() < 8) {
            borrowListActivityBorrowPageNumberTextView.setText("1 / 1");
        } else {
            borrowListActivityBorrowPageNumberTextView.setText(page + " / " + borrowProofList.size() / 8);
            totalPage = borrowProofList.size() / 8;
        }

        // look detail of borrow proof
        // 查看借用证明详情
        borrowListActivityLookBorrowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrowListActivityLookBorrowDetailListener();
            }
        });

        // debug
        String str = "";
        str += " borrow tools amount : " + borrowTools.size();
        int s = SelectedToolCar.toolListInCar.size();

        str = " tool car : " + s;
        str = "";
        for (int i = 0; i < SelectedToolCar.toolListInCar.size(); i++) {
            str += SelectedToolCar.toolListInCar.get(i).getName() + " ; ";
        }
//        borrowListActivityBorrowText8.setText(str);


    }

    // look the detail of borrow proof
    // 查看借用清单详情
    private void borrowListActivityLookBorrowDetailListener() {
        borrowProofToolDetail.setBackgroundColor(Color.WHITE);
        borrowProofToolDetail.setText("the detail of borrow proof's tools");
        int proofId = getSelectedProof();
        String listContent = "";
        // judge the borrow proof that equal whit displayed
        // 统一显示和点击获取的两个对象
        for (int i = 0 ;i < borrowProofList.get(proofId).getIncludeTools().size();i++){
            listContent += " 工具 ： " +
                    borrowProofList.get(proofId).getIncludeTools().get(i).getName();
        }
        borrowProofToolDetail.setText(listContent);
    }

    // according the text list background get the id
    // 根据列表背景获取选择的序号
    public int getSelectedProof() {
        int temp = 0;
        for (int i = 0 ;i < 8;i++){
            if ( textList.get(i).getBackground() == drawableList.get(1)){
                temp = i;
            }
        }
        return temp;
    }

    private void setOnClickListener(ArrayList<TextView> textList, View.OnClickListener textListener) {
        for (int i = 0; i < textList.size(); i++) {
            textList.get(i).setOnClickListener(textListener);
        }


    }

    // display the borrow proof
    // 显示借用清单
    public static void displayBorrowProof(ArrayList<TextView> display, ArrayList<BorrowProof> content, ArrayList<Tools> tool) {
        for (int i = 0; i < display.size(); i++) {
            String str = getFormatBorrowProof(content.get(i));
            display.get(i).setText(str);
        }
    }

    // format the borrow proof display text
    // 格式化借条显示格式
    private static String getFormatBorrowProof(BorrowProof borrowProof) {
        String state = "";
        if (borrowProof.getBorrowState() != null) {
            if (borrowProof.getBorrowState().equals(BorrowProof.BORROW_PROOF_BORROWED)) {
                state = "已经借出";
            }
            if (borrowProof.getBorrowState().equals(BorrowProof.BORROW_PROOF_GIVE_BACK)) {
                state = "已经归还";
            }
        }
        return " id : " + borrowProof.getId() +
                " 借用人/单位 ：" + borrowProof.getBorrower() +
                " 发放人 ：" + borrowProof.getGiver() +
                " 借出时间 ：" + borrowProof.getBorrowTime() +
                state;

    }

    @OnClick(R.id.borrow_list_activity_borrow_back_button)
    public void onViewClicked() {
        Intent intent = new Intent(BorrowListActivity.this, ConfirmBack.class);
        startActivity(intent);
    }

    @OnClick({R.id.borrow_list_activity_borrow_list_last_page_button, R.id.borrow_list_activity_borrow_list_next_page_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.borrow_list_activity_borrow_list_last_page_button:
                lastPage();
                break;
            case R.id.borrow_list_activity_borrow_list_next_page_button:
                nextPage();
                break;
        }
    }

    private void nextPage() {
        if (page < totalPage) {
            page++;
        }
        if (page >= totalPage) {
            page = totalPage;
        }
        borrowListActivityBorrowPageNumberTextView.setText(page + " / " + totalPage);
        showBorrowProofList(textList, borrowProofList, page);
    }

    public static void showBorrowProofList(ArrayList<TextView> textList, ArrayList<BorrowProof> borrowProofList, int page) {
        AllDataControl.allBorrowProof = (ArrayList<BorrowProof>) DataSupport.findAll(BorrowProof.class);
        if (page == 1) {
            for (int i = 0; i < 8; i++) {
                if (AllDataControl.allBorrowProof.isEmpty()) {
                    for (int j = 0; j < 8; j++) {
                        textList.get(j).setText("");
                    }
                    break;
                }
                // if the borrow proof amount is less than 8
                // 如果借用证明数量少于8个
                String str = "";
                if (AllDataControl.allBorrowProof.size() > i) {
                    str = getBorrowProofContent(AllDataControl.allBorrowProof.get(i));
                }
                textList.get(i).setText(str);
            }
        }

        if (page >= 2) {
            for (int i = 0; i < 8; i++) {
                if (AllDataControl.allBorrowProof.size() > i + (page - 1) * 8) {
                    String str2 = getBorrowProofContent(AllDataControl.allBorrowProof.get((i + (page - 1) * 8)));
                    textList.get(i).setText(str2);
                } else {
                    textList.get(i).setText("");
                }
            }
        }
    }

    public static String getBorrowProofContent(BorrowProof borrowProof) {
        String str = "";

//        str += "id: " + borrowProof.getId() + " " +
//            "borrow time: " + borrowProof.getBorrowTime() + " " +
//            "borrow people: " + borrowProof.getGiver() + " "+
//            "receive people: " + borrowProof.getBorrower();

        str += "序号:" + borrowProof.getId() +
                "发放人:" + borrowProof.getGiver() +
                "借用人:" + borrowProof.getBorrower() +
                "借用单位:" + " " +
                "借用时间:" + borrowProof.getBorrowTime();
        return str;
    }

    private void lastPage() {
        page--;
        if (page <= 0) {
            page = 1;
        }
        borrowListActivityBorrowPageNumberTextView.setText(page + " / " + totalPage);
        showBorrowProofList(textList, borrowProofList, page);
    }

    private class TextListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // change the background color
            // 改变被选择的text view的背景颜色
            // 0 : white
            // weather : 天气
            // whether ; 是否
            //  only keep one selected
            // 始终保持只有一个被选中
            initTextViewBackground();
            if (drawable == drawableList.get(1)) {
                drawable = drawableList.get(0);
                v.setBackground(drawable);
            } else if (drawable == drawableList.get(0)) {
                drawable = drawableList.get(1);
                v.setBackground(drawable);
            }
        }
    }

    //  make the background to white
    //  将背景变为白色
    private void initTextViewBackground() {
        for (int i = 0; i < textList.size(); i++) {
            textList.get(i).setBackground(drawableList.get(0));
        }
    }
}
