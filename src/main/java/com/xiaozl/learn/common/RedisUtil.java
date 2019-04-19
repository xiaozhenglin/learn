package com.xiaozl.learn.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 */
public class RedisUtil {
	
	private static Logger log = LoggerFactory.getLogger(RedisUtil.class);
	
    private static RedisTemplate getRedisTemplate() {
        return (RedisTemplate)SpringUtil.getBean("redisTemplate");
    }

    public static void saveAndCheckRepeat(Section section, Serializable key, Object value, ExpiredStrategy redisExpiredStrategyEnum) throws Exception {
        if(checkKeyIsExist(section,key)){
                throw new Exception(section.getCode()+"::"+key);
        }
        save(section,key,value,redisExpiredStrategyEnum);
    }

    /**
     * 新增
     * 检查key值是否存在
     * @param section
     * @param key
     * @return
     */
    public static boolean checkKeyIsExist(Section section, Serializable key){
      return  getRedisTemplate().hasKey(section.getCode()+"::"+key);
    }

    public static void save(Section section, Serializable key, Object value) throws Exception {
        save(section, key, value, ExpiredStrategy.FIVE_MINUTES);
    }


    public static void save(Section section, Serializable key, Object value, ExpiredStrategy redisExpiredStrategyEnum) throws Exception {
    	  if (key == null) {
          	log.info("key 是空"); 
          }
          if (section == null) {
             	log.info("业务类型section 是空"); 
          }
        saveToRedis(section.getCode() + "::" + key, value);
        refreshCacheExpirationTime(section, key, redisExpiredStrategyEnum);
    }


    private static  void saveToRedis(String key, Object value) {
        getRedisTemplate().opsForValue().set(key, value);
    }


    /**
     * 刷新缓存过期时间
     *
     * @param key
     * @param redisExpiredStrategyEnum
     * @return
     */
    public static Boolean refreshCacheExpirationTime(Section section, Serializable key, ExpiredStrategy redisExpiredStrategyEnum) throws Exception {
    	  if (key == null) {
          	log.info("key 是空"); 
          }
          if (section == null) {
             	log.info("业务类型section 是空"); 
          }
        if (redisExpiredStrategyEnum.equals(ExpiredStrategy.NEVER)) {
            return  getRedisTemplate().persist(section.getCode() + "::" + key);
        }
        return  getRedisTemplate().expire(section.getCode() + "::" + key, redisExpiredStrategyEnum.getValue(), redisExpiredStrategyEnum.getTimeUnit());
    }

    /**
     * 删除key
     *
     * @param key
     */
    public static void deleteCacheByKey(Section section, Serializable key) throws Exception {
        if (key == null) {
        	log.info("key 是空"); 
        }
        if (section == null) {
           	log.info("业务类型section 是空"); 
        }
        getRedisTemplate().delete(section.getCode() + "::" + key);
    }


    /**
     * 删除key
     *
     * @param key
     */
    public static void deleteCacheByKey(Serializable key) throws Exception {
        if (key == null) {
        	log.info("key 是空"); 
        }
        getRedisTemplate().delete(key);
    }

    /**
     * 删除某类key
     * @param section
     * @throws Exception
     */
    public static  void deleteCacheBySection(Section section) throws Exception {
        if (section == null) {
             	log.info("业务类型section 是空"); 
        }
        Set keys = getRedisTemplate().keys(section.getCode() + "::" + "*");
        getRedisTemplate().delete(keys);
    }

    public static <T> T get(Section section, Serializable key) throws Exception {
    	  if (key == null) {
          	log.info("key 是空"); 
          }
          if (section == null) {
             	log.info("业务类型section 是空"); 
          }
        return (T)  getRedisTemplate().opsForValue().get(section.getCode() + "::" + key);
    }

    /**
     * 模糊查询
     * 获取所有值
     *
     * @param section
     * @param key  需要自带*号  <if>按类型查  传* </if>  不能传null
     * @param <T>
     * @return
     * @throws Exception
     */
    public static  <T> List<T> multiGet(Section section, Serializable key)throws Exception{
    	  if (key == null) {
          	log.info("key 是空"); 
          }
          if (section == null) {
             	log.info("业务类型section 是空"); 
          }
        Set keys = getRedisTemplate().keys(section.getCode() + "::" + key);
        return getRedisTemplate().opsForValue().multiGet(keys);
    }

    /**
     * 获取所有键 冒号::后面的值
     * @param section
     * @param key
     * @return
     */
    public static List<String> getSectionKeys(Section section ,Serializable key){
    	  if (key == null) {
          	log.info("key 是空"); 
          }
          if (section == null) {
             	log.info("业务类型section 是空"); 
          }
        Set<String> keys = getRedisTemplate().keys(section.getCode() + "::"+key+"*");
        List<String> result = new ArrayList<>(keys.size());
        for(String k : keys) {
            result.add(k.substring(k.indexOf("::")+2));
        }
        return result;
    }

    /**
     * 模糊查询获取所有键
     *
     * @param section
     * @return
     * @throws Exception
     */
    public static List<String> getSectionKeys(Section section)throws Exception{
       return   getSectionKeys(section,"");
    }
    
    public static List<String> getAppKeys(Section section)throws Exception{
    	String prefix = RedisConfiguration.prefix;
        Set<String> keys = getRedisTemplate().keys(prefix + "*");
        List<String> result = new ArrayList<>();
        for(String key : keys) {
        	result.add(key.substring(key.indexOf("::")+2));
        }
        return result;
    }

    /**
     * 获取值并刷新时间它的时间
     * @param section
     * @param key
     * @return
     * @throws Exception
     */
    public  static  <T> T getAndRefresh(Section section, Serializable key) throws Exception {
       return  getAndRefresh(section,key,ExpiredStrategy.FIVE_MINUTES);
    }

    
    /** 获取值并刷新时间它的时间 执行
     * @param section
     * @param key
     * @param redisExpiredStrategyEnum
     * @return
     * @throws Exception
     */
    public static  <T> T getAndRefresh(Section section, Serializable key, ExpiredStrategy redisExpiredStrategyEnum) throws Exception {
        T t = get(section,key);
        refreshCacheExpirationTime(section,key,redisExpiredStrategyEnum);
        return t;
    }
    

}
