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
    Integer amount;
    String giveBacker;
    String giveBackReceiver;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGiveBacker() {
        return giveBacker;
    }

    public void setGiveBacker(String giveBacker) {
        this.giveBacker = giveBacker;
    }

    public String getGiveBackReceiver() {
        return giveBackReceiver;
    }

    public void setGiveBackReceiver(String giveBackReceiver) {
        this.giveBackReceiver = giveBackReceiver;
    }

    public String getRecevTime() {
        return recevTime;
    }

    public void setRecevTime(String recevTime) {
        this.recevTime = recevTime;
    }

    public String getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(String deviceState) {
        this.deviceState = deviceState;
    }

    String recevTime;
    String deviceState;

}
