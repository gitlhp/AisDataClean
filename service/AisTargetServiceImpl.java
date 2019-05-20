package com.ruoyi.project.system.aistarget.service;

import com.ruoyi.project.monitor.server.domain.Sys;
import com.ruoyi.project.system.aistarget.domain.*;
import com.ruoyi.project.system.aistarget.mapper.AisTarget1Mapper;
import com.ruoyi.project.system.aistarget.util.AisDataFieldClean;
import com.ruoyi.project.system.aistarget.util.AisDataFieldCleanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName AisTargetServiceImpl
 * @Description TODO
 * @Author 李怀鹏
 * @Date 2019/4/1 10:04
 * @Version 1.0
 **/
@Service
public class AisTargetServiceImpl implements AisTargetService{

    @Autowired
    private AisTarget1Mapper aisTarget1Mapper;

    @Override
    public List<ShipTarget1> selectShipTarget1List(ShipTarget1 shiptarget1) {
        return aisTarget1Mapper.selectShipTarget1List(shiptarget1);
    }

    @Override
    public int importTarget() {
        return aisTarget1Mapper.importTarget();
    }



    //**************实现方法***************//
    @Override
    public List<L1_orig_dong_history_a> getL1_Orig_Dong_HisaList() {
        return aisTarget1Mapper.getL1_Orig_Dong_HisaList();
    }

    @Override
    public List<Integer> getMMSI() {
        return aisTarget1Mapper.getMMSI();
    }

    @Override
    public List<Integer> getShipIdByDongHisA() {
        return aisTarget1Mapper.getShipIdByDongHisA();
    }

    @Override
    public int getMmsiByDongLastab(int shipid) {
        return aisTarget1Mapper.getMmsiByDongLastab(shipid);
    }

    @Override
    public List<L1_orig_dong_history_a> getL1_Orig_Dong_HisaList(int shipid) {
        return aisTarget1Mapper.getL1_Orig_Dong_HisaList(shipid);
    }

    @Override
    public List<L1_orig_static_history_a_vo> getVoAList(int mmsi) {
        return aisTarget1Mapper.getVoAList(mmsi);
    }

    @Override
    public List<L1_orig_static_history_a_iden> getIdenList(int mmsi) {
        return aisTarget1Mapper.getIdenList(mmsi);
    }
    /**
     * 目前思路（1）：首先将动表和其中一个静态表进行结合 然后在以结合后的适配表作为和另一张静表合并的参数
     * @return
     */

    @Override
    public List<ShipTarget_OrighisVo> getShipTarget_OrighisVoList() {
        //定义存放适配表对象的集合
        List<ShipTarget_OrighisVo> ShipTarget_OrighisVoList = new ArrayList<>();
        //查询所有的ShipId
        List<Integer> shipIdList = getShipIdByDongHisA();
        //每一次循环处理合并一条船对应的信息
        for (int i1 : shipIdList){
            //获取shipid对应的mmsi信息
            int mmsi = getMmsiByDongLastab(i1);
            //通过shipid查询动表a的所需信息
            List<L1_orig_dong_history_a> dongHisAList = getL1_Orig_Dong_HisaList(i1);
            //通过mmsi查询静表vo_a的所需信息
            List<L1_orig_static_history_a_vo> staticHisAList = getVoAList(mmsi);
            //通过mmsi查询静表iden_a的所需信息
            List<L1_orig_static_history_a_iden> staticHisAIdenList = getIdenList(mmsi);
            //循环比较每一个动点和静点的时间序列 进行合并
            for (int i= 0;i<dongHisAList.size();i++){
                //每一次循环都要创建一个适配表实体对象
                ShipTarget_OrighisVo shipTarget_orighisVo = new ShipTarget_OrighisVo();
                for (int j = 0;j<staticHisAList.size();j++){//
                        //如果动点的时间小于静表的起始时间，则让动点合成的静态信息为默认值
                        if (dongHisAList.get(i).getRecord_gps_Time()<staticHisAList.get(0).getStart_Datetime()){
                            //下边时间段内船舶信息没有静态信息
                            shipTarget_orighisVo.setRecord_UTC_Time(dongHisAList.get(i).getRecord_gps_Time());
                            shipTarget_orighisVo.setLongitude(dongHisAList.get(i).getLongitude());
                            shipTarget_orighisVo.setLatitude(dongHisAList.get(i).getLatitude());
                            shipTarget_orighisVo.setHeading(dongHisAList.get(i).getHeading());
                            shipTarget_orighisVo.setSpeed(dongHisAList.get(i).getSpeed());
                            shipTarget_orighisVo.setStatus(dongHisAList.get(i).getStatus());
                            shipTarget_orighisVo.setRot_Sensor(dongHisAList.get(i).getRot_Sensor());
                            shipTarget_orighisVo.setDirection(dongHisAList.get(i).getDirection());
                            shipTarget_orighisVo.setMmsi(mmsi);
                            //再将合并后的适配表和另一张静表合并
                            getShipTarget_OrighisVoListLast(shipTarget_orighisVo,staticHisAIdenList);
                            //将填入信息的适配表添加进集合中
                            ShipTarget_OrighisVoList.add(shipTarget_orighisVo);
                            //释放内存
                            shipTarget_orighisVo = null;
                            //跳出本轮循环
                            break;
                        }else if (dongHisAList.get(i).getRecord_gps_Time()>=staticHisAList.get(0).getStart_Datetime()&&j+1 < staticHisAList.size()){//当动点处于静点时间之间，且保证不会越界
                            if (dongHisAList.get(i).getRecord_gps_Time()>=staticHisAList.get(j).getStart_Datetime()&&dongHisAList.get(i).getRecord_gps_Time()<staticHisAList.get(j+1).getStart_Datetime()){//动点时间在两个静点之间的情况下
                                //下边的适配表包括动态信息和静态信息
                                shipTarget_orighisVo.setRecord_UTC_Time(dongHisAList.get(i).getRecord_gps_Time());
                                shipTarget_orighisVo.setLongitude(dongHisAList.get(i).getLongitude());
                                shipTarget_orighisVo.setLatitude(dongHisAList.get(i).getLatitude());
                                shipTarget_orighisVo.setHeading(dongHisAList.get(i).getHeading());
                                shipTarget_orighisVo.setSpeed(dongHisAList.get(i).getSpeed());
                                shipTarget_orighisVo.setStatus(dongHisAList.get(i).getStatus());
                                shipTarget_orighisVo.setRot_Sensor(dongHisAList.get(i).getRot_Sensor());
                                shipTarget_orighisVo.setDirection(dongHisAList.get(i).getDirection());
                                shipTarget_orighisVo.setMmsi(mmsi);
                                //静态信息：Ship_Name,Call_Sign,IMO_Number,Destination,ETA,Draft
                                shipTarget_orighisVo.setCall_Sign(staticHisAList.get(j).getCall_Sign());
                                shipTarget_orighisVo.setDestination(staticHisAList.get(j).getDestination());
                                shipTarget_orighisVo.setShip_Name(staticHisAList.get(j).getShip_Name());
                                shipTarget_orighisVo.setEta(staticHisAList.get(j).getEta());
                                shipTarget_orighisVo.setDraft(staticHisAList.get(j).getDraft());
                                shipTarget_orighisVo.setImo_Number(staticHisAList.get(j).getImo_Number());
                                //再将合并后的适配表和另一张静表合并
                                getShipTarget_OrighisVoListLast(shipTarget_orighisVo,staticHisAIdenList);
                                ShipTarget_OrighisVoList.add(shipTarget_orighisVo);//加入集合
                                //释放内存
                                shipTarget_orighisVo = null;
                                //跳出
                                break;
                            }
                        }else if (dongHisAList.get(i).getRecord_gps_Time()>= staticHisAList.get(staticHisAList.size()-1).getStart_Datetime()){//如果点处于最后一个静点之后
                            int n = staticHisAList.size()-1;
                            //下边的适配表包括动态信息和静态信息
                            shipTarget_orighisVo.setRecord_UTC_Time(dongHisAList.get(i).getRecord_gps_Time());
                            shipTarget_orighisVo.setLongitude(dongHisAList.get(i).getLongitude());
                            shipTarget_orighisVo.setLatitude(dongHisAList.get(i).getLatitude());
                            shipTarget_orighisVo.setHeading(dongHisAList.get(i).getHeading());
                            shipTarget_orighisVo.setSpeed(dongHisAList.get(i).getSpeed());
                            shipTarget_orighisVo.setStatus(dongHisAList.get(i).getStatus());
                            shipTarget_orighisVo.setRot_Sensor(dongHisAList.get(i).getRot_Sensor());
                            shipTarget_orighisVo.setDirection(dongHisAList.get(i).getDirection());
                            shipTarget_orighisVo.setMmsi(mmsi);
                            //静态信息：Ship_Name,Call_Sign,IMO_Number,Destination,ETA,Draft
                            shipTarget_orighisVo.setCall_Sign(staticHisAList.get(n).getCall_Sign());
                            shipTarget_orighisVo.setDestination(staticHisAList.get(n).getDestination());
                            shipTarget_orighisVo.setShip_Name(staticHisAList.get(n).getShip_Name());
                            shipTarget_orighisVo.setEta(staticHisAList.get(n).getEta());
                            shipTarget_orighisVo.setDraft(staticHisAList.get(n).getDraft());
                            shipTarget_orighisVo.setImo_Number(staticHisAList.get(n).getImo_Number());
                            //再将合并后的适配表和另一张静表合并
                            getShipTarget_OrighisVoListLast(shipTarget_orighisVo,staticHisAIdenList);
                            ShipTarget_OrighisVoList.add(shipTarget_orighisVo);//加入集合
                            //释放内存
                            shipTarget_orighisVo = null;
                            //跳出
                            break;
                        }
                        else {
                            System.out.println("出bug了！！！");
                        }
                    }
                }
            }
        return ShipTarget_OrighisVoList;
    }

    /**
     * 目前思路（2）：这是思路（1）中所需要的方法：让每一条适配表再对另一张静表进行合并
     * @param vo
     * @param list
     */
    @Override
    public void getShipTarget_OrighisVoListLast( ShipTarget_OrighisVo vo ,List<L1_orig_static_history_a_iden> list) {
        for (int i = 0; i < list.size(); i++) {
            //如果动点的时间小于静表的起始时间，则让动点合成的静态信息为默认值
            if (vo.getRecord_UTC_Time() < list.get(0).getStart_Datetime()) {//区间：(-无穷,0)
                return;//保持不变
            } else if (vo.getRecord_UTC_Time() >= list.get(0).getStart_Datetime() && i + 1 < list.size()) {//当适配点时间已经超过静点的初始时间，且保证不会越界 //区间：[0,最后一个点)
                if (vo.getRecord_UTC_Time() >= list.get(i).getStart_Datetime() && vo.getRecord_UTC_Time() < list.get(i + 1).getStart_Datetime()) {
                    // 在适配表的基础上再添加上另一张静表的属性：Ship_Type,To_Bow,To_Stern,To_Port,To_StarBoard,Fixing_Device
                    vo.setTo_Bow(list.get(i).getTo_Bow());
                    vo.setFixing_Device(list.get(i).getFixing_Device());
                    vo.setTo_Port(list.get(i).getTo_Port());
                    vo.setTo_Stern(list.get(i).getTo_Stern());
                    vo.setTo_StarBoard(list.get(i).getTo_StarBoard());
                    vo.setAis_Ship_Type(list.get(i).getShip_Type());
                    return;
                } else if (vo.getRecord_UTC_Time() >= list.get(list.size() - 1).getStart_Datetime()) {//如果点处于最后一个静点之后 //区间：[最后一个点，+无穷)  三个条件全部覆盖所有可能
                    int n = list.size() - 1;
                    vo.setTo_Bow(list.get(n).getTo_Bow());
                    vo.setFixing_Device(list.get(n).getFixing_Device());
                    vo.setTo_Port(list.get(n).getTo_Port());
                    vo.setTo_StarBoard(list.get(n).getTo_StarBoard());
                    vo.setTo_Stern(list.get(i).getTo_Stern());
                    vo.setAis_Ship_Type(list.get(n).getShip_Type());
                    return;
                }
            }
            else {
                    System.out.println("除非有bug");
                }
            }
        }

    /**
     * 清洗目标表数据并返回
     * @param list
     * @return List<ShipTarget_Orighis>
     */
    @Override
    public List<ShipTarget_Orighis> byCleanShipTarget_OrighisVo(List<ShipTarget_OrighisVo> list) {
        //创建一个清洗工具类对象
        AisDataFieldClean aisDataFieldClean = new AisDataFieldCleanImpl();
        //用来保存清洗后的目标数据
        List<ShipTarget_Orighis> list1 = new ArrayList<>();
        //为目标数据字段填充清洗后的数据
        for (ShipTarget_OrighisVo shipTarget_orighisVo : list){
            //最后释放内存
            //ShipTarget_OrighisVo shipTarget_orighisVo = list.get(i);
            //存放清洗数据字段
            ShipTarget_Orighis  shipTarget_orighis = new ShipTarget_Orighis();

            shipTarget_orighis.setShip_Name(aisDataFieldClean.getCleanShip_Name(shipTarget_orighisVo.getShip_Name()));
            shipTarget_orighis.setCall_Sign(aisDataFieldClean.getCleanCall_Sign(shipTarget_orighisVo.getCall_Sign()));
            shipTarget_orighis.setDirection(aisDataFieldClean.getCleanDirection(shipTarget_orighisVo.getDirection()));
            shipTarget_orighis.setMmsi(shipTarget_orighisVo.getMmsi());//本地暂时不清洗
            shipTarget_orighis.setImo_Number(aisDataFieldClean.getCleanIMO_Number(shipTarget_orighisVo.getImo_Number(),false));
            shipTarget_orighis.setHeading(aisDataFieldClean.getCleanHeading(shipTarget_orighisVo.getHeading()));
            shipTarget_orighis.setDestination(aisDataFieldClean.getCleanDestination(shipTarget_orighisVo.getDestination()));
            shipTarget_orighis.setDraft(aisDataFieldClean.getCleanDraft(shipTarget_orighisVo.getDraft()));
            shipTarget_orighis.setEta(aisDataFieldClean.getCleanETA(shipTarget_orighisVo.getEta()));
            shipTarget_orighis.setLatitude(aisDataFieldClean.getCleanLatitude(shipTarget_orighisVo.getLatitude()));
            shipTarget_orighis.setLongitude(aisDataFieldClean.getCleanLongitude(shipTarget_orighisVo.getLongitude()));
            shipTarget_orighis.setSpeed(aisDataFieldClean.getCleanSpeed(shipTarget_orighisVo.getSpeed()));
            shipTarget_orighis.setStatus(aisDataFieldClean.getCleanStatus(shipTarget_orighisVo.getStatus()));
            shipTarget_orighis.setRecord_UTC_Time(aisDataFieldClean.getCleanRecordTime(shipTarget_orighisVo.getRecord_UTC_Time()));
            shipTarget_orighis.setTarget_ID(-1);//'目标唯一标识号，如果不是融合目标，该字段使用-1表示',
            shipTarget_orighis.setAis_Ship_Type(aisDataFieldClean.getCleanShip_Type1(shipTarget_orighisVo.getAis_Ship_Type()));
            shipTarget_orighis.setAggregated_AIS_Ship_Type_ID(aisDataFieldClean.getCleanAggregated_AIS_Ship_Type_ID(shipTarget_orighisVo.getAis_Ship_Type()));
            shipTarget_orighis.setThreat_Level(aisDataFieldClean.getCleanThreat_Level());
            shipTarget_orighis.setTargetIDOrig_Type(aisDataFieldClean.getCleanTargetIDOrig_Type());
            shipTarget_orighis.setTarget_ID_Orig(aisDataFieldClean.getCleanTarget_ID_Orig(shipTarget_orighisVo.getMmsi()));
            shipTarget_orighis.setRot_Sensor(aisDataFieldClean.getCleanROT_Sensor(shipTarget_orighisVo.getRot_Sensor()));
            shipTarget_orighis.setShip_Length(aisDataFieldClean.getCleanShip_Length(shipTarget_orighisVo.getTo_Bow(),shipTarget_orighisVo.getTo_Stern()));
            shipTarget_orighis.setShip_Breadth(aisDataFieldClean.getCleanShip_Breadth(shipTarget_orighisVo.getTo_Port(),shipTarget_orighisVo.getTo_StarBoard()));
            shipTarget_orighis.setPos_Accuracy(aisDataFieldClean.getCleanPos_Accuracy());
            shipTarget_orighis.setOrig_Info_Type(aisDataFieldClean.getCleanOrig_Info_Type());
            shipTarget_orighis.setOrig_Info_Source(aisDataFieldClean.getCleanOrig_Info_Source());
            shipTarget_orighis.setHaijian_ID(aisDataFieldClean.getCleanHaijian_ID());
            shipTarget_orighis.setFixing_Device(aisDataFieldClean.getCleanFixing_Device(shipTarget_orighisVo.getFixing_Device()));
            shipTarget_orighis.setCountry_Name(aisDataFieldClean.getCleanCountry_Number(shipTarget_orighisVo.getMmsi()));
            shipTarget_orighis.setCommun_State(aisDataFieldClean.getCleanCommun_State());
            shipTarget_orighis.setBeiDou_ID(aisDataFieldClean.getCleanBeiDou_ID());
            shipTarget_orighis.setCargo_Type(aisDataFieldClean.getCleanCargo_Type(shipTarget_orighisVo.getAis_Ship_Type()));
            shipTarget_orighis.setArgosAndMarineSat_ID(aisDataFieldClean.getCleanArgosAndMarineSat_ID());
            shipTarget_orighisVo = null;//释放内存
            list1.add(shipTarget_orighis);//将清洗后的目标实体 添加进集合中
            shipTarget_orighis = null;//释放内存
        }
        return list1;//返回目标表数据
    }

    @Override
    public int insertShipTarget_Orighis() {
        List<ShipTarget_OrighisVo> list = getShipTarget_OrighisVoList();
        List<ShipTarget_Orighis> list1 = byCleanShipTarget_OrighisVo(list);
        return aisTarget1Mapper.insertShipTarget_Orighis(list1);
    }

    @Override
    public List<ShipTarget_Orighis> getShipTarget_Orighis() {
        List<ShipTarget_OrighisVo> list = getShipTarget_OrighisVoList();
        List<ShipTarget_Orighis> list1 = byCleanShipTarget_OrighisVo(list);
        return list1;
    }

    @Override
    public List<ShipTarget_Orighis> getShipTarget_OrighisList()
    {
        return aisTarget1Mapper.getShipTarget_OrighisList();
    }

    @Override
    public int insertDbConn(DBConnection dbConnection) {
      return aisTarget1Mapper.insertDbConn(dbConnection);
    }

    @Override
    public int insertTargetDbConn(TargetDbconn dbConnection) {
        return aisTarget1Mapper.insertTargetDbConn(dbConnection);
    }

    @Override
    public int updateTargetConn(TargetDbconn dbConnection) {
        return aisTarget1Mapper.updateTargetConn(dbConnection);
    }

    @Override
    public List<DBConnection> getDBConnection() {
        return aisTarget1Mapper.getDBConnection();
    }

    @Override
    public List<TargetDbconn> getTargetDbconn() {
        return aisTarget1Mapper.getTargetDbconn();
    }

    @Override
    public List<String> getDBConnIp() {
        return aisTarget1Mapper.getDBConnIp();
    }

    @Override
    public int insertDbConnStatus(DBConnection dbConnection) {
        return aisTarget1Mapper.insertDbConnStatus(dbConnection);
    }

    @Override
    public List<TargetDbconn> getTargetDBConnIp() {
        return aisTarget1Mapper.getTargetDBConnIp();
    }

    @Override
    public int deleteDbconn(String ip) throws Exception {
        return aisTarget1Mapper.deleteDbconn(ip);
    }

    @Override
    public int deleteDbconn1(String ip) throws Exception {
        return aisTarget1Mapper.deleteDbconn1(ip);
    }
}
