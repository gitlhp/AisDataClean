package com.ruoyi.project.system.aistarget.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @ClassName L1_orig_dong_last_ab
 * @Description l1_ship_last_position_classab_170320_170703`==/* l1_ship_last_position_classab_1506_18动态 *
 * @Author 李怀鹏
 * @Date 2019/4/12 15:18
 * @Version 1.0
 **/
public class L1_orig_dong_last_ab extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 1.船舶ID   表结构是int类型*/
    private String ship_ID;

    /** 2.A类设备还是B类设备*/
    private int classType;

    /** 3.存储船舶的临时shipID，给程序提供方便*/
    private int temp_ShipID;

    /** 4.MMSI*/
    private int mmsi;

    /** 5.MMSI字段的置信度百分比*/
    private int confidence_MMSI;

    /** 6.相同MMSI的不同船舶的数量*/
    private int ship_Count_With_Same_MMSI;

    /** 7.船舶ID的置信度 */
    private int belief_Ship_ID;

    /** 8.utc时间 */
    private int record_Datetime;

    /** 9.经度 */
    private int longitude; //除以600000得度数

    /** 10.纬度 */
    private int latitude; //除以600000得度数

    /** 11.船航向 */
    private int direction;

    /** 12.船首向 */
    private int heading;

    /** 13.船航向置信度 */
    private int confidence_Direction;

    /** 14.船速 */
    private int speed;

    /** 15.船速置信度 */
    private int confidence_Speed;

    /** 16.船舶运行状态 */
    private int status;

    /** 17.船舶运行状态置信度 */
    private int confidence_Status;

    /** 18.传感器输出的转向率 */
    private int rot_Sensor;

    /** 19.传感器输出的转向率置信度 */
    private int confidence_ROT_Sensor;

    /** 20.数据来源 */
    private int ais_Source;

    /** 21.upt_time */
    private String upt_Time;

    /** 22.船首向置信度 */
    private int confidence_Heading;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getShip_ID() {
        return ship_ID;
    }

    public void setShip_ID(String ship_ID) {
        this.ship_ID = ship_ID;
    }

    public int getClassType() {
        return classType;
    }

    public void setClassType(int classType) {
        this.classType = classType;
    }

    public int getTemp_ShipID() {
        return temp_ShipID;
    }

    public void setTemp_ShipID(int temp_ShipID) {
        this.temp_ShipID = temp_ShipID;
    }

    public int getMmsi() {
        return mmsi;
    }

    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    public int getConfidence_MMSI() {
        return confidence_MMSI;
    }

    public void setConfidence_MMSI(int confidence_MMSI) {
        this.confidence_MMSI = confidence_MMSI;
    }

    public int getShip_Count_With_Same_MMSI() {
        return ship_Count_With_Same_MMSI;
    }

    public void setShip_Count_With_Same_MMSI(int ship_Count_With_Same_MMSI) {
        this.ship_Count_With_Same_MMSI = ship_Count_With_Same_MMSI;
    }

    public int getBelief_Ship_ID() {
        return belief_Ship_ID;
    }

    public void setBelief_Ship_ID(int belief_Ship_ID) {
        this.belief_Ship_ID = belief_Ship_ID;
    }

    public int getRecord_Datetime() {
        return record_Datetime;
    }

    public void setRecord_Datetime(int record_Datetime) {
        this.record_Datetime = record_Datetime;
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

    public int getConfidence_Direction() {
        return confidence_Direction;
    }

    public void setConfidence_Direction(int confidence_Direction) {
        this.confidence_Direction = confidence_Direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getConfidence_Speed() {
        return confidence_Speed;
    }

    public void setConfidence_Speed(int confidence_Speed) {
        this.confidence_Speed = confidence_Speed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getConfidence_Status() {
        return confidence_Status;
    }

    public void setConfidence_Status(int confidence_Status) {
        this.confidence_Status = confidence_Status;
    }

    public int getRot_Sensor() {
        return rot_Sensor;
    }

    public void setRot_Sensor(int rot_Sensor) {
        this.rot_Sensor = rot_Sensor;
    }

    public int getConfidence_ROT_Sensor() {
        return confidence_ROT_Sensor;
    }

    public void setConfidence_ROT_Sensor(int confidence_ROT_Sensor) {
        this.confidence_ROT_Sensor = confidence_ROT_Sensor;
    }

    public int getAis_Source() {
        return ais_Source;
    }

    public void setAis_Source(int ais_Source) {
        this.ais_Source = ais_Source;
    }

    public String getUpt_Time() {
        return upt_Time;
    }

    public void setUpt_Time(String upt_Time) {
        this.upt_Time = upt_Time;
    }

    public int getConfidence_Heading() {
        return confidence_Heading;
    }

    public void setConfidence_Heading(int confidence_Heading) {
        this.confidence_Heading = confidence_Heading;
    }
}
