package com.xlc.entity;

import com.xlc.managetool_3.BorrowProof;

import java.util.ArrayList;

/**
 * Created by zlb on 2017/5/17.
 */

public class SelectedToolCar {
    // tool list
    // 工具清单
    public static final ArrayList<Tools> toolListInCar = new ArrayList<>();
    // borrow proof this tool list
    // 这个借用清单的借条
    public static BorrowProof bp = new BorrowProof();

}
