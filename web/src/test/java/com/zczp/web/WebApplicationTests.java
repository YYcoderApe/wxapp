package com.zczp.web;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zczp.dao.*;
import com.zczp.entity.TbAskReply;
import com.zczp.entity.TbComment;
import com.zczp.service_cancer.Impl.TbCompanyServiceImpl;
import com.zczp.vo_cancer.CommentsVo;
import com.zczp.vo_cancer.CompanyVo;
import com.zczp.vo_yycoder.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

    //    @Test
//    public void contextLoads() {
//         //url中的  appid 和  secret 开发者会给你  这相当于你小程序的ID和密码       js_code 也会给你  js_code是用微信开发者工具调用方法获得
//            String  appid="wx1fb288a9dc863b05";//你小程序Id
//            String secret="26dc028172cffbdecb553383508a0895";//填入你小程序的secret
//            String code="061wHUG61mnkqM1oJGF617nSG61wHUGE";//用微信开发者工具获取到的code
//            String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
//            JSONObject jsonObj= HttpClientUtils.httpGet(url);
//            System.out.println(jsonObj);
//            //打印结果
//
//    }
//    @Autowired(required = false)
//    TbAskReplyMapper tbAskReplyMapper;
//    @Autowired(required = false)
//    TbPostMapper tbPostMapper;
//    @Autowired(required = false)
//    TbCommentMapper tbCommentMapper;
    @Autowired(required = false)
    private TbAskReplyMapper tbAskReplyMapper;

    @Autowired(required = false)
    private TbCommentMapper tbCommentMapper;

    @Autowired(required = false)
    private TbPostMapper tbPostMapper;

    //postDeatilVo（类，collectPostDeatailVo只是多了个state）
    CollectPostDetailVo collectPostDetailVo;

    //评论表，每个评论表含提问和回复
    List<TbCommentsVo> CommentsVoList = null;
    List<TbCommentsVo> tbCommentsVoList = null;
    //评论表对象
    TbCommentsVo tbCommentsVo = new TbCommentsVo();

    @Test
    public void test() {
        TbComment tbComment = new TbComment();
        //含评论表和post表
        List<UserAskReplyVo> userAskReplyVoList = new ArrayList<UserAskReplyVo>();
        //根据open_id去TbAskReply找到post的先后顺序，返回TbAskReply
        List<TbAskReply> askReplyList = tbAskReplyMapper.getAskReplyByOpenId("1");
        tbComment.setFromId("1");
        for (TbAskReply tbAskReply : askReplyList) {
            tbComment.setPostId(tbAskReply.getPostId());
            List<TbComment> userCommentList = tbCommentMapper.getUserCommentList(tbComment);
            System.out.println(userCommentList);
            collectPostDetailVo = tbPostMapper.getPostDetailById(tbAskReply.getPostId());
            String collectPostDetail = JSONObject.toJSONString(collectPostDetailVo);
            int index = 0;
            while (userCommentList.size() > index) {
                UserAskReplyVo userAskReplyVo = JSONObject.parseObject(collectPostDetail, UserAskReplyVo.class);
                userAskReplyVo.setCommentId(userCommentList.get(index).getCommentId());
                userAskReplyVo.setContent(userCommentList.get(index).getContent());
                if (userCommentList.get(index).getReplyId() != null)
                    userAskReplyVo.setIsReply(1);
                else
                    userAskReplyVo.setIsReply(0);

                userAskReplyVoList.add(userAskReplyVo);
                System.out.println(userAskReplyVoList);
                index++;
            }

        }
        System.out.println(userAskReplyVoList);
    }

}