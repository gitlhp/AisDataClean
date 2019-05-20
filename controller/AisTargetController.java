package com.ruoyi.project.system.aistarget.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.aistarget.dbconn.Dbconntool;
import com.ruoyi.project.system.aistarget.domain.DBConnection;
import com.ruoyi.project.system.aistarget.domain.ShipTarget_Orighis;
import com.ruoyi.project.system.aistarget.domain.TargetDbconn;
import com.ruoyi.project.system.aistarget.jdbcdao.AisTargetDao;
import com.ruoyi.project.system.aistarget.jdbcdao.AisTargetDaoImpl;
import com.ruoyi.project.system.aistarget.jdbcdao.AisTargetInsert;
import com.ruoyi.project.system.aistarget.jdbcdao.DbPool;
import com.ruoyi.project.system.aistarget.jdbcservice.AisTargetJdbcService;
import com.ruoyi.project.system.aistarget.jdbcservice.AisTargetJdbcServiceImpl;
import com.ruoyi.project.system.aistarget.service.AisTargetService;
import com.ruoyi.project.system.aistarget.util.averageAssign;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName AisTargetController
 * @Description TODO
 * @Author 李怀鹏
 * @Date 2019/4/1 9:57
 * @Version 1.0
 **/
@Controller
@RequestMapping("/system/aistarget")
public class AisTargetController extends BaseController {
    //定义一个url前缀
    private String prefix = "system/aistarget";
    @Autowired
    private AisTargetService aisTargetService;
    //存放数据对象
    public static final DbPool dbPool = new DbPool();
    //数据库连接对象key
    public static String ipkey = null;
    public static CountDownLatch countDownLatch;//线程计数器
    @RequiresPermissions("system:aistarget:view")
    @GetMapping()
    public String operlog() {
        return prefix + "/aisdbTargetconn";
    }

    //@RequiresPermissions("system:aistarget:list")
    //@PostMapping("/list")
    //@ResponseBody
    //public TableDataInfo list() {
    //    //System.out.println(ship.getShip_ID());
    //    startPage();
    //    List<ShipTarget_Orighis> list = aisTargetService.getShipTarget_OrighisList();
    //    //此处可以批量插入目标表
    //    return getDataTable(list);
    //}
    //@Log(title = "用户管理", businessType = BusinessType.IMPORT)
    //@RequiresPermissions("system:user:import")
    //@PostMapping("/importData")
    //@ResponseBody
    //public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    //{
    //    ExcelUtil<User> util = new ExcelUtil<User>(User.class);
    //    List<User> userList = util.importExcel(file.getInputStream());
    //    //String message = userService.importUser(userList, updateSupport);
    //    //return AjaxResult.success(message);
    //}

    //@RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.importTemplateExcel("用户数据");
    }
    /**
     * 融合数据进目标数据表 返回执行完毕信息
     */
    @RequiresPermissions("system:aistarget:import")
    @PostMapping("/importtarget")
    @ResponseBody
    public AjaxResult importTarget1()
    {
        return toAjax(aisTargetService.insertShipTarget_Orighis());
    }

    /**
     * 新增抽取条件 跳转到添加页面
     */
    @GetMapping("/addetl")
    public String addetl()
    {
        return prefix + "/addEtl";
    }
    /**
     * 新增目标数据库连接 跳转到添加页面
     */
    @GetMapping("/addtargetconn")
    public String addtargetconn()
    {
        return prefix + "/addTargetConn";
    }
    /**
     * 新增数据源连接 跳转到添加页面
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/addConn";
    }


    /**
     * 新增保存数据库连接
     */
    @RequiresPermissions("system:aistarget:add")
    @Log(title = "数据库", businessType = BusinessType.INSERT)
    @PostMapping("/addConn")
    @ResponseBody
    public AjaxResult addSave(DBConnection dbConnection){
        ipkey = dbConnection.getIp();
        Connection connection = null;
        try {
            connection = Dbconntool.getConnection(dbConnection);
            if (connection!=null){
                dbPool.put(ipkey, connection);//将数据库连接对象保存 用ip来区分数据库连接对象
                return toAjax(aisTargetService.insertDbConn(dbConnection));
            }
        } catch (SQLException e) {
            return toAjax1(-1);//表示连接错误
        }
            return toAjax1(-1);//表示连接错误
    }

    /**
     * 新增保存数据库连接
     */
    @RequiresPermissions("system:aistarget:add1")
    @Log(title = "目标数据库", businessType = BusinessType.INSERT)
    @PostMapping("/addTargetConn")
    @ResponseBody
    public AjaxResult addSaveTarget(TargetDbconn dbConnection)  {
        ipkey = dbConnection.getIp();
        Connection connection = null;
        try {
            //测试是否连接成功
            connection = Dbconntool.getConnection(dbConnection);
            if (connection!=null){
                dbPool.put(ipkey, connection);//将数据库连接对象保存 用ip来区分数据库连接对象
                return toAjax(aisTargetService.insertTargetDbConn(dbConnection));//写进去的都是成功的链接
            }
        } catch (SQLException e) {
            return toAjax1(-1);//表示连接错误
        }
        return toAjax1(-1);//表示连接错误
    }

    /**
     * 融合数据进目标数据表 返回执行完毕信息
     */
    @RequiresPermissions("system:aistarget:import")
    @PostMapping("/importtarget1")
    @ResponseBody
    public AjaxResult importTarget()
    {
        //获取添加的所有数据库连接
        List<String> connlist = aisTargetService.getDBConnIp();
        List<TargetDbconn> targetconnlist = aisTargetService.getTargetDBConnIp();//获取目标数据库的ip和sourceip
        if (connlist.size()==0){
            return error("请连接数据源");
        }else if (targetconnlist.size()==0){
            return error("请连接目标库");
        }
        else if (dbPool.isEmpty()){//如果没有新的连接直接融合报错
            return toAjax1(-1);//表示连接错误
        }else {
            if (connlist.size()!=0&&targetconnlist.size()!=0){//必须数据源和目标库同时有数据才行
                for (String conn : connlist){//遍历数据源ip
                    Connection connection = null;
                    List<ShipTarget_Orighis> list = null;//多目标库从第二次开始直接插入 避免多次查询清洗操作
                    List<List<ShipTarget_Orighis>>  list1 = null;//同上
                    int n = 0;//记录多目标库的数量 用来判断是否二次清洗
                    String time="0";//运行时间
                    String sumdata = "0";//影响条数
                    for (TargetDbconn targetDbconn:targetconnlist){//遍历目标数据库对象
                        if(conn.equals(targetDbconn.getSourceip())){//如果目标库与数据源相匹配
                            n++;
                            //测试代码
                            if (list1==null){
                                System.out.println("--------"+n);
                            }else {
                                System.out.println("++++++"+n+list1.size());
                            }
                            long startTime2 = System.currentTimeMillis();//两次循环需要归零
                            connection = dbPool.get(conn);//获取数据源连接
                            Connection connection1 = dbPool.get(targetDbconn.getIp());//获取目标库的链接
                            if (n>1){//两个及其以上连接从第二次起
                                try {
                                    System.out.println("只计算插入时间咯");
                                    countDownLatch = new CountDownLatch(list1.size());
                                    System.out.println("第2次开始的线程计数器"+countDownLatch);
                                    long startTime4 = System.currentTimeMillis();
                                    for (int i = 0;i<list1.size();i++) {
                                        new AisTargetInsert(connection1, list1.get(i), countDownLatch).start();
                                    }
                                    countDownLatch.await();//主线程等待
                                    long endTime4 = System.currentTimeMillis();
                                    long runtime = endTime4 - startTime4;
                                    System.out.println("第一次之后的运行时间为"+runtime);
                                    time = String.valueOf(runtime)+"ms";//当作时间字段加入数据库
                                    sumdata = String.valueOf(list.size());
                                    TargetDbconn dbConnection = new TargetDbconn();
                                    dbConnection.setIp(targetDbconn.getIp());
                                    dbConnection.setStatus("融合成功");
                                    dbConnection.setTime(time);
                                    dbConnection.setSumdata(sumdata);
                                    aisTargetService.updateTargetConn(dbConnection);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    TargetDbconn dbConnection = new TargetDbconn();
                                    dbConnection.setIp(targetDbconn.getIp());
                                    dbConnection.setStatus("融合失败");
                                    dbConnection.setTime(time);
                                    dbConnection.setSumdata(sumdata);
                                    aisTargetService.updateTargetConn(dbConnection);
                                    return error(e.getMessage());
                                }finally {
                                    Dbconntool.close(null,null,connection1);
                                    continue;//不能往下执行
                                }
                            }
                            AisTargetDao aisTargetDao = new AisTargetDaoImpl(connection);//数据源操作对象如果是有两个数据源则要使用同一个数据源链接
                            //创建service对象
                            AisTargetJdbcService aisTargetJdbcService = new AisTargetJdbcServiceImpl(aisTargetDao);
                            try {
                                long startTime1 = System.currentTimeMillis();
                                list = aisTargetJdbcService.getShipTarget_Orighis();//获取目标表的数据集合
                                list1 = averageAssign.averageAssign(list,100000);
                                long endTime1 = System.currentTimeMillis();    //获取结束时间
                                System.out.println("查询清洗数据运行时间：" + (endTime1 - startTime1) + "ms");
                                System.out.println("数据量为："+list.size());
                                System.out.println("最后的数据量为："+list1.get(list1.size()-1).size());
                                countDownLatch = new CountDownLatch(list1.size());
                                long startTime = System.currentTimeMillis();
                                for (int i = 0;i<list1.size();i++) {
                                    new AisTargetInsert(connection1, list1.get(i), countDownLatch).start();
                                }
                                countDownLatch.await();//主线程等待
                                System.out.println("第一次的线程计数器"+countDownLatch);
                                long endTime = System.currentTimeMillis();    //获取结束时间
                                System.out.println("融合数据运行时间：" + (endTime - startTime) + "ms");
                                long endTime2 = System.currentTimeMillis();
                                long runtime = endTime2 - startTime2;
                                time = String.valueOf(runtime)+"ms";//当作时间字段加入数据库
                                sumdata = String.valueOf(list.size());
                                TargetDbconn dbConnection = new TargetDbconn();
                                dbConnection.setIp(targetDbconn.getIp());
                                dbConnection.setStatus("融合成功");
                                dbConnection.setTime(time);
                                dbConnection.setSumdata(sumdata);
                                aisTargetService.updateTargetConn(dbConnection);
                            }catch (Exception e){
                                e.printStackTrace();
                                TargetDbconn dbConnection = new TargetDbconn();
                                dbConnection.setIp(targetDbconn.getIp());
                                dbConnection.setStatus("融合失败");
                                dbConnection.setTime(time);
                                dbConnection.setSumdata(sumdata);
                                aisTargetService.updateTargetConn(dbConnection);
                                return error(e.getMessage());
                            }finally{
                                //Dbconntool.close(null,null,connection);//手动关闭数据源连接
                                //手动关闭目标数据源连接但是在多线程下，线程的执行是不阻塞主线程的，这点其实也是多线程的优势，提高代码执行效率，不必相互等待可以并行执行
                                Dbconntool.close(null,null,connection1);//只关闭目标库连接
                            }
                        }
                    }
                    Dbconntool.close(null,null,connection);//手动关闭数据源连接 数据源连接使用完毕再关闭
                    }
                 dbPool.clear();//融合完毕清空连接  避免二次伤害哦
                 return toAjax1(10);
                    }
                    return toAjax1(-1);//表示连接错误:数据库信息添加不完整
            }
        }

    //@RequiresPermissions("system:aistarget:list")
    //@PostMapping("/list")
    //@ResponseBody
    //public TableDataInfo list() {
    //    //System.out.println(ship.getShip_ID());
    //    //判断是否连接成功
    //
    //    List<ShipTarget_Orighis> list1 = new ArrayList<>();
    //    Connection connection = dbPool.get(ipkey);
    //    if (connection!=null){
    //
    //        //dao层获取连接
    //        AisTargetDao aisTargetDao = new AisTargetDaoImpl(connection);
    //        //创建service对象
    //        AisTargetJdbcService aisTargetJdbcService = new AisTargetJdbcServiceImpl(aisTargetDao);
    //        //startPage();
    //        List<ShipTarget_Orighis> list = null;
    //        try {
    //            startPage();
    //            list = aisTargetJdbcService.getShipTarget_OrighisList();
    //        }catch (Exception e){
    //            return getDataTable(list1);
    //        }
    //        return getDataTable(list);
    //
    //        //此处可以批量插入目标表
    //    }
    //    return getDataTable(list1);
    //}

    @RequiresPermissions("system:aistarget:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        //System.out.println(ship.getShip_ID());
        startPage();
        //List<DBConnection> list = aisTargetService.getDBConnection();
        List<TargetDbconn> list = aisTargetService.getTargetDbconn();
        //此处可以批量插入目标表
        return getDataTable(list);
    }

    @RequiresPermissions("system:aistarget:remove")
    @Log(title = "数据库操作删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            int n = aisTargetService.deleteDbconn(ids);
            int m = aisTargetService.deleteDbconn1(ids);
            int sum = m+n;
            if (sum>0){
                return toAjax(2);
            }else
                throw new Exception();
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
//    /**
//     * 融合数据进目标数据表 返回执行完毕信息 显示数据源的信息
//     */
//    @RequiresPermissions("system:aistarget:import")
//    @PostMapping("/importtarget1")
//    @ResponseBody
//    public AjaxResult importTarget()
//    {
//        long startTime2 = System.currentTimeMillis();
//        //获取添加的所有数据库连接
//        List<String> connlist = aisTargetService.getDBConnIp();
//        List<TargetDbconn> targetconnlist = aisTargetService.getTargetDBConnIp();//获取目标数据库的ip和sourceip
//        if (dbPool.isEmpty()){//如果没有新的连接直接融合报错
//            return toAjax1(-1);//表示连接错误
//        }else {
//            if (connlist.size()!=0&&targetconnlist.size()!=0){//必须数据源和目标库同时有数据才行
//                for (String conn : connlist){//遍历数据源ip
//                    for (TargetDbconn targetDbconn:targetconnlist){//遍历目标数据库对象
//                        if(conn.equals(targetDbconn.getSourceip())){//如果目标库与数据源相匹配
//                            Connection connection = dbPool.get(conn);//获取数据源连接
//                            Connection connection1 = dbPool.get(targetDbconn.getIp());//获取目标库的链接
//                            AisTargetDao aisTargetDao = new AisTargetDaoImpl(connection);//数据源操作对象
//                            //创建service对象
//                            AisTargetJdbcService aisTargetJdbcService = new AisTargetJdbcServiceImpl(aisTargetDao);
//                            String time=null;
//                            String sumdata = null;
//                            try {
//                                long startTime1 = System.currentTimeMillis();
//                                List<ShipTarget_Orighis> list = aisTargetJdbcService.getShipTarget_Orighis();//获取目标表的数据集合
//                                List<List<ShipTarget_Orighis>>  list1 = averageAssign.averageAssign(list,10000);
//                                long endTime1 = System.currentTimeMillis();    //获取结束时间
//                                System.out.println("查询清洗数据运行时间：" + (endTime1 - startTime1) + "ms");
//                                System.out.println("数据量为："+list.size());
//                                System.out.println("最后的数据量为："+list1.get(list1.size()-1).size());
//                                countDownLatch = new CountDownLatch(list1.size());
//                                long startTime = System.currentTimeMillis();
//                                for (int i = 0;i<list1.size();i++) {
//                                    new AisTargetInsert(connection1, list1.get(i), countDownLatch).start();
//                                }
//                                countDownLatch.await();
//                                long endTime = System.currentTimeMillis();    //获取结束时间
//                                System.out.println("融合数据运行时间：" + (endTime - startTime) + "ms");
//                                long endTime2 = System.currentTimeMillis();
//                                long runtime = endTime2 - startTime2;
//                                time = String.valueOf(runtime)+"ms";//当作时间字段加入数据库
//                                sumdata = String.valueOf(list.size());
//                                DBConnection dbConnection = new DBConnection();
//                                dbConnection.setIp(conn);
//                                dbConnection.setStatus("融合成功");
//                                dbConnection.setTime(time);
//                                dbConnection.setSumdata(sumdata);
//                                aisTargetService.insertDbConnStatus(dbConnection);//输出程序运行时间
//                            }catch (Exception e){
//                                e.printStackTrace();
//                                DBConnection dbConnection = new DBConnection();//添加状态
//                                dbConnection.setIp(conn);
//                                dbConnection.setStatus("融合失败");
//                                dbConnection.setTime(time);
//                                dbConnection.setSumdata(sumdata);
//                                aisTargetService.insertDbConnStatus(dbConnection);
//                                return error(e.getMessage());
//                            }finally {
//                                Dbconntool.close(null,null,connection);//手动关闭数据源连接
//                                //手动关闭目标数据源连接但是在多线程下，线程的执行是不阻塞主线程的，这点其实也是多线程的优势，提高代码执行效率，不必相互等待可以并行执行
//                                Dbconntool.close(null,null,connection1);
//
//                            }
//
//                        }
//                    }
//                }
//                dbPool.clear();//融合完毕清空连接  避免二次伤害哦
//                return toAjax1(10);
//            }
//            return toAjax1(-1);//表示连接错误:数据库信息添加不完整
//        }
//    }