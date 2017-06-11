package com.xinchen.redis.service;

import java.util.List;
import java.util.Set;

/**
 * Redis服务接口
 * Created by xinchen on 2017/6/10.
 */
public interface RedisService {
    /**
     * 插入key value
     * @param key 键
     * @param value 值
     */
    void set(String key, Object value);

    /**
     * 根据key获取对象
     * @param key 键
     * @return Object
     */
    Object get(String key);

    /**
     * 获取所有键值
     * @return object
     */
    Set<String> getAllKeys();

    /**
     * 入栈 保存List 从头部添加 从左侧开始
     * @param key 键
     * @param value list
     * @return 成功返回 1
     */
    Long leftList(String key, List<?> value);

    /**
     * 出栈 从头部获取 从左侧开始
     * @param key 键
     * @return list
     */
    Object leftList(String key);

    /**
     * 入队 保存List 从尾部部添加 从右侧开始
     * @param key 键
     * @param value list
     * @return 成功返回 1
     */
    Long rightList(String key, List<?> value);

    /**
     * 出队 从尾部获取 从右侧开始
     * @param key 键
     * @return list
     */
    Object rightList(String key);

    /**
     * list的剩余个数
     * @param key 键
     * @return list
     */
    Long listSize(String key);
}
