package com.zczp.service_cancer;

import com.zczp.entity.TbUser;
import com.zczp.entity.WxAccount;
import com.zczp.vo_cancer.AuthorizeVO;
import com.zczp.vo_cancer.JwtToken;
import jdk.nashorn.internal.parser.Token;

public interface WxUserService {

    String authorize(AuthorizeVO authorizeVO);

    String login(String code);

    int save(TbUser tbUser);
}
