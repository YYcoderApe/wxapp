package com.zczp.dao;

import com.zczp.entity.TbUser;
import com.zczp.util.PageQueryUtil;
import com.zczp.vo_yycoder.UserDetailVo;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Repository
public interface TbUserMapper extends BaseMapper<TbUser>{

    //根据登录传来的openid展示用户信息
    UserDetailVo getUserByOpenId(String openId);

    //根据openId进行销户操作
    int deleteByPrimaryKey(String id);

    //查询所有的用户
    List<UserDetailVo> getAllUser(PageQueryUtil pageUtil);

    //修改用户信息
    int updateUserInfoById( UserDetailVo userDetailVo);

    //查看个人收藏
    List<Integer> getUserCollection( String openId);

    //获取用户的总数量
    int getTotalTags(PageQueryUtil pageUtil);

    //根据昵称进行搜索
    UserDetailVo seachUserByName(String userName);

}