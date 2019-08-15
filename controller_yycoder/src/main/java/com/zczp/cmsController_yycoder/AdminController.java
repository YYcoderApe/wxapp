package com.zczp.cmsController_yycoder;


        import com.zczp.service_cancer.TbPostService;
        import com.zczp.service_yycoder.AdminService;
        import com.zczp.service_yycoder.AskReplyService;
        import com.zczp.service_yycoder.UserService;
        import com.zczp.util.AjaxResult;
        import com.zczp.util.JwtUtil;
        import com.zczp.util.PageQueryUtil;
        import com.zczp.util.PageResult;
        import com.zczp.vo_cancer.PostDetailsVo;
        import com.zczp.vo_yycoder.CollectPostDetailVo;
        import com.zczp.vo_yycoder.MyAskReplyVo;
        import com.zczp.vo_yycoder.PostDetailVo;
        import com.zczp.vo_yycoder.UserDetailVo;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import io.swagger.annotations.ApiParam;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.util.StringUtils;
        import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

/**
 * 管理员 进行 对用户信息的管理
 */
@RestController
@RequestMapping("admin")
@Api(tags = "登录界面模块")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    @ApiOperation("管理员登录")
    public AjaxResult adminLogin(
            @RequestParam @ApiParam("Admin名字") String adminName,
            @RequestParam @ApiParam("Admin密码") String adminPwd) {
        boolean result = adminService.checkAdmin(adminName, adminPwd);
        if (result) {
//            //把token返回给客户端-->客户端保存至cookie-->客户端每次请求附带cookie参数
//            String JWT = JwtUtil
            return new AjaxResult().error("登录成功");
        }
        return new AjaxResult().error("用户名或密码错误，请重新输入");

    }

}
