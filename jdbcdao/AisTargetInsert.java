package com.ruoyi.project.system.aistarget.jdbcdao;

import com.ruoyi.project.system.aistarget.dbconn.Dbconntool;
import com.ruoyi.project.system.aistarget.domain.ShipTarget_Orighis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName AisTargetInsert
 * @Description 实现多线程+批量+事务
 * 1.拆分list 2.将拆分好的list作为参数加入操作类 3.拆分的时候进行创建线程 4.
 * @Author 李怀鹏
 * @Date 2019/5/7 14:13
 * @Version 1.0
 **/
public class AisTargetInsert extends Thread {
    private Connection connection;
    //private PreparedStatement ps;
    private List<ShipTarget_Orighis> list;//拆分后的List
    private CountDownLatch countDownLatch;
    public AisTargetInsert(Connection connection,List<ShipTarget_Orighis> list1,CountDownLatch countDownLatch){
        this.connection = connection;
        this.list = list1;
        this.countDownLatch=countDownLatch;
    }
    @Override
    public void run() {
        PreparedStatement ps = null;
        String sql ="INSERT INTO l1_orig_target_history_positions_shipidkey_2017(Target_ID,Record_UTC_Time,Longitude,Latitude,Direction,Heading,Speed,Status,ROT_Sensor,Orig_Info_Type,Orig_Info_Source,TargetIDOrig_Type,Target_ID_Orig,MMSI,Ship_Name,Call_Sign,IMO_Number,Destination,ETA,Draft,Country_Name,AIS_Ship_Type,Aggregated_AIS_Ship_Type_ID,Cargo_Type,Ship_Length,Ship_Breadth,Fixing_Device,Pos_Accuracy,Commun_State,Threat_Level,BeiDou_ID,Haijian_ID,ArgosAndMarineSat_ID)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int n = 0;//记录插入条数
        try {
            ps = connection.prepareStatement(sql);
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
                    ps.clearBatch();//清除之前的数据
                    connection.commit();
                    connection.setAutoCommit(false);
                    System.out.println(this.getName()+"我执行了！！！");
                }
            }
            if (list.size()<100000){
                ps.executeBatch();
                connection.commit();
                System.out.println("最后余数提交完成！"+list.size()+connection.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            //throw new Exception(e.getMessage());
        }finally {
            Dbconntool.close(null,ps,null);
            countDownLatch.countDown();
            System.out.println(this.getName()+"countDownLatch"+countDownLatch);
        }
        System.out.println("正在执行线程"+this.getName());
    }

    /**
     * 插入目标表 批量插入 批量操作+事务
     * @return
     */
}
