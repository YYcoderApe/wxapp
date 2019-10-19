package com.zczp.service_yycoder.impl;

import com.zczp.dao.TbCollectMapper;
import com.zczp.dao.TbPostMapper;
import com.zczp.dao.TbUserMapper;
import com.zczp.service_yycoder.UserService;
import com.zczp.util.MathUtils;
import com.zczp.util.RedisKeyUtil;
import com.zczp.util.RedisUtil;
import com.zczp.vo_yycoder.CollectPostDetailVo;
import com.zczp.vo_yycoder.PostDetailVo;
import com.zczp.vo_yycoder.UserDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YYcoder
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private TbUserMapper tbUserMapper;

    @Autowired(required = false)
    private TbCollectMapper tbCollectMapper;

    @Autowired(required = false)
    private TbPostMapper tbPostMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //    @Autowired
//    private RedisTemplate<String, Serializable> redisCacheTemplate;
//
    @Autowired(required = false)
    private CommonServiceImpl commonService;

    @Autowired
    RedisUtil redisUtil;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDetailVo getUserByOpenId(String openId) {
        UserDetailVo userDetailVo = tbUserMapper.getUserByOpenId(openId);
        if (userDetailVo != null)
            return userDetailVo;
        return null;
    }

    @Override
    public int addRobotUserIfo(UserDetailVo userDetailVo) {
        String s = MathUtils.makeUpNewData(Thread.currentThread().hashCode() + "", 3) + MathUtils.randomDigitNumber(7);
        userDetailVo.setOpenId(s);
        userDetailVo.setState(1);
        return tbUserMapper.addRobotUserIfo(userDetailVo);
    }

    @Override
    public int updateUserIfoById(UserDetailVo userDetailVo) {
        return tbUserMapper.updateUserInfoById(userDetailVo);
    }

//    @Override
//    public List<CollectPostDetailVo> getUserCollection(String openId) {
//        Set<String> keys = redisUtil.keys("collect:" + openId + ":*");
//        List<CollectPostDetailVo> collectPostDetailVoList= new ArrayList<>();
//        if(keys == null || keys.size()==0){
//            collectPostDetailVoList= tbCollectMapper.getPostDetailById(openId);
//            Integer index = 0;
//            String s="collect:" + openId + ":";
//            for(CollectPostDetailVo c:collectPostDetailVoList){
//                String key=s+c.getPostId();
//                redisCacheTemplate.opsForValue().set(key, (Serializable) c);
//            }
//            log.info("从数据库中查找。。。");
//        }else{
//            for(String key:keys){
//                collectPostDetailVoList.add((CollectPostDetailVo) redisCacheTemplate.opsForValue().get(key));
//            }
//            log.info("从缓存中查找。。。");
//        }
//        return collectPostDetailVoList;
//
//    }

    @Override
    public List<CollectPostDetailVo> getUserCollection(String openId) {
        commonService.transCollectToDB();
        commonService.transCountToDB();
        List<CollectPostDetailVo> collectPostDetailVoList = tbCollectMapper.getPostDetailById(openId);
        return collectPostDetailVoList;
    }


    @Override
    public int deleteCollection(String openId, Integer postId) {
        String key = RedisKeyUtil.getKey(openId, postId);
        if (key != null) {
            redisUtil.hset(RedisKeyUtil.MAP_KEY_COLLECT, key, "0");
        } else {
            tbCollectMapper.deleteCollectionById(openId, postId);
        }
        return 1;
    }

    @Override
    @Transactional
    public List<PostDetailVo> getUserIssue(String openId) {
        commonService.transCountToDB();
        List<PostDetailVo> postDetailVoList = tbPostMapper.getPostIssueByOpenId(openId);
        return postDetailVoList;
    }

    @Override
    public int deleteUserIssue(String openId, Integer postId) {
        return tbPostMapper.deleteUserIssueById(openId, postId);
    }

    @Override
    public List<UserDetailVo> getAllUser(Integer state) {
        return tbUserMapper.getAllUser(state);
    }

    @Override
    public List<UserDetailVo> searchUserByName(String userName) {
        return tbUserMapper.seachUserByName(userName);
    }

    @Override
    public int deleteUserById(String openId) {
        return tbUserMapper.deleteByPrimaryKey(openId);
    }
}
