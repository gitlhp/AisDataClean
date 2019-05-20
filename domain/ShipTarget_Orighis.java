package com.ruoyi.project.system.aistarget.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @ClassName ShipTarget_Orighis
 * @Description 融合前的历史数据，MMSI未去重
 * 对应表是`l1_orig_target_history_positions_shipidkey_2017
 * @Author 李怀鹏
 * @Date 2019/4/9 15:07
 * @Version 1.0
 **/
public class ShipTarget_Orighis extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 目标唯一标识号  */
    private int target_ID;
    /** UTC时间   PRIMARY KEY*/
    private int record_UTC_Time;
    /** 经度 */
    private int longitude;
    /** 纬度 */
    private int latitude;
    /** 目的地 */
    private int direction;
    /** 船首向 */
    private int heading;
    /** 船速 */
    private int speed;
    /** 状态 */
    private int status;
    /** 转向率 */
    private int rot_Sensor;
    /** 信息来源  PRIMARY KEY*/
    private int orig_Info_Type;
    /** 信息来源  PRIMARY KEY*/
    private int orig_Info_Source;
    /** 目标编号类型 */
    private int targetIDOrig_Type;
    /** 原始消息目标编号  PRIMARY KEY*/
    private int target_ID_Orig;
    /** MMSI */
    private int mmsi;
    /** 船名 */
    private String ship_Name;
    /** 编号 */
    private String call_Sign;
    /** IMO_Number编号 */
    private int imo_Number;
    /** 目标港口 */
    private String destination;
    /** 预计到达时间 */
    private String eta;
    /** 目前最大吃水深度 */
    private int draft;
    /** 国家名 */
    private String country_Name;
    /** 枚举，AIS中的船舶类型 */
    private int ais_Ship_Type;
    /** 聚合后的ais船类型 */
    private int aggregated_AIS_Ship_Type_ID;
    /** Cargo_Type */
    private int cargo_Type;
    /** 船长 */
    private int ship_Length;
    /** 船宽 */
    private int ship_Breadth;
    /** 电子定位装置的类型 */
    private int fixing_Device;
    /** 船位精度 */
    private int pos_Accuracy;
    /** 枚举，通信状态 */
    private int commun_State;
    /** 枚举，存储xgs版定制目标威胁等级 */
    private int threat_Level;
    /** 北斗id */
    private int beiDou_ID;
    /** Haijian_ID */
    private int haijian_ID;
    /** argosAndMarineSat_ID */
    private int argosAndMarineSat_ID;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getTarget_ID() {
        return target_ID;
    }

    public void setTarget_ID(int target_ID) {
        this.target_ID = target_ID;
    }

    public int getRecord_UTC_Time() {
        return record_UTC_Time;
    }

    public void setRecord_UTC_Time(int record_UTC_Time) {
        this.record_UTC_Time = record_UTC_Time;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRot_Sensor() {
        return rot_Sensor;
    }

    public void setRot_Sensor(int rot_Sensor) {
        this.rot_Sensor = rot_Sensor;
    }

    public int getOrig_Info_Type() {
        return orig_Info_Type;
    }

    public void setOrig_Info_Type(int orig_Info_Type) {
        this.orig_Info_Type = orig_Info_Type;
    }

    public int getOrig_Info_Source() {
        return orig_Info_Source;
    }

    public void setOrig_Info_Source(int orig_Info_Source) {
        this.orig_Info_Source = orig_Info_Source;
    }

    public int getTargetIDOrig_Type() {
        return targetIDOrig_Type;
    }

    public void setTargetIDOrig_Type(int targetIDOrig_Type) {
        this.targetIDOrig_Type = targetIDOrig_Type;
    }

    public int getTarget_ID_Orig() {
        return target_ID_Orig;
    }

    public void setTarget_ID_Orig(int target_ID_Orig) {
        this.target_ID_Orig = target_ID_Orig;
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

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public int getDraft() {
        return draft;
    }

    public void setDraft(int draft) {
        this.draft = draft;
    }

    public String getCountry_Name() {
        return country_Name;
    }

    public void setCountry_Name(String country_Name) {
        this.country_Name = country_Name;
    }

    public int getAis_Ship_Type() {
        return ais_Ship_Type;
    }

    public void setAis_Ship_Type(int ais_Ship_Type) {
        this.ais_Ship_Type = ais_Ship_Type;
    }

    public int getAggregated_AIS_Ship_Type_ID() {
        return aggregated_AIS_Ship_Type_ID;
    }

    public void setAggregated_AIS_Ship_Type_ID(int aggregated_AIS_Ship_Type_ID) {
        this.aggregated_AIS_Ship_Type_ID = aggregated_AIS_Ship_Type_ID;
    }

    public int getCargo_Type() {
        return cargo_Type;
    }

    public void setCargo_Type(int cargo_Type) {
        this.cargo_Type = cargo_Type;
    }

    public int getShip_Length() {
        return ship_Length;
    }

    public void setShip_Length(int ship_Length) {
        this.ship_Length = ship_Length;
    }

    public int getShip_Breadth() {
        return ship_Breadth;
    }

    public void setShip_Breadth(int ship_Breadth) {
        this.ship_Breadth = ship_Breadth;
    }

    public int getFixing_Device() {
        return fixing_Device;
    }

    public void setFixing_Device(int fixing_Device) {
        this.fixing_Device = fixing_Device;
    }

    public int getPos_Accuracy() {
        return pos_Accuracy;
    }

    public void setPos_Accuracy(int pos_Accuracy) {
        this.pos_Accuracy = pos_Accuracy;
    }

    public int getCommun_State() {
        return commun_State;
    }

    public void setCommun_State(int commun_State) {
        this.commun_State = commun_State;
    }

    public int getThreat_Level() {
        return threat_Level;
    }

    public void setThreat_Level(int threat_Level) {
        this.threat_Level = threat_Level;
    }

    public int getBeiDou_ID() {
        return beiDou_ID;
    }

    public void setBeiDou_ID(int beiDou_ID) {
        this.beiDou_ID = beiDou_ID;
    }

    public int getHaijian_ID() {
        return haijian_ID;
    }

    public void setHaijian_ID(int haijian_ID) {
        this.haijian_ID = haijian_ID;
    }

    public int getArgosAndMarineSat_ID() {
        return argosAndMarineSat_ID;
    }

    public void setArgosAndMarineSat_ID(int argosAndMarineSat_ID) {
        this.argosAndMarineSat_ID = argosAndMarineSat_ID;
    }

    @Override
    public String toString() {
        return "ShipTarget_Orighis{" +
                "target_ID=" + target_ID +
                ", record_UTC_Time=" + record_UTC_Time +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", direction=" + direction +
                ", heading=" + heading +
                ", speed=" + speed +
                ", status=" + status +
                ", rot_Sensor=" + rot_Sensor +
                ", orig_Info_Type=" + orig_Info_Type +
                ", orig_Info_Source=" + orig_Info_Source +
                ", targetIDOrig_Type=" + targetIDOrig_Type +
                ", target_ID_Orig=" + target_ID_Orig +
                ", mmsi=" + mmsi +
                ", ship_Name='" + ship_Name + '\'' +
                ", call_Sign='" + call_Sign + '\'' +
                ", imo_Number=" + imo_Number +
                ", destination='" + destination + '\'' +
                ", eta='" + eta + '\'' +
                ", draft=" + draft +
                ", country_Name='" + country_Name + '\'' +
                ", ais_Ship_Type=" + ais_Ship_Type +
                ", aggregated_AIS_Ship_Type_ID=" + aggregated_AIS_Ship_Type_ID +
                ", cargo_Type=" + cargo_Type +
                ", ship_Length=" + ship_Length +
                ", ship_Breadth=" + ship_Breadth +
                ", fixing_Device=" + fixing_Device +
                ", pos_Accuracy=" + pos_Accuracy +
                ", commun_State=" + commun_State +
                ", threat_Level=" + threat_Level +
                ", beiDou_ID=" + beiDou_ID +
                ", haijian_ID=" + haijian_ID +
                ", argosAndMarineSat_ID=" + argosAndMarineSat_ID +
                '}';
    }
}
