package com.zczp.service_yycoder.impl;

import com.zczp.dao.TbCityMapper;
import com.zczp.dao.TbPostTypeMapper;
import com.zczp.entity.TbCity;
import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostType;
import com.zczp.service_yycoder.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired(required = false)
    private TbCityMapper tbCityMapper;

    @Autowired(required = false)
    private TbPostTypeMapper tbPostTypeMapper;

    TbCity tbCity = new TbCity();
    TbPostType tbPostType = new TbPostType();

    @Override
    public int addCityLabel(String cityName) {

        tbCity.setCityName(cityName);
        tbCity.setCityNewDate(new Date());
        int result = tbCityMapper.insert(tbCity);
        return result;
    }

    @Override
    public int updateCityLabel(TbCity tbCity) {
        tbCity.setCityNewDate(new Date());
        return tbCityMapper.updateByPrimaryKeySelective(tbCity);
    }

    @Override
    public int deleteCityLabel(Integer cityId) {
        tbCityMapper.deleteByPrimaryKey(cityId);
        tbCityMapper.updateSerialNumber();
        return 1;
    }

    @Override
    public int addPostTypeLabel(String typeName) {
        tbPostType.setTypeName(typeName);
        tbPostType.setTypeNewDate(new Date());
        tbPostType.setTypeId(tbPostTypeMapper.getMaxTypeId()+1);
        return tbPostTypeMapper.insert(tbPostType);
    }

    @Override
    public int updatePostTypeLabel(TbPostType tbPostType) {
        tbPostType.setTypeNewDate(new Date());
        return tbPostTypeMapper.updateByPrimaryKeySelective(tbPostType);
    }

    @Override
    public int deletePostTypeLabel(Integer typeId) {
        tbPostTypeMapper.deleteByPrimaryKey(typeId);
        tbPostTypeMapper.updateSerialNumber(typeId);
        return 1;
    }
}