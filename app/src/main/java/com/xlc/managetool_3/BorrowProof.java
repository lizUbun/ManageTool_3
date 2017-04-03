package com.xlc.managetool_3;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by zlb on 2017/4/3.
 */

public class BorrowProof extends DataSupport {
    @Column
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getGiver() {
        return giver;
    }

    public void setGiver(String giver) {
        this.giver = giver;
    }

    public String getToolsName() {
        return toolsName;
    }

    public void setToolsName(String toolsName) {
        this.toolsName = toolsName;
    }

    String borrower;
    String borrowTime;
    String giver;
    String toolsName;

}
