package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbReliabilityMapper;
import com.zczp.entity.ReliabilityStatusEnum;
import com.zczp.entity.TbReliability;
import com.zczp.service_cancer.TbReliabilityService;
import com.zczp.util.RedisKeyUtil;
import com.zczp.util.RedisUtil;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TbReliabilityServiceImpl implements TbReliabilityService {
    @Autowired
    private TbReliabilityMapper tbReliabilityMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public int deleteByPrimaryKey(Integer reliabilityId) {
        return 0;
    }

    @Override
    public int insert(TbReliability record) {
        return 0;
    }

    @Override
    public int insertSelective(TbReliability record) {
        return 0;
    }

    @Override
    public TbReliability selectByPrimaryKey(Integer reliabilityId) {
//        if(reliabilityId==1){
//            redisUtil.incr("测试");
//            System.out.println(redisUtil.get("测试",0));
//        }else if(reliabilityId==0){
//            redisUtil.decr("测试");
//            System.out.println(redisUtil.get("测试",0));
//        }else {
//            redisUtil.set("测试","161",0);
//        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TbReliability record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TbReliability record) {
        return 0;
    }

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
        List<TbReliability> list = new ArrayList<>();
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
