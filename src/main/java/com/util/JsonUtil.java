package com.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	static Map map = new HashMap();

	public static void main(String[] args) {
		map.put("channel.className.newName", "name");
		map.put("channel.className.objectName.objName", "name");
		map.put("channel.className.car.newType", "type");
		map.put("channel.className.car.bus.newName", "name");

		map.put("channel.className.set.status", "{'0':'正常','1':'癫疯'}");
		map.put("channel.className.car.set.type", "{'bmw':'宝马','byd':'比亚迪'}");
		map.put("channel.className.car.bus.set.name", "{'busname':'公共汽车'}");

		// String strJson =
		// "{'RenewalItemInfo':{'CheSun':272236.0,'SanZhe':1000000.0,'DaoQiang':0.0,'SiJi':0.0,'ChengKe':0.0,'BoLi':0.0,'HuaHen':0.0,'BuJiMianCheSun':1.0,'BuJiMianSanZhe':1.0,'BuJiMianDaoQiang':0.0,'BuJiMianRenYuan':0.0,'BuJiMianFuJian':0.0,'TeYue':0.0,'SheShui':0.0,'CheDeng':0.0,'ZiRan':0.0,'SanFangTeYue':0.0,'JingShenSunShi':0.0,'BuJiMianJingShenSunShi':0.0,'BuJiMianSiji':0.0,'BuJiMianChengKe':0.0,'BuJiMianHuaHen':0.0,'BuJiMianZiRan':0.0,'BuJiMianSheShui':0.0,'XiuLiChang':0.0,'XiuLiChangType':0.0,'BizTotal':1272238.0,'ForceTotal':0.0,'TaxTotal':0.0,'Source':1,'LastEndDate':'2017-01-13T23:59:59+08:00','LastForceEndTime':'2017-01-13T23:59:59+08:00','NextBizStartTime':'2017-01-18T00:00:00','NextForceStartTime':'2017-01-18T00:00:00','LicenseOwner':null,'LicenseOwnerIdType':null,'LicenseOwnerIdCard':null,'InsuredName':null,'InsuredIdCard':null,'RegisterDate':'0001-01-01T00:00:00','CarVin':null,'EngineNo':null,'MoldName':null,'AutoModelCode':null,'BrandName':null,'CarUsedType':null,'CarUsedTypeValue':null,'LicenseType':null,'LicenseTypeValue':null,'CarType':null,'CarTypeValue':null,'SeatCount':null,'ExhaustScale':null,'CarLotEquQuality':null,'CarTonCount':null,'PurchasePrice':0.0,'RelationPerson':{'OwnerInfo':{'Customcode':null,'Name':'孙铭志','IdType':'身份证','IdTypeValue':'1','IdNo':'371002198103034013','Mobile':'13764115339'},'PolicyHoderInfo':{'Customcode':null,'Name':'孙铭志','IdType':'身份证','IdTypeValue':'1','IdNo':'371002198103034013','Mobile':''},'InsuredInfo':{'Customcode':'','Name':'孙铭志','IdType':'身份证','IdTypeValue':'1','IdNo':'371002198103034013','Mobile':'13764115339'},'CaimantInfo':{'Customcode':null,'Name':null,'IdType':null,'IdTypeValue':null,'IdNo':null,'Mobile':null}},'CarInfo':{'RegisterDate':'2012-05-11T00:00:00+08:00','CarVin':'LSVUH65NXC2012597','EngineNo':'737174','MoldName':'大众汽车SVW6451HED多用途乘用车','AutoModelCode':'DZABFD0017','BrandName':'大众汽车SVW6451HED多用途乘用车','CarUsedType':'家庭自用车','CarUsedTypeValue':'101','SubCarUsedTypeValue':null,'LicenseNo':null,'LicenseType':'小型汽车号牌','LicenseTypeValue':'02','CarType':'6座以下客车','CarTypeValue':'01','SubCarType':'9座及9座以下非营运客车(含越野)','SubCarTypeValue':'01','SeatCount':'5','ExhaustScale':'1.798','CarLotEquQuality':'1710.0','CarTonCount':'5','PurchasePrice':272236.0,'analogyCarPrice':0.0,'ClauseType':'','ClauseTypeValue':null,'LicenseColor':'蓝','LicenseColorValue':'1','CarProofType':'','CarProofTypeValue':null,'FuelType':null,'FuleTypeValue':'','RunRegionCode':'境内','RunRegionCodeValue':'2','LicenseOwner':null,'TransferDate':'0001-01-01T00:00:00'},'RateFactor':{'NonClaimRate':-1.0,'MultiDiscountRate':-1.0,'AvgMileRate':-1.0,'RiskRate':-1.0,'CustomerLoyalty':0.0,'DriveSafely':0.0},'ForceNO':'ASHH381CTP16X009024R','BizNO':'ASHH381DX916X008029E','CompanyCode':null},'RenewalItemQuote':null,'LastYearInfo':null,'ErrCode':0,'ErrMsg':null,'Version':'1.0.0.41'}";
		String strJson = "{'newName':'tom','status':1,'age':123,objectName:{'objName':'jack'},'car':{'price':123,'newType':'bmw','bus':{'newName':'busname'}}}";
		JSONObject jsonObject = JSONObject.parseObject(strJson);

		// json数据转换
		convertJson(jsonObject, "channel", "className", "objectName,car,car.bus");
		System.out.println("==convert==" + jsonObject);

		// 属性设置值
		 resetJsonValue(jsonObject, "channel", "className","objectName,car,car.bus");
		 System.out.println("==resetvalue==" + jsonObject);

		Person p = JSONObject.toJavaObject(jsonObject, Person.class);
		System.out.println(p);

	}
	/**
	 * 
	 * @param jsonObject
	 *            json数据对象
	 * @param channel
	 *            渠道号
	 * @param className
	 *            实体对象名称，和配置文件key对应
	 * @param objectNames
	 *            json中子对象名称，多个用逗号隔开
	 */
	public static void convertJson(JSONObject jsonObject, String channel,
			String className, String objectNames) {
		try {
			// 处理外层json
			addAndConvertJson(jsonObject, channel, className, null);
			if (null == objectNames || "".equals(objectNames))
				return;

			String[] objNames = objectNames.split(",");
			for (int i = 0; i < objNames.length; i++) {
				//获取当前对象
				JSONObject currentJsonObject = getCurrentJsonObject(jsonObject,
						channel, className, objNames[i]);
				addAndConvertJson(currentJsonObject, channel, className, objNames[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取当前对象
	 * 
	 * @param jsonObject 原始json对象
	 * @param channel 渠道号
	 * @param className 对应实体类名称（和配置文件key对应）
	 * @param objectName 对象名称，嵌套对象中间用点.隔开
	 * @return
	 */
	private static JSONObject getCurrentJsonObject(JSONObject jsonObject,
			String channel, String className, String objectName) {
		if(null == objectName || "".equals(objectName))
			return jsonObject;
		String[] objArys = objectName.split("\\.");
		if (objArys.length == 1) {
			return jsonObject.getJSONObject(objectName);
		}
		JSONObject tempJsonObj = jsonObject;
		for (int i = 0; i < objArys.length; i++) {
			tempJsonObj = tempJsonObj.getJSONObject(objArys[i]);
		}
		return tempJsonObj;
	}

	/**
	 * 新增并转换json数据
	 * 
	 * @param currentJsonObject 当前json对象
	 * @param channel 渠道号
	 * @param className 对应实体类名称（和配置文件key对应）
	 * @param objectName 对象名称，嵌套对象中间用点.隔开
	 */
	private static void addAndConvertJson(JSONObject currentJsonObject, String channel,
			String className, String objectName) {
		Map<Object,Object>	tempMap = new HashMap<Object,Object>();
		String propertyKey = "";
		if(null == objectName || "".equals(objectName)){
			propertyKey = channel + "." + className + ".";
		}else{
			propertyKey = channel + "." + className + "." + objectName + ".";
		}
		try {
			Set<String> keySet = currentJsonObject.keySet();
			Iterator iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				if(map.get(propertyKey + key) == null || "".equals(map.get(propertyKey + key)))
					continue;
				tempMap.put(propertyKey + key, currentJsonObject.getString(key));
			}
			System.out.println("====tempMap===="+tempMap);
			addKeyValueFromMap(currentJsonObject, channel, className, tempMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 读取map集合数据添加到json对象
	 * 
	 * @param currentJsonObject 当前json对象
	 * @param channel 渠道号
	 * @param className 对应实体类名称（和配置文件key对应）
	 * @param tempMap 临时集合对象
	 */
	private static void addKeyValueFromMap(JSONObject currentJsonObject,
			String channel, String className,
			Map<Object, Object> tempMap) {
		Iterator iter = tempMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			if(null == map.get(key) || "".equals(map.get(key)))
				continue;
			currentJsonObject.put((String) map.get(key), entry.getValue());
		}
	}
	/**
	 * 设置json属性值
	 * @param jsonObject json数据对象
	 * @param channel 渠道号
	 * @param className 对应实体类名称（和配置文件key对应）
	 * @param objectNames json中子对象名称，多个用逗号隔开
	 */
	public static void resetJsonValue(JSONObject jsonObject, String channel,String className,String objectNames){
		try {
			//处理外层json
			setJsonVlaue(jsonObject, channel, className, null);

			String[] objNames = objectNames.split(",");
			for (int i = 0; i < objNames.length; i++) {
				//获取当前对象
				JSONObject currentJsonObject = getCurrentJsonObject(jsonObject,
						channel, className, objNames[i]);
				setJsonVlaue(currentJsonObject, channel, className, objNames[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 设置json属性值
	 * 
	 * @param currentJsonObject 当前json对象
	 * @param channel 渠道号
	 * @param className 对应实体类名称（和配置文件key对应）
	 * @param objectName 对象名称，嵌套对象中间用点.隔开
	 */
	public static void setJsonVlaue(JSONObject currentJsonObject, String channel,String className, String objectName) {
		Map<Object,Object>	tempMap = new HashMap<Object,Object>();
		String propertyKey = "";
		if(null == objectName || "".equals(objectName)){
			propertyKey = channel + "." + className + ".set.";
		}else{
			propertyKey = channel + "." + className + "." + objectName + ".set.";
		}
		try {
			Set<String> keySet = currentJsonObject.keySet();
			Iterator iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				if(map.get(propertyKey + key) == null || "".equals(map.get(propertyKey + key)))
					continue;
				tempMap.put(propertyKey + key, map.get(propertyKey + key));
			}
			System.out.println("====setJsonVlauetempMap===="+tempMap);
			setValueFromMap(currentJsonObject, channel, className, tempMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 读取map集合数据设置json数据
	 * 
	 * @param currentJsonObject 当前json对象
	 * @param channel 渠道号
	 * @param className 对应实体类名称（和配置文件key对应）
	 * @param tempMap 临时集合对象
	 */
	private static void setValueFromMap(JSONObject currentJsonObject,
			String channel, String className,
			Map<Object, Object> tempMap) {
		Iterator iter = tempMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			if(null == map.get(key) || "".equals(map.get(key)))
				continue;
			String realKey = key.split("\\.")[key.split("\\.").length-1];
			
			currentJsonObject.put(realKey, getJsonValue((String) entry.getValue(), currentJsonObject.getString(realKey)));
		}
	}
	/**
	 * 获取json数据
	 * @param strJson
	 * @param key
	 * @return
	 */
	private static String getJsonValue(String strJson,String key){
		JSONObject valueJson = JSONObject.parseObject(strJson);
		return valueJson.getString(key);
	}
}
