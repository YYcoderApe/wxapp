package com.zczp.service_yycoder.impl;

import com.zczp.dao.TbCollectMapper;
import com.zczp.dao.TbPostMapper;
import com.zczp.entity.TbCollect;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.service_yycoder.CommonService;
import com.zczp.util.RedisKeyUtil;
import com.zczp.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired(required = false)
    private TbCollectMapper tbCollectMapper;

    @Autowired(required = false)
    private TbPostMapper tbPostMapper;


    @Autowired
    RedisUtil redisUtil;

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

    @Override
    public void transCountToDB() {
        Map<String,String> postCount=redisUtil.hgetall(RedisKeyUtil.KEY_POST_COUNT,0);
        for(Map.Entry<String,String> entry:postCount.entrySet()){
            int postId=Integer.valueOf(entry.getKey());
            int count=Integer.valueOf(entry.getValue());
            TbPostWithBLOBs tbPostWithBLOBs=tbPostMapper.selectByPrimaryKey(postId);
            tbPostWithBLOBs.setCount(tbPostWithBLOBs.getCount()+count);
            tbPostMapper.updateCountByPrimaryKey(tbPostWithBLOBs);
            redisUtil.hdel(RedisKeyUtil.KEY_POST_COUNT,entry.getKey());
        }
    }
}
