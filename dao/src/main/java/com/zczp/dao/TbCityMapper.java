package com.zczp.dao;

import com.zczp.entity.TbCity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbCityMapper extends BaseMapper<TbCity>{

    List<TbCity> selectAll();
}