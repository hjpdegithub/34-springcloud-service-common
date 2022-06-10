
package com.springboot.boot.common.constant;

/**
 * 公共常量
 *
 */
public interface CommonConstant {

	/**
	 * 默认页码为1
	 */
	Long DEFAULT_PAGE_INDEX = 1L;

	/**
	 * 默认页大小为10
	 */
	Long DEFAULT_PAGE_SIZE = 10L;


	/**
	 * 分页总行数名称
	 */
	String PAGE_TOTAL_NAME = "total";

	/**
	 * 分页数据列表名称
	 */
	String PAGE_RECORDS_NAME = "records";

	/**
	 * 分页当前页码名称
	 */
	String PAGE_INDEX_NAME = "pageNum";

	/**
	 * 分页当前页大小名称
	 */
	String PAGE_SIZE_NAME = "pageSize";

	/**
	 * 登录用户
	 */
	String LOGIN_SYS_USER = "loginSysUser";

	/**
	 * 登录token
	 */
	String JWT_DEFAULT_TOKEN_NAME = "token";

	/**
	 * JWT用户名
	 */
	String JWT_USERNAME = "username";

	/**
	 * JWT刷新新token响应状态码
	 */
	int JWT_REFRESH_TOKEN_CODE = 460;

	/**
	 * JWT刷新新token响应状态码，
	 * Redis中不存在，但jwt未过期，不生成新的token，返回361状态码
	 */
	int JWT_INVALID_TOKEN_CODE = 461;

	/**
	 * JWT Token默认密钥
	 */
	String JWT_DEFAULT = "xxx";

	/**
	 * JWT 默认过期时间，3600L，单位秒
	 */
	Long JWT_DEFAULT_EXPIRE_SECOND = 3600L;

	String GEO_POINT_JSON = "{\n" +
			"  \"type\": \"FeatureCollection\",\n" +
			"  \"features\": [\n" +
			"        {\"type\":\"Feature\",\n" +
			"        \"properties\":{},\n" +
			"        \"geometry\":{\n" +
			"            \"type\":\"Point\",\n" +
			"            \"coordinates\":[%s,%s]\n" +
			"            }\n" +
			"        }\n" +
			"    ]\n" +
			"}";

	String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	/**
	 * 铁骑缓存
	 */
	String MOTOR_INFO_REDIS_KEY = "/api/totalitySituation/cavalryNumber";

	/**
	 * 在线警力缓存
	 */
	String POLICE_NUMBER_REDIS_KEY = "/api/totalitySituation/policeNumber";

	/**
	 * 涉奥警力缓存
	 */
	String OLYMPIC_NUMBER_REDIS_KEY = "/api/totalitySituation/relatedOlympicNumber";

	/**
	 * 国家体育场交通管制区警力缓存
	 */
	String OLYMPIC_TRAFFIC_CONTROL_POLICE_REDIS_KEY = "/api/totalitySituation/policeInfoInTrafficControl";

	/**
	 * 国家体育场交通疏导区警力缓存
	 */
	String OLYMPIC_TRAFFIC_DIVERSION_POLICE_REDIS_KEY = "/api/totalitySituation/policeInfoInTrafficDiversion";

	/**
	 * 国家体育场核心区警力缓存
	 */
	String OLYMPIC_CORE_POLICE_REDIS_KEY = "/api/totalitySituation/policeInfoInCore";

	/**
	 * 国家体育场交通疏导分流区警力缓存
	 */
	String OLYMPIC_TRIAGE_POLICE_REDIS_KEY = "/api/totalitySituation/policeInfoInTriage";

	/**
	 * 带道警力缓存
	 */
	String POLICE_LEADER_REDIS_KEY = "/api/totalitySituation/policeLeaderNumber";

	/**
	 * 进京证缓存
	 */
	String IN_BJ_CARD_REDIS_KEY = "/api/totalitySituation/ringBj/inBjCard";

	/**
	 * 今日关联信息缓存Key(临时)
	 */
	String RELATED_TODAY_KEY = "/api/missionRelated";

	/**
	 * 奥运班车线路
	 */
	String SHUTTLE_BUS = "shuttle_bus";
	String TASK_TYPE = "taskType";
	String SHUTTLE_BUS_NUMBER =	"shuttleBusNumber";
	String TASK_SUB_NAME = "taskSubName";

	String ROADNET_TYPE = "road_net";

	/**
	 * 今日任务参数配置存储Key
	 */
	String MISSION_TODAY_KEY = "data:mission";

	/**
	 * 闭环车警情参数配置存储Key
	 */
	String POLICESITUATION_KEY = "data:policeSituation";

	/**
	 * 闭环车警情标题参数配置存储Key
	 */
	String POLICESITUATION_TITLE_KEY = "data:policeSituationTitle";

	/**
	 * 可去除关键字
	 */
	String REMOVED_KEYWORDS = "removed_keywords";

	/**
	 * 相似度
	 */
	String SSIM = "similarity";

	/**
	 * 目的地类别(其他)识别字
	 */
	String DES_OTHER_IDENTIFY = "des_other_identify";

	/**
	 * 目的地类别(酒店)识别字
	 */
	String DES_HOTEL_IDENTIFY = "des_hotel_identify";

	/**
	 * 市局pda实时定位接口
	 */
	String LOCATION_URL = "http://14.16.64.37:63925/location/latest/devices?deviceIds=";

	String MAP_URL = "http://14.81.12.116:8883/tile?lid=traffic&get=map&cache=off&z=%s&x=%s&y=%s";
	//String MAP_URL = "http://tm.amap.com/trafficengine/mapabc/traffictile?v=1.0&;t=1&zoom=5&x=%s&y=%s&t=1643972433215";
	String MAP_ROAD_URL = "http://14.81.12.116:8884/tile?lid=traffic&get=map&cache=off&z=%s&x=%s&y=%s";
	//String MAP_ROAD_URL = "http://114.215.146.210:25003/v3/tile?z=12&x=%s&y=%s";


	//  文件的实际地址
	String   rootPath =    "D://upload//";
	//  文件的类型地址
	String   fileTypePath = "courseFile//";

	String   fileTypePathForUrl = "courseFile/";
	//文件的公开路径

	String   fileShowPatten =    "showresource/";

	//服务器的ip
	String    fileIpAdress =    "127.0.0.1:8080/";
}
