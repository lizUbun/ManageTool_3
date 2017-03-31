package com.xlc.managetool_3;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by zlb on 2017/3/21.
 */

public class Tools extends DataSupport {
    @Column(unique = false)
    private String name;
    private Integer amount;




    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
