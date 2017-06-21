package com.xlc.managetool_3;

import com.xlc.entity.Tools;
import java.util.ArrayList;

public class AllDataControl {
    // define the display string
    // 定义需要显示的工具条目
    public static ArrayList<Tools> display = new ArrayList<>();

    // define the all data array list
    // 定义所有工具存放的列表
    public static ArrayList<Tools> allToolsList = new ArrayList<>();

    // define the search result
    // 定义搜索结果列表
    public static ArrayList<Tools> searchResult = new ArrayList<Tools>();

    // define the borrow proof
    // 定义借用证明列表
    public static ArrayList<BorrowProof> allBorrowProof = new ArrayList<>();

    // borrow state
    // 借用借条的状态
    public static final String BORROW_PROOF_BORROWED = "borrowed";
    public static final String BORROW_PROOF_GIVE_BACK = "give_back";
    public static final String BORROW_PROOF_GIVE_PART_BACK = "give_part_back";

    public AllDataControl() {

    }
}
