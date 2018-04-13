package com.article.recommend.constant;
/**
 * 
* @ClassName: RecommendConstant  
* @Description: 常量类  
* @author sw  
* @date 2018年4月10日
 */
public final class RecommendConstant {
    public  static final  String HDFSBASEPATH="hdfs://hadoop2.campus-card.com:8020/opt/sunwei";
    public static final  String BASEPATH="/opt/sunwei/";//根目录
    /**用户文件夹路径**/
    public static final  String USERINFO_PATH="userinfo";

    /***文章存放路径**/
    public static final String ARTICLE_PATH="articleinfo";
    /***用户行为数据****/
    public  static  final String USERPREFS_PATH="userprefs";
    /***用户有效数据****/
    public  static  final String USERPREFS_DATA_PATH="data";
    /***用户无效数据****/
    public  static  final String USERPREFS_LOSEDATA_PATH="loseData";


    /***用户操作历史***/
    public static final String USER_HAND_HISTORY_PATH="userhandhistory";
    /** hdfs 执行数据导入后生成的默认文件**/
    public static  final  String HDFS_SUCCESS_FILE="part-00000";
    /***文章数据类型***/
    public static  final String  DB_ARTICLE_TYPE="0";
    /***用户数据类型***/
    public static  final String DB_USER_INFO_TYPE="1";
    /****用户操作历史数据类型******/
    public static  final String DB_USER_HISTORY_TYPE="2";
    /**追加数据**/
    public static  final String DATA_IMPORT_TYPE_INTO="into";
    /**重写数据**/
    public static  final String DATA_IMPORT_TYPE_OVERWRITE="overwrite";
    /**文章失效天数**/
    public static  final String ARTICLE_LOSE_TIME="article_lose_days";
    /*********定时任务***********************/
    /**文章定时任务**/
    public static final String QUARTZ_TYPE_ARTICLE="quartz_article";

    /*********定时任务***********************/

    /*********系统数据统一状态***********************/
    /**有效**/
    public static final String SYSTEM_DATE_EFFECTIVE="0";
    /**无效**/
    public static final String SYSTEM_DATE_INVALID="1";

    /*********系统数据统一状态***********************/


    /**********推荐算法参数配置********************************/
    /***用户相邻个数**/
    public static  final String NEAREST_NUM="NEAREST_NUM";
    /**用户相邻百分比**/
    public static  final String THRESHOLD_NUM="THRESHOLD_NUM";
    /**抽取多少**/
    public static  final String TOP_NUM="TOP_NUM";
    /**参与计算数据中多少数据参与计算评分***/
    public static  final  String TRAINPT_NUM="trainPt";
    /**总数据中多少数据参与计算**/
    public static final String TOTAL_NUM="total_num";
    /**那些距离参与**/
    public static  final  String USER_SIM_STATE="user_sim_state";
    /***取值个数**/
    public static  final  String RECOMMENDER_NUM="RecommendValueTask";

    /**********推荐算法参数配置********************************/
    /**默认线程数**/
    public static  final int RECOMMEDN_THREAD_DEFAULT_LIMIT=100;
    /**线程池个数字典配置**/
    public static  final String RECOMMEDN_THREAD_LIMIT="THREAD_COUNT";
    /**计算评分任务线程数***/
    public static final String RECOMMEND_VALUE_NUM="RecommendValueTask";
    /**单个任务数***/
    public static final int TASK_DEFAULT_NUM=20;
    /*********推荐相关start********************/
    /**协同过滤基于用户***/
    public static final String RECOMMEND_TYPE_BASEUSER="BASEUSER";
    /***协同过滤基于物品*************/
    public static final String RECOMMEND_TYPE_BASEITERM="BASEITERM";
    /*****基于业务规则************/
    public static final String RECOMMEND_TYPE_USERRULES="BASERULES";
    /*********推荐相关end**************************/
    /*************************redis 存储key*************************/
    /**用户**/
    public static final String REDIS_USER_RECOMMEND_KEY="user:recommend";
    /**文章***/
    public static final String REDIS_ITEM_RECOMMEND_KEY="item:recommend";
    /**规则**/
    public static final String REDIS_RULE_RECOMMEND_KEY="rule:recommend";


    
    


}
