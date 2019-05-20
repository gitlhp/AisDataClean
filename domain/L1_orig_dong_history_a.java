package com.ruoyi.project.system.aistarget.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @ClassName ShipD1
 * @Description l1_ship_history_positions_classa_170320_170703 船舶动态信息表1
 * L1_orig_dong_history_a 动态
 * @Author 李怀鹏
 * @Date 2019/3/26 17:37
 * @Version 1.0
 **/
public class L1_orig_dong_history_a extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 船舶ID   表结构是int类型*/
    private String ship_ID;

    /** 船舶ID的置信度 */
    private int belief_Ship_ID;

    /** GPS时间 */
    private int record_gps_Time;

    /** 经度 */
    private int longitude; //除以600000得度数

    /** 纬度 */
    private int latitude; //除以600000得度数

    /** 目的地 */
    private int direction;

    /** 船首向 */
    private int heading;

    /** 船速 */
    private int speed;

    /** 船舶运行状态 */
    private int status;

    /** 传感器输出的转向率 */
    private int rot_Sensor;

    /** 数据来源 */
    private int ais_Source;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getShip_ID() {
        return ship_ID;
    }

    public void setShip_ID(String ship_ID) {
        this.ship_ID = ship_ID;
    }

    public int getBelief_Ship_ID() {
        return belief_Ship_ID;
    }

    public void setBelief_Ship_ID(int belief_Ship_ID) {
        this.belief_Ship_ID = belief_Ship_ID;
    }

    //public int getRecord_GPS_Time() {
    //    return record_GPS_Time;
    //}
    //
    //public void setRecord_GPS_Time(int record_GPS_Time) {
    //    this.record_GPS_Time = record_GPS_Time;
    //}


    public int getRecord_gps_Time() {
        return record_gps_Time;
    }

    public void setRecord_gps_Time(int record_gps_Time) {
        this.record_gps_Time = record_gps_Time;
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

    public int getAis_Source() {
        return ais_Source;
    }

    public void setAis_Source(int ais_Source) {
        this.ais_Source = ais_Source;
    }
}
