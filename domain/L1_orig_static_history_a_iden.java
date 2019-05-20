package com.ruoyi.project.system.aistarget.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @ClassName L1_orig_static_history_a_iden
 * @Description l1_ship_identity_classa_history_160302_999999`==/* l1_ship_identity_classa_history_150629_160302静态
 *
 * @Author 李怀鹏
 * @Date 2019/4/12 15:52
 * @Version 1.0
 **/
public class L1_orig_static_history_a_iden extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 1.MMSI */
    private int mmsi;

    /** 2.船名 */
    private String ship_Name;

    /** 3.呼号\n7*6比特ASCII字符，@@@@@@@=不可用=默认值 */
    private String call_Sign;

    /** 4.IMO编号 */
    private int imo_Number;

    /** 5.非空，该状态起始的时间 */
    private int start_Datetime;

    /** 6 */
    private int confidence_MMSI;

    /** 7 */
    private int confidence_Ship_Name;

    /** 8 */
    private int confidence_Call_Sign;

    /** 9 */
    private int confidence_IMO_Number;

    /** 10.船舶类型 */
    private int ship_Type;

    /** 11.船头距离 */
    private int to_Bow;

    /** 12.船尾距离 */
    private int to_Stern;

    /** 13.左舷距离 */
    private int to_Port;

    /** 14.右舷距离 */
    private int to_StarBoard;

    /** 15.电子定位装置的类型 */
    private int Fixing_Device;

    /** 16. */
    private int is_IMO_Number_Valid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getMmsi() {
        return mmsi;
    }

    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    public String getShip_Name() {
        return ship_Name;
    }

    public void setShip_Name(String ship_Name) {
        this.ship_Name = ship_Name;
    }

    public String getCall_Sign() {
        return call_Sign;
    }

    public void setCall_Sign(String call_Sign) {
        this.call_Sign = call_Sign;
    }

    public int getImo_Number() {
        return imo_Number;
    }

    public void setImo_Number(int imo_Number) {
        this.imo_Number = imo_Number;
    }

    public int getStart_Datetime() {
        return start_Datetime;
    }

    public void setStart_Datetime(int start_Datetime) {
        this.start_Datetime = start_Datetime;
    }

    public int getConfidence_MMSI() {
        return confidence_MMSI;
    }

    public void setConfidence_MMSI(int confidence_MMSI) {
        this.confidence_MMSI = confidence_MMSI;
    }

    public int getConfidence_Ship_Name() {
        return confidence_Ship_Name;
    }

    public void setConfidence_Ship_Name(int confidence_Ship_Name) {
        this.confidence_Ship_Name = confidence_Ship_Name;
    }

    public int getConfidence_Call_Sign() {
        return confidence_Call_Sign;
    }

    public void setConfidence_Call_Sign(int confidence_Call_Sign) {
        this.confidence_Call_Sign = confidence_Call_Sign;
    }

    public int getConfidence_IMO_Number() {
        return confidence_IMO_Number;
    }

    public void setConfidence_IMO_Number(int confidence_IMO_Number) {
        this.confidence_IMO_Number = confidence_IMO_Number;
    }

    public int getShip_Type() {
        return ship_Type;
    }

    public void setShip_Type(int ship_Type) {
        this.ship_Type = ship_Type;
    }

    public int getTo_Bow() {
        return to_Bow;
    }

    public void setTo_Bow(int to_Bow) {
        this.to_Bow = to_Bow;
    }

    public int getTo_Stern() {
        return to_Stern;
    }

    public void setTo_Stern(int to_Stern) {
        this.to_Stern = to_Stern;
    }

    public int getTo_Port() {
        return to_Port;
    }

    public void setTo_Port(int to_Port) {
        this.to_Port = to_Port;
    }

    public int getTo_StarBoard() {
        return to_StarBoard;
    }

    public void setTo_StarBoard(int to_StarBoard) {
        this.to_StarBoard = to_StarBoard;
    }

    public int getFixing_Device() {
        return Fixing_Device;
    }

    public void setFixing_Device(int fixing_Device) {
        Fixing_Device = fixing_Device;
    }

    public int getIs_IMO_Number_Valid() {
        return is_IMO_Number_Valid;
    }

    public void setIs_IMO_Number_Valid(int is_IMO_Number_Valid) {
        this.is_IMO_Number_Valid = is_IMO_Number_Valid;
    }
}
