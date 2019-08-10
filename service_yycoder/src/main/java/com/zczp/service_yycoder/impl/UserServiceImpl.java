package com.zczp.service_yycoder.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.zczp.dao.*;
import com.zczp.entity.TbAskReply;
import com.zczp.entity.TbComment;
import com.zczp.service_yycoder.UserService;
import com.zczp.util.PageQueryUtil;
import com.zczp.util.PageResult;
import com.zczp.vo_cancer.CommentsVo;
import com.zczp.vo_yycoder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Transactional
    public UserDetailVo getUserByOpenId(String openId) {
        UserDetailVo userDetailVo =tbUserMapper.getUserByOpenId(openId);
        if(userDetailVo!=null)
            return userDetailVo;
        return null;
    }

    @Override
    @Transactional
    public int updateUserIfoById(UserDetailVo userDetailVo) {
        return tbUserMapper.updateUserInfoById(userDetailVo);
    }

    @Override
    @Transactional
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
        return  tbPostMapper.deleteUserIssueById(openId, postId);
    }

    @Override
    public PageResult getAllUser(PageQueryUtil pageUtil) {
          List<UserDetailVo> tags=tbUserMapper.getAllUser(pageUtil);
        int total = tbUserMapper.getTotalTags(pageUtil);
        PageResult pageResult = new PageResult(tags, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public UserDetailVo searchUserByName(String userName) {
        return tbUserMapper.seachUserByName(userName);
    }

    @Override
    public int deleteUserById(String openId) {
        return tbUserMapper.deleteByPrimaryKey(openId);
    }


}
