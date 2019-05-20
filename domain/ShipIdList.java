package com.ruoyi.project.system.aistarget.domain;

/**
 * @ClassName ShipIdList
 * @Description shipidlist
 * @Author 李怀鹏
 * @Date 2019/5/20 9:56
 * @Version 1.0
 **/
public class ShipIdList {
    private int id;
    private int shipid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShipid() {
        return shipid;
    }

    public void setShipid(int shipid) {
        this.shipid = shipid;
    }
    @Override
    public String toString() {
        return "ShipIdList{" +
                "id=" + id +
                ", shipid=" + shipid +
                '}';
    }
}
