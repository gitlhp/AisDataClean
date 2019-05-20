package com.ruoyi.project.system.aistarget.service;

import com.ruoyi.RuoYiApplication;
import com.ruoyi.project.system.aistarget.domain.*;
import com.ruoyi.project.system.aistarget.mapper.AisTarget1Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class AisTargetServiceImplTest {
    @Autowired
    private AisTarget1Mapper aisTarget1Mapper;
    @Autowired
    private AisTargetService aisTargetService;
    //success
    @Test
    public void getL1_Orig_Dong_HisaList() {

        List<L1_orig_dong_history_a> list = aisTarget1Mapper.getL1_Orig_Dong_HisaList(1162);
        System.out.println(list.size());
        int i = 0;
        for (L1_orig_dong_history_a time : list){
            i++;
            if(i<200){
                System.out.println(time.getRecord_gps_Time());
            }else
                break;
        }


    }
    //success
    @Test
    public void getShipIdByDongHisA(){
        List<Integer> list = aisTarget1Mapper.getShipIdByDongHisA();
        for (int a:list) {
            System.out.println(a);
        }
    }
    //success
    @Test
    public void getMmsiByDongLastab(){
        List<Integer> list = aisTarget1Mapper.getShipIdByDongHisA();
        for (int i = 0 ; i < list.size();i++){
            System.out.println(list.get(i));
            int list1 = aisTarget1Mapper.getMmsiByDongLastab(list.get(i));
                System.out.println(list1);
        }
    }
    //success
    @Test
    public void getVoAList() {
        List<L1_orig_static_history_a_vo> list = aisTarget1Mapper.getVoAList(204709000);
        for (int i = 0; i< 100 ;i++){
            System.out.println(list.get(i).getStart_Datetime());
        }
    }
    //success
    @Test
    public void getIdenList() {
        List<L1_orig_static_history_a_iden> list = aisTarget1Mapper.getIdenList(204709000);
        for (int i = 0;i<list.size();i++){
            System.out.println(list.get(i).getStart_Datetime());
        }
    }
    //success
    @Test
    public void getShipTarget_OrighisVoList(){
        //1:6178ms 2:6832ms 3:7669ms 4:7126ms
        long startTime = System.currentTimeMillis(); //获取开始时间
        List<ShipTarget_OrighisVo> list = aisTargetService.getShipTarget_OrighisVoList();
        long endTime = System.currentTimeMillis(); //获取结束时间

        System.out.println("10000条数据程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
        for (int i = 0; i< 5;i++){
            System.out.println(list.get(i));

        }

    }
    // success
    @Test
    public void  byCleanShipTarget_OrighisVo(){
        List<ShipTarget_Orighis> list = aisTargetService.byCleanShipTarget_OrighisVo(aisTargetService.getShipTarget_OrighisVoList());
        System.out.println(list.size());
        for (int i = 0; i< 5 ;i++){
            System.out.println(list.get(i));
        }
    }
    // success
    @Test
    public void insertShipTarget_Orighis() {
        long startTime = System.currentTimeMillis(); //获取开始时间
        int n = aisTargetService.insertShipTarget_Orighis();
        long endTime = System.currentTimeMillis(); //获取结束时间

        System.out.println("插入10000条数据程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
        System.out.println(n);
    }

    @Test
    public void deletelist(){
        aisTarget1Mapper.deletelist();
        System.out.println("删除成功！");
    }
}