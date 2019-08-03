package com.zczp.service_cancer.Impl;

import com.sun.javafx.collections.MappingChange;
import com.zczp.dao.TbCollectMapper;
import com.zczp.entity.TbCollect;
import com.zczp.service_cancer.TbCollectService;
import com.zczp.util.RedisKeyUtil;
import com.zczp.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TbCollectServiceImpl implements TbCollectService {
    @Autowired
    private TbCollectMapper tbCollectMapper;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public int deleteByPrimaryKey(Integer collectId) {
        return tbCollectMapper.deleteByPrimaryKey(collectId);
    }

    @Override
    public int insert(TbCollect record) {
        return tbCollectMapper.insert(record);
    }

    @Override
    public int insertSelective(TbCollect record) {
        return tbCollectMapper.insertSelective(record);
    }

    @Override
    public Integer selectByPostIdAndUserId(Integer postId, String openId) {
        Integer result= tbCollectMapper.selectByPostIdAndUserId(postId,openId);
        if (result!=null){
            return result;
        }else {
            return 0;
        }
    }

    @Override
    public void saveCollectState(int postId, String openId) {
        String key = RedisKeyUtil.getKey(openId,postId);
        redisUtil.hset(RedisKeyUtil.MAP_KEY_COLLECT,key,"1");
    }

    @Override
    public void delCollectState(int postId, String openId) {
        String key = RedisKeyUtil.getKey(openId,postId);
        redisUtil.hset(RedisKeyUtil.MAP_KEY_COLLECT,key,"0");
    }

    @Override
    public void transCollectToDB() {
        Map<String,String> collect= redisUtil.hgetall(RedisKeyUtil.MAP_KEY_COLLECT,0);
        for(Map.Entry<String,String> entry:collect.entrySet()){
            String key=entry.getKey();
            String[] split = key.split("::");
            String openId = split[0];
            int postId = Integer.valueOf(split[1]);
            int value =Integer.valueOf(entry.getValue());
            //创建tbColllect对象
            TbCollect tbCollect =new TbCollect(postId,openId,value);
            //删除缓存数据
            redisUtil.hdel(RedisKeyUtil.MAP_KEY_COLLECT,key);
            //插入数据库
            Integer result = tbCollectMapper.selectByPostIdAndUserId(postId,openId);
            if (result==null){
                tbCollectMapper.insert(tbCollect);
            }else {
                tbCollectMapper.updateByPostIdAndOpenId(tbCollect);
            }
        }
    }


}
