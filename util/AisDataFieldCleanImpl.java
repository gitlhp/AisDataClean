package com.ruoyi.project.system.aistarget.util;

import com.ruoyi.common.utils.aiscountry.AisMid2Country;

import java.text.SimpleDateFormat;

/**
 * @ClassName AisDataFieldCleanImpl
 * @Description 实现AIS字段清洗的接口 从数据库中查询到的数据字段要进行相应的判断赋值才能做下一步操作
 * @Author 李怀鹏
 * @Date 2019/4/15 10:09
 * @Version 1.0
 **/
public class AisDataFieldCleanImpl implements AisDataFieldClean {
    /**
     * /判断船舶的类型
     * @param intClassType
     * @return
     */
    @Override
    public int getClassTypeFromInt(int intClassType) {
        switch (intClassType){
            case 0:
                intClassType = AisDataFieldClean.Enum_Class_A;
                break;//return 直接退出函数
            case 1:
                intClassType = AisDataFieldClean.Enum_Class_B;
                break;
            default:
                intClassType = AisDataFieldClean.Enum_Class_UnKnown;
                break;
        }
        return intClassType;
    }

    /**
     * 在AISL0消息中，使用30bit来表示,所以最多表现为十位'  这个方法暂时用不到
     * @param rawMMSI
     * @return
     */
    @Override
    public int getCleanMMSI(String rawMMSI) {
        String strMMSI = rawMMSI.substring(0,10);
        int intMMSI = Integer.valueOf(strMMSI);
        return intMMSI;
    }

    /**
     * 附默认值0或指定非法值  动态表字段 记录对应的GPS时间，有误差.Ship_ID和GPS_Time是联合主键，需要保证唯一性'
     * @param drGPSTime
     * @return
     */
    @Override
    public int getCleanRecordTime(int drGPSTime) {
        if (drGPSTime < 0){//非法数据
            drGPSTime = 0;//非法数据全部赋值为0
        }
        return drGPSTime;
    }

    /**
     * 经度 除以600000得度数。除完以后，范围是-180~180，181：不可用 182：非法\n（±180°，东=正，西=负。\n181°=不可用=默认值）',
     * @param rawLongitude
     * @return
     */
    @Override
    public int getCleanLongitude(int rawLongitude) {
        if (-180*600000 <= rawLongitude&&180*600000 >= rawLongitude ){
            return rawLongitude;
        }else if (rawLongitude == 181*600000){
            return AisDataFieldClean.DEFAULT_LONGITUDE;//默认值表示不可用 可以录入数据库
        }
        return 182*600000;//非法值 统一赋值182：非法
    }

    /**
     * 除以600000得度数。范围是-90~90，91:不可用 92：是非法\n（±90°，北=正，南=负。91°=不可用=默认值）',
     * @param rawLatitude
     * @return
     */
    @Override
    public int getCleanLatitude(int rawLatitude) {
        if(-90*600000<=rawLatitude&&90*600000>=rawLatitude)
            return rawLatitude;
        else if(rawLatitude==91*600000)//默认值，表示不可用
            return AisDataFieldClean.DEFAULT_LATITUDE;
        else //非法值
            return 92*600000;
    }

    /**
     * 船航向清洗：'船航向\n范围是0-3600，超出为非法\nCOG，以1/10°为单位（0-3599）。3600 =不可用=默认值。3601：非法',
     * @param rawDirection
     * @return
     */
    @Override
    public int getCleanDirection(int rawDirection) {
        if (0<= rawDirection&&rawDirection <=AisDataFieldClean.DEFAULT_DIRECTION){//合法数据以及默认值3600
            return rawDirection;
        }
        else
            return 3601;//非法数据  主要是不合法则3601
    }

    /**
     * /'船首向。范围是0-359以及511，512：非法\n度（0-359）（511表明不可用=默认值）'。
     * @param rawHeading
     * @return
     */
    @Override
    public int getCleanHeading(int rawHeading) {
        if((0<=rawHeading&&rawHeading<=359)||AisDataFieldClean.DEFAULT_HEADING==rawHeading)
            return rawHeading;
        else
            return 512; //非法数据
    }

    /**
     * /'除以10得节。范围0-1023，1024：非法\n1 023=不可用，1 022=102.2节或更快',
     * @param rawSpeed
     * @return
     */
    @Override
    public int getCleanSpeed(int rawSpeed) {
        if(0<=rawSpeed&&AisDataFieldClean.DEFAULT_SPEED>=rawSpeed)
            return rawSpeed;
        else
            return 1024;//非法数据
    }

    /**
     * '范围0-15. 16：非法。\n船状态。0=机动船在航（under way using engine），1=锚泊，2=船舶失控',
     * @param rawStatus
     * @return
     */
    @Override
    public int getCleanStatus(int rawStatus) {
        if(0<=rawStatus&&AisDataFieldClean.DEFAULT_STATUS>=rawStatus)//我们只需要写入数据库 其他转化需要前后端的事情
            return rawStatus;
        else
            return 16; //非法数据
    }

    /**
     * /'传感器输出的转向率。\n正常范围-708到708表示右转708°/min到左转708°/min之间\n值为正负709时，分别';
     * @param rawROT
     * @return
     */
    @Override
    public int getCleanROT_Sensor(int rawROT) {
        if(rawROT>=0&&rawROT<=126)
            return (int) Math.floor((rawROT/4.733)*(rawROT/4.733));//如果向上取整，最大会是709，Math.floor向下取整
        else if(rawROT<0&&rawROT>=-126)
            return -1*(int) Math.floor((rawROT/4.733)*(rawROT/4.733));
        else if(rawROT==127)
            return 709;
        else if(rawROT==-127)
            return -709;
        else if(rawROT==-128)//没有数据，默认值
            return AisDataFieldClean.DEFAULT_ROT_SENSOR;
        else //非法数据
            return -711;
    }

    /**
     * 数据源中，对AIS_Source有两种编码方案。\n方案一:\n210:集美\n131:ais_hub\n110:zhou_shan\n306:ship_finder\n112:yan_tai\n301:卫',DEFAULT -1
     * @param rawAIS_Source
     * @return
     */
    @Override
    public int getCleanAIS_Source(int rawAIS_Source) {
        if(rawAIS_Source<1||rawAIS_Source>30)
        {
            switch (rawAIS_Source) {
                case 210:
                    rawAIS_Source=1;
                    break;
                case 131:
                    rawAIS_Source=2;
                    break;
                case 110:
                    rawAIS_Source=3;
                    break;
                case 306:
                    rawAIS_Source=4;
                    break;
                case 112:
                    rawAIS_Source=5;
                    break;
                case 301:
                    rawAIS_Source=6;
                    break;
                case 310:
                    rawAIS_Source=10;
                    break;
                case 401:
                    rawAIS_Source=8;
                    break;
                case 307:
                    rawAIS_Source=9;
                    break;
                default:
                    rawAIS_Source=AisDataFieldClean.DEFAULT_AIS_SOURCE;
            }
        }
        return rawAIS_Source;
    }

    /**
     * @@@@@@@' COMMENT '呼号\n7*6比特ASCII字符，@@@@@@@=不可用=默认值',
     * @param rawCall_Sign
     * @return
     */
    @Override
    public String getCleanCall_Sign(String rawCall_Sign) {
        //如果为空则赋予默认值，否则取前七位
        if(rawCall_Sign==null||rawCall_Sign.isEmpty())
            return AisDataFieldClean.DEFAULT_CALL_SIGN;
        else
            if (rawCall_Sign.length()>7){
                return rawCall_Sign.substring(0,7);
            }else {
            return rawCall_Sign;
            }

    }

    /**
     * '@@@@@@@@@@@@@@@@@@@@' COMMENT '最长20字符的6比特ASCII码，如表44的规定“@@@@@@@@@@@@@@@@@@@@”=不可用=默认值。对于SAR航空器，应设置为“SAR航空器NNNNNNN”，其中NNNNNNN等于航空器登记号码',
     * @param rawShip_Name
     * @return
     */
    @Override
    public String getCleanShip_Name(String rawShip_Name) {
        if(rawShip_Name ==null||rawShip_Name.isEmpty())
            return AisDataFieldClean.DEFAULT_SHIP_NAME;
        else
            if (rawShip_Name.length()>20){
                return rawShip_Name.substring(0,20);
            }else {
            return rawShip_Name;
            }

    }

    /**
     * 'IMO编号。范围0-999999999，1000000000为无效\n1-999999999；0=不可用=默认值 – 不适用于SAR航空器  ',
     * @param rawIMO_Number
     * @param isValid
     * @return
     */
    @Override
    public int getCleanIMO_Number(int rawIMO_Number, boolean isValid) {
        isValid=false;
        if(1000000000>=rawIMO_Number) //AIS协议中固定的IMO有效范围
        {
            if(1000000<=rawIMO_Number&&9999999>=rawIMO_Number) //IMO号应当为7位数字
            {
                int n7,n6,n5,n4,n3,n2; //最高位到倒数第二位
                n7=rawIMO_Number/1000000;
                int leftIMO=rawIMO_Number-n7*1000000; //去掉最高位后的imo
                n6=leftIMO/100000;
                leftIMO=leftIMO-n6*100000;
                n5=leftIMO/10000;
                leftIMO=leftIMO-n5*10000;
                n4=leftIMO/1000;
                leftIMO=leftIMO-n4*1000;
                n3=leftIMO/100;
                leftIMO=leftIMO-n3*100;
                n2=leftIMO/10;
                int checkNum=n7*7+n6*6+n5*5+n4*4+n3*3+n2*2;
                if((rawIMO_Number-checkNum)%10==0)
                    isValid=true; //checkNum的个位数和imo个位数应当一样
            }
            return rawIMO_Number;
        }//若为空 默认值
        else if((Integer)rawIMO_Number == null){
            return AisDataFieldClean.DEFAULT_IMO_NUMBER;
        }
            return 1000000000;//非法值
    }

    /**
     * '船舶和货物类型\n0=不可用或没有船舶=默认值\n1-99=如第3.3.2节的规定\n100-199=保留，用于区域性使用\n200-255=保留，用于将来使用\n不适用于SAR航空器',
     * @param rawShip_Type
     * @return
     */
    @Override
    public int getCleanShip_Type1(int rawShip_Type) {
        if (0<=rawShip_Type&&rawShip_Type<100){
            return rawShip_Type;
        }else{
            //非法值赋默认值
            return AisDataFieldClean.DEFAULT_SHIP_TYPE;
        }
    }
    //在上一个方法保证数据正确的前提下 抛弃此方法直接一步到位
    //@Override
    //public String getCleanShip_Type(int rawShip_Type) {
    //    int n = rawShip_Type/10;//获取船舶类型的第一位数
    //    String shipType;
    //   if (n != 0){//0代表不可用 默认值
    //       switch (n){
    //           case 1:
    //               shipType = AisShipType.a;
    //               break;
    //           case 2:
    //               shipType = AisShipType.b;
    //       }
    //   }
    //
    //    return null;
    //}
    //首先根据rawShip_Type查询接口转换数值
    @Override
    public int getCleanAggregated_AIS_Ship_Type_ID(int rawShip_Type) {
        int n = rawShip_Type/10;//获取船舶类型的第一位数
        if (0<=rawShip_Type&&rawShip_Type<100){
            switch (rawShip_Type){
                case 30:
                    rawShip_Type = 5;
                    break;
                case 31:
                    rawShip_Type = 6;
                    break;
                case 32:
                    rawShip_Type = 6;
                    break;
                case 33:
                    rawShip_Type = 16;
                    break;
                case 34:
                    rawShip_Type = 17;
                    break;
                case 35:
                    rawShip_Type = 8;
                    break;
                case 36:
                    rawShip_Type = 14;
                    break;
                case 37:
                    rawShip_Type = 15;
                    break;
                case 38:
                    rawShip_Type = 100;
                    break;
                case 39:
                    rawShip_Type = 100;
                    break;
                case 50:
                    rawShip_Type = 12;
                    break;
                case 51:
                    rawShip_Type = 2;
                    break;
                case 52:
                    rawShip_Type = 4;
                    break;
                case 53:
                    rawShip_Type = 13;
                    break;
                case 54:
                    rawShip_Type = 100;
                    break;
                case 55:
                    rawShip_Type = 9;
                    break;
                case 56:
                    rawShip_Type = 100;
                    break;
                case 57:
                    rawShip_Type = 100;
                    break;
                case 58:
                    rawShip_Type = 100;
                    break;
                case 59:
                    rawShip_Type = 100;
                    break;
                default:
                    switch (n){
                        case 1:
                            rawShip_Type = 100;
                            break;
                        case 2:
                            rawShip_Type = 10;
                            break;
                        case 4:
                            rawShip_Type = 11;
                            break;
                        case 6:
                            rawShip_Type = 7;
                            break;
                        case 7:
                            rawShip_Type = 1;
                            break;
                        case 8:
                            rawShip_Type = 3;
                            break;
                        case 9:
                            rawShip_Type = 100;
                            break;
                        default:
                            rawShip_Type = 0;//缺省值
                    }
            }
        }
        return rawShip_Type;//返回Aggregated_AIS_Ship_Type
    }

    ////DEFAULT 0
    //@Override
    //public int getCleanShip_Length(int rawShip_Length) {
    //    if(rawShip_Length<65535)
    //        return rawShip_Length;
    //    else
    //        return AisDataFieldClean.DEFAULT_SHIP_LENGTH;
    //}
    ////DEFAULT 0
    //@Override
    //public int getCleanShip_Breadth(int rawShip_Breadth) {
    //    if(rawShip_Breadth<65535)
    //        return rawShip_Breadth;
    //    else
    //        return AisDataFieldClean.DEFAULT_SHIP_BREADTH;
    //}

    /**
     * /船长 = 船头距离 + 船尾距离
     * @param to_Bow
     * @param To_Stern
     * @return
     */
    @Override
    public int getCleanShip_Length(int to_Bow, int To_Stern) {
        if (to_Bow>0&&To_Stern>0){
            return to_Bow+To_Stern;
        }
        return 0;
    }

    /**
     * 船宽 = 左舷距离 + 右舷距离
     * @param to_Port
     * @param to_StarBoard
     * @return
     */
    @Override
    public int getCleanShip_Breadth(int to_Port, int to_StarBoard) {
        if (to_Port>0&&to_StarBoard>0){
            return to_Port+to_StarBoard;
        }
        return 0;
    }

    /**
     * 根据MMSI的前三位查询中文国家名
     * @param rawMMSI
     * @return
     */
    @Override
    public String getCleanCountry_Number(int rawMMSI) {
       return AisMid2Country.getCountryName(rawMMSI);
    }

    /**
     * '@@@@@@@@@@@@@@@@@@@@' COMMENT '目的港口\n最长20字符，采用6比特ASCII码；\n@@@@@@@@@@@@@@@@@@@@=不可用\n对于SAR航空器，此字段的使用可能会由负责的管理部门决定。',
     * @param rawDestination
     * @return
     */
    @Override
    public String getCleanDestination(String rawDestination) {
        if (rawDestination==null||rawDestination.isEmpty()){
            return AisDataFieldClean.DEFAULT_DESTIONATION;
        } else
            if (rawDestination.length()>20){
                return rawDestination.substring(0,20);
            }else {
            return rawDestination;
            }

    }

    /**
     *  '预计到达时间，如12-05 23:30 UTC\nMM-DD HH:MM UTC\n不可用、默认值：00-00 24:60 UTC\n非法值：00-00 00-00 UTC\n',
     //再议！！！
     * @param rawETA
     * @return
     */
    @Override
    public boolean checkTime(String rawETA) {
        boolean falg = false;
        try {
            new SimpleDateFormat("MM-DD HH:MM").parse(rawETA);//判断字符串是否符合相应的日期格式
            falg = true;
        }catch (Exception e){
            falg = false;
        }
        return falg;
    }

    /**
     * 再议！！！！
     * @param rawETA
     * @return
     */
    @Override
    public String getCleanETA(String rawETA) {
        if (rawETA==null||rawETA.isEmpty()){
            return AisDataFieldClean.DEFAULT_ETA;
        }
        return rawETA;
    }

    /**
     *  DEFAULT 0'目前最大静态吃水，范围0-255\n以1/10 m为单位，255=吃水25.5 m或更大，0=不可用=默认值,\n按照IMO的A.851号决议\n不适用于SAR航空器，应置为0',
     * @param rawDraft
     * @return
     */
    @Override
    public int getCleanDraft(int rawDraft) {
        if(rawDraft>255||rawDraft<0){
        return AisDataFieldClean.DEFAULT_DRAFT;//默认值＋不可用
        }
        return rawDraft;
    }

    /**
     * DEFAULT 0'电子定位装置的类型\n-1:非法\n0=未规定（默认值）\n1=GPS\n2=GLONASS\n3=GPS/GLONASS组合\n4=Loran-C\n5=Chayka\n6=综合导航系统\n7=正在研究\n8=Galileo\n9-14=未使用\n15=内部 GNSS',
     * @param rawFixing_Device
     * @return
     */
    @Override
    public int getCleanFixing_Device(int rawFixing_Device) {
        if (0>rawFixing_Device||rawFixing_Device>15){
            return -1;//非法值
        }else if (0<=rawFixing_Device&&rawFixing_Device<=15){
            return rawFixing_Device;
        }
        return 0;
    }

    /**
     * ==MMSI 在原始消息中的目标编号，结合TargetIDOrig_Type判断
     * @param mmsi
     * @return Target_ID_Orig
     */
    @Override
    public int getCleanTarget_ID_Orig(int mmsi) {
        return mmsi;
    }

    /**
     * AIS动态=4
     * @return Orig_Info_Type = 4
     */
    @Override
    public int getCleanOrig_Info_Type() {
        return 4;
    }

    /**
     * 星载 = 4
     * @return Orig_Info_Source = 4
     */
    @Override
    public int getCleanOrig_Info_Source() {
        return 4;
    }

    /**
     * EV_TargetIDType_MMSI =1 默认
     * @return TargetIDOrig_Type = 1
     */
    @Override
    public int getCleanTargetIDOrig_Type() {
        return 1;
    }

    /**
     *货物类型，对应AIS标准中船舶类型编号的第二位数字75->5
     * @param rawShip_Type
     * @return
     */
    @Override
    public int getCleanCargo_Type(int rawShip_Type) {
        if (0<=rawShip_Type&&rawShip_Type<100){
            //计算船舶类型的第一位  int自动取整
            int n1 = rawShip_Type/10;
            //计算船舶类型的第二位
            int n2 = rawShip_Type-n1*10;
            return n2;
        }
        return -1;
    }

    /**
     *
     * @return EV_AISPosAccuracy_NA = 0;
     */
    @Override
    public int getCleanPos_Accuracy() {
        return 0;
    }

    /**
     *
     * @return EV_AISCommumState_NA = 0;
     */
    @Override
    public int getCleanCommun_State() {
        return 0;
    }

    /**
     *
     * @return EV_TargetThreatLevel_NA = 0;缺省值
     */
    @Override
    public int getCleanThreat_Level() {
        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public int getCleanBeiDou_ID() {
        return 0;
    }

    /**
     *
     * @return 0
     */
    @Override
    public int getCleanHaijian_ID() {
        return 0;
    }

    /**
     *
     * @return 0
     */
    @Override
    public int getCleanArgosAndMarineSat_ID() {
        return 0;
    }
}
