package com.ruoyi.project.system.aistarget.jdbcdao;

import com.ruoyi.project.system.aistarget.domain.*;

import java.util.List;

/**
 * @ClassName AisTargetDao
 * @Description TODO
 * @Author 李怀鹏
 * @Date 2019/4/26 10:46
 * @Version 1.0
 **/
public interface AisTargetDao {
    //********以下是用来合成ShipTarget_Orighis表的方法**********

    /**
     * 查询动表中所有的ShipId的信息 然后根据此查询AB表中的MMSI
     * @return
     */
    public List<Integer> getShipIdByDongHisA() throws Exception;

    /**
     * 通过shipid查询MMSI（获取一条或者多条 大多数时间是一条）
     * @param shipid
     * @return
     */
    public int getMmsiByDongLastab(int shipid) throws Exception;

    /**《待定：暂时不实现》
     * 从ab表查询MMSI 作为动表和静表的一个连接点
     * @return
     */
    public List<Integer> getMMSI();

    /**
     * 根据ab表中的MMSI与ShipId的关系查询一个ShipId对应的一艘船的所有动态信息
     * @param shipid
     * @return
     */
    public List<L1_orig_dong_history_a> getL1_Orig_Dong_HisaList(int shipid) throws Exception;

    /**
     * 根据动态表ab中ShipId和MMSI的关系 查询静态表中mmsi对应的静态信息 动ab中MMSI对应静态MMSI
     * @param mmsi
     * @return
     */
    public List<L1_orig_static_history_a_vo> getVoAList(int mmsi) throws Exception;

    /**
     * 同上 查询IDEN的静态信息
     * @param mmsi
     * @return
     */
    public List<L1_orig_static_history_a_iden> getIdenList(int mmsi) throws Exception;

    /**
     * 插入目标表
     * @return
     */
    public int insertShipTarget_Orighis(List<ShipTarget_Orighis> list) throws Exception;

    //查询目标表所有数据进行展示
    public List<ShipTarget_Orighis> getShipTarget_OrighisList() throws Exception;
}
