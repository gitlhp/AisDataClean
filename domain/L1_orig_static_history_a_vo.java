package com.ruoyi.project.system.aistarget.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @ClassName L1_orig_static_history_a_vo
 * @Description l1_ship_voyage_classa_history_160302_999999`==/* l1_ship_voyage_classa_history_ 静态
 * 提供 船名，吃水，呼号，到达时间等静态信息
 * @Author 李怀鹏
 * @Date 2019/4/12 15:51
 * @Version 1.0
 **/
public class L1_orig_static_history_a_vo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 1.Ship_ID */
    private int ship_ID;

    /** 2.该条记录的置信度百分比 */
    private int confidence_Record;

    /** 3.非空，该状态起始的时间 */
    private int start_Datetime;

    /** 4.MMSI */
    private int mmsi;

    /** 5.船名 */
    private String ship_Name;

    /** 6.呼号\n7*6比特ASCII字符，@@@@@@@=不可用=默认值 */
    private String call_Sign;

    /** 7.IMO编号 */
    private int imo_Number;

    /** 8.目的港口 */
    private String destination;

    /** 9.目的港口置信度 */
    private int confidence_Destination;

    /** 10.预计到达时间 */
    private String eta;

    /** 11.预计到达时间置信度 */
    private int confidence_ETA;

    /** 12.目前最大静态吃水 */
    private int draft;

    /** 13.Confidence_Draft */
    private int confidence_Draft;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getShip_ID() {
        return ship_ID;
    }

    public void setShip_ID(int ship_ID) {
        this.ship_ID = ship_ID;
    }

    public int getConfidence_Record() {
        return confidence_Record;
    }

    public void setConfidence_Record(int confidence_Record) {
        this.confidence_Record = confidence_Record;
    }

    public int getStart_Datetime() {
        return start_Datetime;
    }

    public void setStart_Datetime(int start_Datetime) {
        this.start_Datetime = start_Datetime;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getConfidence_Destination() {
        return confidence_Destination;
    }

    public void setConfidence_Destination(int confidence_Destination) {
        confidence_Destination = confidence_Destination;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public int getConfidence_ETA() {
        return confidence_ETA;
    }

    public void setConfidence_ETA(int confidence_ETA) {
        this.confidence_ETA = confidence_ETA;
    }

    public int getDraft() {
        return draft;
    }

    public void setDraft(int draft) {
        this.draft = draft;
    }

    public int getConfidence_Draft() {
        return confidence_Draft;
    }

    public void setConfidence_Draft(int confidence_Draft) {
        this.confidence_Draft = confidence_Draft;
    }
}
