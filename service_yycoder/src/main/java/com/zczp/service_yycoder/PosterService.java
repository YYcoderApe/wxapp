package com.zczp.service_yycoder;

import com.zczp.entity.TbPoster;

import java.util.List;
import com.zczp.entity.TbPoster;

import java.util.List;

public interface PosterService {

    //查询所有海报轮播图
    List<TbPoster> getAllPoster();

    //根据ID查询海报轮播图
    TbPoster getPosterById(Integer posterId);

    //新增海报轮播图(后台)
    int addPoster(TbPoster tbPoster);

    //编辑海报轮播图(后台)
    int updatePoster(TbPoster tbPoster);

    //删除海报轮播图(后台)
    int deletePoster(Integer posterId);


}
