package com.ruoyi.project.system.aistarget.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName averageAssign
 * @Description  将源List按照指定元素数量拆分为多个List
 * @Author 李怀鹏
 * @Date 2019/5/13 10:22
 * @Version 1.0
 **/
public class averageAssign {
    /**
     *
     * @param source 源List
     * @param splitItemNum 每个List中元素数量
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source,int splitItemNum ){//定义泛型的方法
        List<List<T>> result = new ArrayList<>();
        if (source != null && source.size() >0 && splitItemNum>0){
            // 源List元素数量小于等于目标分组数量
            if (source.size()<=splitItemNum){
                result.add(source);
            }else{
                //计算拆分后的list数量
                int splitNum = ( source.size() % splitItemNum == 0 ) ? ( source.size() / splitItemNum ) : ( source.size() / splitItemNum + 1 );
                List<T> value = null;
                for (int i= 0 ; i < splitNum; i++){
                    if (i < splitNum - 1){
                        value = source.subList(i*splitItemNum,(i+1)*splitItemNum);
                    }else {//最后一组
                        value = source.subList(i*splitItemNum,source.size());
                    }
                    result.add(value);
                }
            }
        }
        return result;
    }
    //测试类
    public static void main(String[] args){
        List<Integer> source = new ArrayList<Integer>();
        for ( int i = 1; i <= 101; i++ ){
            source.add( i );
        }
        List<List<Integer>> result = averageAssign(source, 10 );

        for ( List<Integer> re : result ){
            System.out.println( JSON.toJSONString( re ) );
        }
    }
}
