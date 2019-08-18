package com.zczp.service_yycoder;

import com.zczp.entity.TbComment;
import com.zczp.util.PageQueryUtil;
import com.zczp.util.PageResult;
import com.zczp.vo_yycoder.*;

import java.util.List;

public interface UserService {

    //根据登录传来的id获取用户的信息
    UserDetailVo getUserByOpenId(String openId);

    //增加虚拟用户
    int addRobotUserIfo(UserDetailVo userDetailVo);

    //用户修改个人信息
    int updateUserIfoById(UserDetailVo userDetailVo);

    //用户查看个人收藏
    List<CollectPostDetailVo> getUserCollection(String openId);

    //删除个人收藏
    int deleteCollection(String openId,Integer postId);

    //查看个人发布信息
    List<PostDetailVo> getUserIssue(String openId);

    //删除个人发布信息
    int deleteUserIssue(String openId,Integer postId);

    //获取所有用户的信息
    List<UserDetailVo> getAllUser(Integer state);

    //根据昵称查找用户
    List<UserDetailVo> searchUserByName(String userName);

    //销户操作（删除用户信息）
    int deleteUserById(String openId);
}
