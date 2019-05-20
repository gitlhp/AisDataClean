package com.ruoyi.project.system.aistarget.jdbcdao;

import com.ruoyi.project.system.aistarget.dbconn.Dbconntool;
import com.ruoyi.project.system.aistarget.domain.L1_orig_dong_history_a;
import com.ruoyi.project.system.aistarget.domain.L1_orig_static_history_a_iden;
import com.ruoyi.project.system.aistarget.domain.L1_orig_static_history_a_vo;
import com.ruoyi.project.system.aistarget.domain.ShipTarget_Orighis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AisTargetDaoImpl
 * @Description 重写操作数据库
 * @Author 李怀鹏
 * @Date 2019/4/26 10:51
 * @Version 1.0
 **/

public class AisTargetDaoImpl implements AisTargetDao{
    private Connection connection;//数据库连接
    public AisTargetDaoImpl(Connection connection){
        this.connection = connection;
    }
    /**
     * 查询动表中所有的ShipId的信息 然后根据此查询AB表中的MMSI
     * @return
     */
    @Override
    public List<Integer> getShipIdByDongHisA() throws Exception {
        PreparedStatement ps = null;
        ResultSet res = null;
        //String sql = "select distinct Ship_ID FROM l1_ship_last_position_classab_170320_170703";
        String sql = "select distinct Ship_ID FROM L1_Ship_Last_Position_ClassAB_1505_1805 limit 1";
        List<Integer> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()){
                list.add(res.getInt("Ship_ID"));
            }
        } catch (SQLException e) {
            throw new Exception("数据库存在问题，请查看！");
        }finally {
            Dbconntool.close(res,ps,null);
        }
        return list;
    }
    /**
     * 通过shipid查询MMSI（多对一）
     * @param shipid
     * @return
     */
    @Override
    public int getMmsiByDongLastab(int shipid) throws Exception {
        PreparedStatement ps = null;
        ResultSet res = null;
        //String sql = " select MMSI from l1_ship_last_position_classab_170320_170703 WHERE Ship_ID=?";
        String sql = " select MMSI from L1_Ship_Last_Position_ClassAB_1505_1805 WHERE Ship_ID=?";
        int mmsi = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,shipid);
            res = ps.executeQuery();
            while (res.next()){
                mmsi = res.getInt("MMSI");
            }

        } catch (SQLException e) {
            throw new Exception("数据库存在问题，请查看！");
        }finally {
            Dbconntool.close(res,ps,null);
        }
        return mmsi;
    }

    @Override
    public List<Integer> getMMSI() {
        return null;
    }
    /**
     * 根据ab表中的MMSI与ShipId的关系查询一个ShipId对应的一艘船的所有动态信息
     * @param shipid
     * @return
     */
    @Override
    public List<L1_orig_dong_history_a> getL1_Orig_Dong_HisaList(int shipid) throws Exception {
        PreparedStatement ps = null;
        ResultSet res = null;
        //String sql = " select Record_GPS_Time,Longitude,Latitude,Direction,Heading,Speed,Status,ROT_Sensor from l1_ship_history_positions_classa_170320_170703 WHERE Ship_ID=? ORDER BY Record_GPS_Time";
        String sql = " select Record_GPS_Time,Longitude,Latitude,Direction,Heading,Speed,Status,ROT_Sensor from L1_Ship_History_Positions_1505_1805 WHERE Ship_ID=? ORDER BY Record_GPS_Time ";
        List<L1_orig_dong_history_a> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,shipid);
            res = ps.executeQuery();
            while (res.next()){
                L1_orig_dong_history_a dong = new L1_orig_dong_history_a();
                dong.setRecord_gps_Time(res.getInt("Record_GPS_Time"));
                dong.setLongitude(res.getInt("Longitude"));
                dong.setLatitude(res.getInt("Latitude"));
                dong.setDirection(res.getInt("Direction"));
                dong.setHeading(res.getInt("Heading"));
                dong.setSpeed(res.getInt("Speed"));
                dong.setStatus(res.getInt("Status"));
                dong.setRot_Sensor(res.getInt("ROT_Sensor"));
                list.add(dong);
                dong = null;
            }
        } catch (SQLException e) {
            throw new Exception("数据库存在问题，请查看！");
        }finally {
            Dbconntool.close(res,ps,null);
        }
        return list;
    }
    /**
     * 根据动态表ab中ShipId和MMSI的关系 查询静态表中对应的静态信息 动ab中MMSI对应静态MMSI
     * @param mmsi
     * @return
     */
    @Override
    public List<L1_orig_static_history_a_vo> getVoAList(int mmsi) throws Exception {
        PreparedStatement ps = null;
        ResultSet res = null;
        //String sql = "select MMSI,Ship_Name,Call_Sign,IMO_Number,Start_Datetime,Destination,ETA,Draft FROM l1_ship_voyage_classa_history_160302_999999 WHERE MMSI=? ORDER BY Start_Datetime";
        String sql = "select MMSI,Ship_Name,Call_Sign,IMO_Number,Start_Datetime,Destination,ETA,Draft FROM L1_Ship_Voyage_ClassA_History_150629_1805 WHERE MMSI=? ORDER BY Start_Datetime";
        List<L1_orig_static_history_a_vo> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,mmsi);
            res = ps.executeQuery();
            while (res.next()){
                L1_orig_static_history_a_vo vo = new L1_orig_static_history_a_vo();
                vo.setMmsi(res.getInt("MMSI"));
                vo.setDraft(res.getInt("Draft"));
                vo.setShip_Name(res.getString("Ship_Name"));
                vo.setCall_Sign(res.getString("Call_Sign"));
                vo.setImo_Number(res.getInt("IMO_Number"));
                vo.setStart_Datetime(res.getInt("Start_Datetime"));
                vo.setDestination(res.getString("Destination"));
                vo.setEta(res.getString("ETA"));
                list.add(vo);
                vo = null;
            }
        } catch (SQLException e) {
            throw new Exception("数据库存在问题，请查看！");
        }finally {
            Dbconntool.close(res,ps,null);
        }
        return list;
    }
    /**
     * 同上
     * @param mmsi
     * @return
     */
    @Override
    public List<L1_orig_static_history_a_iden> getIdenList(int mmsi) throws Exception {
        PreparedStatement ps = null;
        ResultSet res = null;
        //String sql = "select MMSI,Ship_Name,Call_Sign,IMO_Number,Start_Datetime,Ship_Type,To_Bow,To_Stern,To_Port,To_StarBoard,Fixing_Device FROM l1_ship_identity_classa_history_160302_999999 WHERE MMSI=? ORDER BY Start_Datetime";
        String sql = "select MMSI,Ship_Name,Call_Sign,IMO_Number,Start_Datetime,Ship_Type,To_Bow,To_Stern,To_Port,To_StarBoard,Fixing_Device FROM L1_Ship_Identity_ClassA_History_150629_1805 WHERE MMSI=? ORDER BY Start_Datetime";
        List<L1_orig_static_history_a_iden> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,mmsi);
            res = ps.executeQuery();
            while (res.next()){
                L1_orig_static_history_a_iden iden = new L1_orig_static_history_a_iden();
                iden.setMmsi(res.getInt("MMSI"));
                iden.setShip_Name(res.getString("Ship_Name"));
                iden.setCall_Sign(res.getString("Call_Sign"));
                iden.setImo_Number(res.getInt("IMO_Number"));
                iden.setStart_Datetime(res.getInt("Start_Datetime"));
                iden.setShip_Type(res.getInt("Ship_Type"));
                iden.setTo_Bow(res.getInt("To_Bow"));
                iden.setTo_Port(res.getInt("To_Port"));
                iden.setTo_Stern(res.getInt("To_Stern"));
                iden.setTo_StarBoard(res.getInt("To_StarBoard"));
                iden.setFixing_Device(res.getInt("Fixing_Device"));
                list.add(iden);
                iden = null;
            }
        } catch (SQLException e) {
            throw new Exception("数据库存在问题，请查看！");
        }finally {
            Dbconntool.close(res,ps,null);
        }
        return list;
    }

    /**
     * 插入目标表 批量插入 批量操作+事务
     * @return
     */

    @Override
    public int insertShipTarget_Orighis(List<ShipTarget_Orighis> list) throws Exception {
        PreparedStatement ps = null;
        //String sql ="INSERT INTO l1_orig_target_history_positions_shipidkey_2017(Target_ID,Record_UTC_Time,Longitude,Latitude,Direction,Heading,Speed,Status,ROT_Sensor,Orig_Info_Type,Orig_Info_Source,TargetIDOrig_Type,Target_ID_Orig,MMSI,Ship_Name,Call_Sign,IMO_Number,Destination,ETA,Draft,Country_Name,AIS_Ship_Type,Aggregated_AIS_Ship_Type_ID,Cargo_Type,Ship_Length,Ship_Breadth,Fixing_Device,Pos_Accuracy,Commun_State,Threat_Level,BeiDou_ID,Haijian_ID,ArgosAndMarineSat_ID)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String sql ="INSERT INTO l1_orig_target_history_positions_shipidkey_2017(Target_ID,Record_UTC_Time,Longitude,Latitude,Direction,Heading,Speed,Status,ROT_Sensor,Orig_Info_Type,Orig_Info_Source,TargetIDOrig_Type,Target_ID_Orig,MMSI,Ship_Name,Call_Sign,IMO_Number,Destination,ETA,Draft,Country_Name,AIS_Ship_Type,Aggregated_AIS_Ship_Type_ID,Cargo_Type,Ship_Length,Ship_Breadth,Fixing_Device,Pos_Accuracy,Commun_State,Threat_Level,BeiDou_ID,Haijian_ID,ArgosAndMarineSat_ID)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int n = 0;//记录插入条数
        try {
            ps = connection.prepareStatement(sql);//预编译下
            connection.setAutoCommit(false);
            for (ShipTarget_Orighis i:list) {
                ps.setInt(1,i.getTarget_ID());
                ps.setInt(2,i.getRecord_UTC_Time());
                ps.setInt(3,i.getLongitude());
                ps.setInt(4,i.getLatitude());
                ps.setInt(5,i.getDirection());
                ps.setInt(6,i.getHeading());
                ps.setInt(7,i.getSpeed());
                ps.setInt(8,i.getStatus());
                ps.setInt(9,i.getRot_Sensor());
                ps.setInt(10,i.getOrig_Info_Type());
                ps.setInt(11,i.getOrig_Info_Source());
                ps.setInt(12,i.getTargetIDOrig_Type());
                ps.setInt(13,i.getTarget_ID_Orig());
                ps.setInt(14,i.getMmsi());
                ps.setString(15,i.getShip_Name());
                ps.setString(16,i.getCall_Sign());
                ps.setInt(17,i.getImo_Number());
                ps.setString(18,i.getDestination());
                ps.setString(19,i.getEta());
                ps.setInt(20,i.getDraft());
                ps.setString(21,i.getCountry_Name());
                ps.setInt(22,i.getAis_Ship_Type());
                ps.setInt(23,i.getAggregated_AIS_Ship_Type_ID());
                ps.setInt(24,i.getCargo_Type());
                ps.setInt(25,i.getShip_Length());
                ps.setInt(26,i.getShip_Breadth());
                ps.setInt(27,i.getFixing_Device());
                ps.setInt(28,i.getPos_Accuracy());
                ps.setInt(29,i.getCommun_State());
                ps.setInt(30,i.getThreat_Level());
                ps.setInt(31,i.getBeiDou_ID());
                ps.setInt(32,i.getHaijian_ID());
                ps.setInt(33,i.getArgosAndMarineSat_ID());
                ps.addBatch();
                n++;
                if (n%10000==0){//每10000条提交一次
                    ps.executeBatch();
                    connection.commit();
                    connection.setAutoCommit(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }finally {
            Dbconntool.close(null,ps,null);
        }
        return 10;
    }

    /**
     * 查询目标表数据
     */
    @Override
    public List<ShipTarget_Orighis> getShipTarget_OrighisList() throws Exception{
        PreparedStatement ps = null;
        ResultSet res = null;
        //String sql = "select * from l1_orig_target_history_positions_shipidkey_2017 limit 100";
        String sql = "select * from l1_orig_target_history_positions_shipidkey_2017 limit 100";
        List<ShipTarget_Orighis> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()){
                ShipTarget_Orighis ship = new ShipTarget_Orighis();
                ship.setTarget_ID(res.getInt("Target_ID"));
                ship.setRecord_UTC_Time(res.getInt("Record_UTC_Time"));
                ship.setLongitude(res.getInt("Longitude"));
                ship.setLatitude(res.getInt("Latitude"));
                ship.setDirection(res.getInt("Direction"));
                ship.setHeading(res.getInt("Heading"));
                ship.setSpeed(res.getInt("Speed"));
                ship.setStatus(res.getInt("Status"));
                ship.setRot_Sensor(res.getInt("ROT_Sensor"));
                ship.setOrig_Info_Type(res.getInt("Orig_Info_Type"));
                ship.setOrig_Info_Source(res.getInt("Orig_Info_Source"));
                ship.setTargetIDOrig_Type(res.getInt("TargetIDOrig_Type"));
                ship.setTarget_ID_Orig(res.getInt("Target_ID_Orig"));
                ship.setMmsi(res.getInt("MMSI"));
                ship.setShip_Name(res.getString("Ship_Name"));
                ship.setCall_Sign(res.getString("Call_Sign"));
                ship.setImo_Number(res.getInt("IMO_Number"));
                ship.setDestination(res.getString("Destination"));
                ship.setEta(res.getString("ETA"));
                ship.setDraft(res.getInt("Draft"));
                ship.setCountry_Name(res.getString("Country_Name"));
                ship.setAis_Ship_Type(res.getInt("AIS_Ship_Type"));
                ship.setAggregated_AIS_Ship_Type_ID(res.getInt("Aggregated_AIS_Ship_Type_ID"));
                ship.setCargo_Type(res.getInt("Cargo_Type"));
                ship.setShip_Length(res.getInt("Ship_Length"));
                ship.setShip_Breadth(res.getInt("Ship_Breadth"));
                ship.setFixing_Device(res.getInt("Fixing_Device"));
                ship.setPos_Accuracy(res.getInt("Pos_Accuracy"));
                ship.setCommun_State(res.getInt("Commun_State"));
                ship.setThreat_Level(res.getInt("Threat_Level"));
                ship.setBeiDou_ID(res.getInt("BeiDou_ID"));
                ship.setHaijian_ID(res.getInt("Haijian_ID"));
                ship.setArgosAndMarineSat_ID(res.getInt("ArgosAndMarineSat_ID"));
                list.add(ship);
                ship = null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("数据库存在问题，请查看！");
        }finally {
            Dbconntool.close(null,ps,null);
        }
        return list;
    }
}
