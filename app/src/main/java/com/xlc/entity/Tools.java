package com.xlc.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by zlb on 2017/3/21.
 */

public class Tools extends DataSupport {
    @Column(unique = false)
    private String name;
    private Integer amount;
    private String brand;
    private String type;
    private String color;
    private String material;
    private String standard;
    private String size;
    private String picture;
    private String remark;
    private Integer id;

    public String getTool_state() {
        return tool_state;
    }

    public void setTool_state(String tool_state) {
        this.tool_state = tool_state;
    }

    private String tool_state;

    public int getId_second() {
        return id_second;
    }

    public void setId_second(int id_second) {
        this.id_second = id_second;
    }

    private int id_second;

    public String getCorrectSize() {
        return correctSize;
    }

    public void setCorrectSize(String correctSize) {
        this.correctSize = correctSize;
    }

    private String correctSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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
