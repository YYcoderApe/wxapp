package com.zczp.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zczp.dao.*;
import com.zczp.entity.TbAskReply;
import com.zczp.entity.TbComment;
import com.zczp.vo_cancer.CommentsVo;
import com.zczp.vo_yycoder.CollectPostDetailVo;
import com.zczp.vo_yycoder.MyAskReplyVo;
import com.zczp.vo_yycoder.TbCommentsVo;
import com.zczp.vo_yycoder.UserDetailVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    TbUserMapper tbUserMapper;
//    @Test
//    public void getMyAskReplyList(){
//        //根据open_id去TbAskReply找到post的先后顺序，返回TbAskReply
//        List<TbAskReply> askReplyList = tbAskReplyMapper.getAskReplyByOpenId("1");
//        //postDeatilVo（类，collectPostDeatailVo只是多了个state）
//        CollectPostDetailVo collectPostDetailVo;
//        //评论表，每个评论表含提问和回复
//        List<TbCommentsVo> tbCommentsVoList;
//        List<TbCommentsVo> tbCommentsVoList1 = new ArrayList<TbCommentsVo>();
//        //含评论表和post表
//        List<MyAskReplyVo> myAskReplyVoList = new ArrayList<MyAskReplyVo>();
//        //评论表对象
//        TbComment tbComment=new TbComment();
//        TbCommentsVo tbCommentsVo=new TbCommentsVo();
//        List<TbCommentsVo> commentList;
//        tbComment.setFromId("1");
//        for(TbAskReply tbAskReply:askReplyList){
//            tbComment.setPostId(tbAskReply.getPostId());
//            tbCommentsVoList = tbCommentMapper.getCommentByPostId(tbComment.getPostId());
//            int index=0;
//            while(tbCommentsVoList.size()>index) {
//                tbComment.setReplyId(tbCommentsVoList.get(index).getCommentId());
//                tbComment.setToId(tbCommentsVoList.get(index).getFromId());
//                tbCommentsVo.setCommentList(tbCommentMapper.selectCommentList(tbComment));
//
//                tbCommentsVoList.get(index).setCommentList(tbCommentsVo.getCommentList());
//
//                index++;
//            }
//            for(TbCommentsVo tbCommentsVom:tbCommentsVoList){
//                if(tbCommentsVom.getCommentList().size()>0||tbCommentsVom.getFromId()=="1")
//                    tbCommentsVoList1.add(tbCommentsVom);
//            }
//            collectPostDetailVo=tbPostMapper.getPostDetailById(tbAskReply.getPostId());
//            MyAskReplyVo myAskReplyVo =new MyAskReplyVo();
//            myAskReplyVo.setPostDetailList(collectPostDetailVo);
//            myAskReplyVo.setCommentList(tbCommentsVoList1);
//            myAskReplyVoList.add(myAskReplyVo);
//        }
//        System.out.println(myAskReplyVoList.get(0).getCommentList()) ;
//        System.out.println(myAskReplyVoList.get(0).getPostDetailList()) ;
//        System.out.println(myAskReplyVoList.get(1).getCommentList()) ;
//        System.out.println(myAskReplyVoList.get(1).getPostDetailList()) ;
//        System.out.println(myAskReplyVoList.get(2).getCommentList()) ;
//        System.out.println(myAskReplyVoList.get(2).getPostDetailList()) ;
//
//
//    }

  //  @Test
//    public void test(){
//            PageHelper.startPage(2,2);
//            List<UserDetailVo> userDetailVoList =tbUserMapper.getAllUser();;
//            PageInfo<UserDetailVo> userDetailVoPageInfo=new PageInfo<UserDetailVo>(userDetailVoList);
//            List<UserDetailVo> userList=userDetailVoPageInfo.getList();
//             System.out.println(userDetailVoList);
//        System.out.println(userList);
//            System.out.println(userDetailVoPageInfo);
//    }
}
