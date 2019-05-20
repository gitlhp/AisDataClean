package com.ruoyi.project.system.aistarget.service;

import com.ruoyi.project.system.aistarget.domain.*;
import com.ruoyi.project.system.user.domain.User;

import java.util.List;

/**
 * @ClassName AisTargetService
 * @Description TODO
 * @Author 李怀鹏
 * @Date 2019/4/1 10:01
 * @Version 1.0
 **/
public interface AisTargetService {
    /**
     * 查询船舶信息集合
     *
     * @param shiptarget1 目标船舶信息表载体
     * @return 融合前船舶信息集合
     */
    public List<ShipTarget1> selectShipTarget1List(ShipTarget1 shiptarget1);
    /**
     * 将所有数据融合进目标数据表
     *
     * @return 影响结果行数
     */
    public int importTarget();
















    //********以下是用来合成ShipTarget_Orighis表的方法**********


    /**
     * 查询所有动态表his_a的数据
     * @return
     */
    public List<L1_orig_dong_history_a> getL1_Orig_Dong_HisaList();

    /**
     * 查询动表中所有的ShipId的信息 然后根据此查询AB表中的MMSI
     * @return
     */
    public List<Integer> getShipIdByDongHisA();

    /**
     * 通过shipid查询MMSI（获取一条或者多条 大多数时间是一条）
     * @param shipid
     * @return
     */
    public int getMmsiByDongLastab(int shipid);

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
    public List<L1_orig_dong_history_a> getL1_Orig_Dong_HisaList(int shipid);

    /**
     * 根据动态表ab中ShipId和MMSI的关系 查询静态表中mmsi对应的静态信息 动ab中MMSI对应静态MMSI
     * @param mmsi
     * @return
     */
    public List<L1_orig_static_history_a_vo> getVoAList(int mmsi);

    /**
     * 同上 查询IDEN的静态信息
     * @param mmsi
     * @return
     */
    public List<L1_orig_static_history_a_iden> getIdenList(int mmsi);

    /**
     * 目前思路（1）：首先将动表和其中一个静态表进行结合 然后在以结合后的适配表作为和另一张静表合并的参数
     * @return
     */
    public List<ShipTarget_OrighisVo> getShipTarget_OrighisVoList();

    /**
     *  目前思路（2）：这是思路一中所需要的方法：让每一条适配表再对另一张静表进行合并
     * @param vo
     * @param list
     */
    public void getShipTarget_OrighisVoListLast(ShipTarget_OrighisVo vo ,List<L1_orig_static_history_a_iden> list);

    /**
     * 清洗数据：调整数据以及添加默认数据等 返回最终插入数据库的集合
     * @param list
     * @return
     */
    public List<ShipTarget_Orighis> byCleanShipTarget_OrighisVo(List<ShipTarget_OrighisVo> list);

    /**
     * 最后一步：合成目标表将数据按需批量写入数据库 （前端可调用该方法显示最终目标表数据）
     * @return 影响条数
     */
    public int insertShipTarget_Orighis();

    /**
     * 获取最后的目标表
     * @return
     */
    public List<ShipTarget_Orighis> getShipTarget_Orighis();

    //查询目标表所有数据进行展示
    public List<ShipTarget_Orighis> getShipTarget_OrighisList();

    /**
     * 将前台的数据源参数写入数据库
     */
    public int insertDbConn(DBConnection dbConnection);

    /**
     * 将前台的目标数据库参数写入数据库
     */
    public int insertTargetDbConn(TargetDbconn dbConnection);

    /**
     * 将前台的数据库状态参数写入数据库
     */
    public int insertDbConnStatus(DBConnection dbConnection);
    /**
     * 将前台的数据库状态参数写入目标数据库
     */
    public int updateTargetConn(TargetDbconn dbConnection);
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
     * 查询刚刚插入的目标数据库参数数据
     */
    public List<TargetDbconn> getTargetDBConnIp();
    /**
     * 删除数据源和目标库的信息
     */
    public int deleteDbconn(String ip) throws Exception;
    public int deleteDbconn1(String ip) throws Exception;
    /**
     * 导入用户数据
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importUser(List<User> userList, Boolean isUpdateSupport);
   }




