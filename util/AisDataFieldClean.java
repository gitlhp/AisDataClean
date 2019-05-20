package com.ruoyi.project.system.aistarget.util;

/**
 * @ClassName aisDataFieldClean
 * @Description 对数据字段进行清洗 主要是对非法字段进行默认值或者非法值得赋值
 * @Author 李怀鹏
 * @Date 2019/4/12 17:11
 * @Version 1.0
 **/
//可以进行直接赋值 也可以使用接口中的常量 更加规范
public interface AisDataFieldClean {
    int Enum_Class_A = 0;
    int Enum_Class_B = 1;
    int Enum_Class_UnKnown = 2;
    int DEFAULT_LONGITUDE = 108600000;
    int DEFAULT_LATITUDE = 54600000;
    int DEFAULT_DIRECTION = 3600;
    int DEFAULT_HEADING = 511;
    int DEFAULT_SPEED = 1023;
    int DEFAULT_STATUS = 15;
    int DEFAULT_ROT_SENSOR = -710;
    int DEFAULT_AIS_SOURCE = -1;
    String DEFAULT_SHIP_NAME = "@@@@@@@@@@@@@@@@@@@@";
    String DEFAULT_DESTIONATION = "@@@@@@@@@@@@@@@@@@@@";
    String DEFAULT_CALL_SIGN = "@@@@@@@";
    String DEFAULT_ETA = "00-00 24:60 UTC";
    int DEFAULT_IMO_NUMBER = 0;
    int DEFAULT_SHIP_LENGTH = -1;
    int DEFAULT_SHIP_BREADTH = -1;
    int DEFAULT_SHIP_TYPE = 0;
    int DEFAULT_DRAFT = 0;
    int DEFAULT_FIXING_DEVICE = 0;//默认设备类型

    //接口方法清洗字段
    int getClassTypeFromInt(int intClassType);
    int getCleanMMSI(String rawMMSI);
    int getCleanRecordTime(int drGPSTime);
    int getCleanLongitude(int rawLongitude);
    int getCleanLatitude(int rawLatitude);
    int getCleanDirection(int rawDirection);
    int getCleanHeading(int rawHeading);
    int getCleanSpeed(int rawSpeed);
    int getCleanStatus(int rawStatus);
    int getCleanROT_Sensor(int rawROT);
    int getCleanAIS_Source(int rawAIS_Source);
    String getCleanCall_Sign(String rawCall_Sign);
    String getCleanShip_Name(String rawShip_Name);
    //int getCleanIMO_Number(String rawIMO_Number,boolean isValid);
    int getCleanIMO_Number(int rawIMO_Number,boolean isValid);
    int getCleanShip_Type1(int rawShip_Type);
    //String getCleanShip_Type(int rawShip_Type);
    //目标字段中的字段转换
    int getCleanAggregated_AIS_Ship_Type_ID(int rawShip_Type);
    //再议
    //int getCleanTo_Bow(int rawTo_Bow);
    //int getCleanTo_Stern(int rawTo_Stern);
    //int getCleanTo_Bow(int rawTo_Bow);
    //int getCleanTo_Bow(int rawTo_Bow);
    int getCleanShip_Length(int to_Bow,int to_Stern);
    int getCleanShip_Breadth(int to_Port,int to_StarBoard);
    String getCleanCountry_Number(int rawMMSI);
    String getCleanDestination(String rawDestination);
    String getCleanETA(String rawETA);
    boolean checkTime(String rawETA);
    int getCleanDraft(int rawDraft);
    int getCleanFixing_Device(int rawFixing_Device);
    //原始表没有字段进行默认赋值
    //这边需要对接proto接口标准，没有字段使用默认值
    int getCleanTarget_ID_Orig(int mmsi);//=MMSI
    int getCleanOrig_Info_Type();//AIS动态=4
    int getCleanOrig_Info_Source();//AIS动态=4 星载
    int getCleanTargetIDOrig_Type();//=1
    //货物类型，对应AIS标准中船舶类型编号的第二位数字
    int getCleanCargo_Type(int rawShip_Type);
    int getCleanPos_Accuracy();
    int getCleanCommun_State();
    int getCleanThreat_Level();
    int getCleanBeiDou_ID();
    int getCleanHaijian_ID();
    int getCleanArgosAndMarineSat_ID();
}
