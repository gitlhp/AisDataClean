package com.ruoyi.project.system.aistarget.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @ClassName ShipTarget_OrighisVo
 * @Description 用来接收原始数据可以提供的所有字段 通过多表关联查询得到
 * @Author 李怀鹏
 * @Date 2019/4/18 17:15
 * @Version 1.0
 **/
public class ShipTarget_OrighisVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

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
    /** 11.船头距离 */
    private int to_Bow;

    /** 12.船尾距离 */
    private int to_Stern;

    /** 13.左舷距离 */
    private int to_Port;

    /** 14.右舷距离 */
    private int to_StarBoard;

    /** 枚举，AIS中的船舶类型 */
    private int ais_Ship_Type;

    /** 电子定位装置的类型 */
    private int fixing_Device;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public int getAis_Ship_Type() {
        return ais_Ship_Type;
    }

    public void setAis_Ship_Type(int ais_Ship_Type) {
        this.ais_Ship_Type = ais_Ship_Type;
    }

    public int getFixing_Device() {
        return fixing_Device;
    }

    public void setFixing_Device(int fixing_Device) {
        this.fixing_Device = fixing_Device;
    }

    @Override
    public String toString() {
        return "ShipTarget_OrighisVo{" +
                "record_UTC_Time=" + record_UTC_Time +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", direction=" + direction +
                ", heading=" + heading +
                ", speed=" + speed +
                ", status=" + status +
                ", rot_Sensor=" + rot_Sensor +
                ", mmsi=" + mmsi +
                ", ship_Name='" + ship_Name + '\'' +
                ", call_Sign='" + call_Sign + '\'' +
                ", imo_Number=" + imo_Number +
                ", destination='" + destination + '\'' +
                ", eta='" + eta + '\'' +
                ", draft=" + draft +
                ", to_Bow=" + to_Bow +
                ", to_Stern=" + to_Stern +
                ", to_Port=" + to_Port +
                ", to_StarBoard=" + to_StarBoard +
                ", ais_Ship_Type=" + ais_Ship_Type +
                ", fixing_Device=" + fixing_Device +
                '}';
    }
}
