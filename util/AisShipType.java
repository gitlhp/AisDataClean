package com.ruoyi.project.system.aistarget.util;

/**
 * @ClassName AisShipType
 * @Description 船舶报告类型所用标识符 有据可查
 * 除3和5以外 直接看第一位判断
 * @Author 李怀鹏
 * @Date 2019/4/15 16:07
 * @Version 1.0
 **/
public interface AisShipType {
    String a = "留着将来使用";//1 - 100
    String b = "风效应船";//2 -10
    String c = "船舶";//3
    String d = "高速船";//4 - 11
    String e = "客轮";//6 - 7
    String f = "货轮";//7 - 1
    String g = "油轮";//8 - 3
    String h = "其他类型船";//9 - 100
    String c0 = "捕捞";//30 - 5
    String c1 = "拖船";//31 - 6
    String c2 = "超大拖船";//32 - 6
    String c3 = "从事疏浚，挖掘或水下作业";//33 - 16
    String c4 = "从事潜水作业";//34 - 17
    String c5 = "从事军事行动";//35 - 8
    String c6 = "帆船";//36 - 14
    String c7 = "游艇";//37 - 15
    String c8 = "留着将来使用";//38 - 100
    String c9 = "留着将来使用";//39 - 100
    String w0 = "引航船舶";//50 - 12
    String w1 = "搜救船舶";//51 - 2
    String w2 = "拖轮";//52 - 4
    String w3 = "港口补给船";//53 - 13
    String w4 = "安装有防污染设施或设备的船舶";//54 - 100
    String w5 = "执法船舶";//55 - 9
    String w6 = "备用-当地船舶支配使用";//56 - 100
    String w7 = "备用-当地船舶支配使用";//57 - 100
    String w8 = "医疗运送船舶";//58 - 100
    String w9 = "非武装冲突参与国的船舶和航空器";//59 - 100

}
