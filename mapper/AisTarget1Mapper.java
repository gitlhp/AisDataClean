package com.ruoyi.project.system.aistarget.mapper;

import com.ruoyi.project.system.aistarget.domain.*;

import java.util.List;

/**
 * @ClassName AisTarget1Mapper
 * @Description 对目标数据表进行数据操作
 * @Author 李怀鹏
 * @Date 2019/4/1 10:09
 * @Version 1.0
 **/
public interface AisTarget1Mapper {

    public List<ShipTarget1> selectShipTarget1List(ShipTarget1 shiptarget1);
    /**
     * 将所有数据融合进目标数据表
     *
     * @return 影响结果行数
     */
    public int importTarget();









    /**
     * 查询所有动态表1的数据
     * @return
     */
    public List<L1_orig_dong_history_a> getL1_Orig_Dong_HisaList();

    /**
     * 方案二(暂时不实现)：直接从最终位置动态表中查询MMSI 因为实际上MMSI和ShipId还是一一对应的
     * @return
     */
    public List<Integer> getMMSI();

    /**
     * 查询动表中所有的ShipId的信息 然后根据此查询AB表中的MMSI
     * @return
     */
    public List<Integer> getShipIdByDongHisA();

    /**
     * 通过shipid查询MMSI（多对一）
     * @param shipid
     * @return
     */
    public int getMmsiByDongLastab(int shipid);

    /**
     * 根据ab表中的MMSI与ShipId的关系查询一个ShipId对应的一艘船的所有动态信息
     * @param shipid
     * @return
     */
    public List<L1_orig_dong_history_a> getL1_Orig_Dong_HisaList(int shipid);

    /**
     * 根据动态表ab中ShipId和MMSI的关系 查询静态表中对应的静态信息 动ab中MMSI对应静态MMSI
     * @param mmsi
     * @return
     */
    public List<L1_orig_static_history_a_vo> getVoAList(int mmsi);

    /**
     * 同上
     * @param mmsi
     * @return
     */
    public List<L1_orig_static_history_a_iden> getIdenList(int mmsi);

    /**
     * 插入目标表
     * @return
     */
    public int insertShipTarget_Orighis(List<ShipTarget_Orighis> list);

    /**
     * 查询目标表数据
     */
    public List<ShipTarget_Orighis> getShipTarget_OrighisList();
    /**
     * 测试用：删除目标表全部数据
     */
    public void deletelist();
    /**
     * 将前台的数据库参数写入数据库
     */
    public int insertDbConn(DBConnection dbConnection);
    /**
     * 将前台的目标数据库参数写入数据库  update
     */
    public int insertTargetDbConn(TargetDbconn dbConnection);
    /**
     * 将前台的数据库状态参数写入目标数据库
     */
    public int updateTargetConn(TargetDbconn dbConnection);
    /**
     * 查询刚刚插入的目标数据库参数数据
     */
    public List<TargetDbconn> getTargetDBConnIp();
    /**
     * 将前台的数据库状态参数写入数据库
     */
    public int insertDbConnStatus(DBConnection dbConnection);
    /**
     * 查询刚刚插入的数据库参数数据
     */
    public List<DBConnection> getDBConnection();
    /**
     * 查询目标库参数
     */
    public List<TargetDbconn> getTargetDbconn();
    /**
     * 查询刚刚插入的数据库参数数据
     */
    public List<String> getDBConnIp();
    /**
     * 删除数据融合操作信息
     */
    public int deleteDbconn(String ip);
    public int deleteDbconn1(String ip);
}
