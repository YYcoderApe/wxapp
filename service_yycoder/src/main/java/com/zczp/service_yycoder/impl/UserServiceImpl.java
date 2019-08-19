package com.zczp.service_yycoder.impl;

import com.zczp.dao.*;
import com.zczp.service_yycoder.UserService;
import com.zczp.util.MathUtils;
import com.zczp.vo_yycoder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private TbUserMapper tbUserMapper;

    @Autowired(required = false)
    private TbCollectMapper tbCollectMapper;

    @Autowired(required = false)
    private TbPostMapper tbPostMapper;

    @Override
    public UserDetailVo getUserByOpenId(String openId) {
        UserDetailVo userDetailVo = tbUserMapper.getUserByOpenId(openId);
        if (userDetailVo != null)
            return userDetailVo;
        return null;
    }

    @Override
    @Transactional
    public int addRobotUserIfo(UserDetailVo userDetailVo) {
        String s = MathUtils.makeUpNewData(Thread.currentThread().hashCode()+"", 3)+   MathUtils.randomDigitNumber(7);
        userDetailVo.setOpenId(s);
        userDetailVo.setUserGender("男");
        userDetailVo.setState(1);
        return tbUserMapper.addRobotUserIfo(userDetailVo);
    }

    @Override
    public int updateUserIfoById(UserDetailVo userDetailVo) {
        return tbUserMapper.updateUserInfoById(userDetailVo);
    }

    @Override
    public List<CollectPostDetailVo> getUserCollection(String openId) {
        List<CollectPostDetailVo> collectPostDetailVoList = tbCollectMapper.getPostDetailById(openId);
        return collectPostDetailVoList;

    }

    @Override
    @Transactional
    public int deleteCollection(String openId, Integer postId) {
        return tbCollectMapper.deleteCollectionById(openId, postId);
    }

    @Override
    @Transactional
    public List<PostDetailVo> getUserIssue(String openId) {
        List<PostDetailVo> postDetailVoList = tbPostMapper.getPostIssueByOpenId(openId);
        return postDetailVoList;
    }

    @Override
    @Transactional
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
