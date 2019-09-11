package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbReliabilityMapper;
import com.zczp.entity.ReliabilityStatusEnum;
import com.zczp.entity.TbReliability;
import com.zczp.service_cancer.TbReliabilityService;
import com.zczp.util.RedisKeyUtil;
import com.zczp.util.RedisUtil;
import com.zczp.vo_yycoder.CollectPostDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

@Service
public class TbReliabilityServiceImpl implements TbReliabilityService {
    @Autowired
    private TbReliabilityMapper tbReliabilityMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Override
    public Integer selectByPostIdAndUserId(int postId, String openId) {
        Integer reliabilityId =tbReliabilityMapper.selectByPostIdAndUserId(postId,openId);
        if (reliabilityId!=null){
            return reliabilityId;
        }
        return 0;
    }

    //保存已点击可信状态
    public void saveReliabilityState(String openId,int postId) {
        String key=RedisKeyUtil.getKey(openId,postId);
        Long value=new Long(1);
        String postid=String.valueOf(postId);
        redisUtil.hset(RedisKeyUtil.MAP_KEY_RELIABILITY,key, ReliabilityStatusEnum.RELIABILITY.getCode().toString());
        redisUtil.hincrby(RedisKeyUtil.MAP_KEY_RELIABILITY_COUNT,postid,value);
//        Serializable serializable = redisCacheTemplate.opsForValue().get(key);
//
//        redisUtil.hset("collect:" + openId + ":"+postId,((CollectPostDetailVo)serializable).getReliability()+"",RedisKeyUtil.MAP_KEY_RELIABILITY_COUNT);
    }

    //保存取消点击可信状态
    public void delReliabilityState(String openId,int postId) {
        String key=RedisKeyUtil.getKey(openId,postId);
        Long value=new Long(-1);
        String postid=String.valueOf(postId);
        redisUtil.hset(RedisKeyUtil.MAP_KEY_RELIABILITY,key, ReliabilityStatusEnum.UNRELIABILITY.getCode().toString());
        redisUtil.hincrBy(RedisKeyUtil.MAP_KEY_RELIABILITY_COUNT,postid,value);
    }

    @Override
    //同步点击可信度数据到数据库
    public void transReliabilityToDB() {
        Map<String,String> reliabiltys= redisUtil.hgetall(RedisKeyUtil.MAP_KEY_RELIABILITY,0);
        for (Map.Entry<String, String> entry : reliabiltys.entrySet()){
            String key = entry.getKey();
            //分离出 uerId，postId
            String[] split = key.split("::");
            String openId = split[0];
            int postId = Integer.valueOf(split[1]);
            int value =Integer.valueOf(entry.getValue());

            //组装成 TbReliability 对象
            TbReliability tbReliability = new TbReliability(postId,openId,value);
            //存到 list 后从 Redis 中删除
            redisUtil.hdel(RedisKeyUtil.MAP_KEY_RELIABILITY,entry.getKey());
            //查询数据库并更新
            Integer result=tbReliabilityMapper.selectByPostIdAndUserId(postId,openId);
            if (result==null){
                tbReliabilityMapper.insert(tbReliability);
            }else {
                tbReliabilityMapper.updateByUserIdAndPostId(tbReliability);
            }
        }
    }
}
