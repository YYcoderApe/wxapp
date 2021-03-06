package com.zczp.service_yycoder.impl;

import com.zczp.dao.TbPosterMapper;
import com.zczp.entity.TbPoster;
import com.zczp.service_yycoder.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosterServiceImpl implements PosterService {

    @Autowired(required = false)
    private TbPosterMapper tbPosterMapper;

    @Override
    public List<TbPoster> getAllPoster() {
        List<TbPoster> PosterList = tbPosterMapper.selectAllPoster();
        if(PosterList!=null){
            return PosterList;
        }
        return null;
    }

    @Override
    public TbPoster getPosterById(Integer posterId) {
        TbPoster tbPoster = tbPosterMapper.selectByPrimaryKey(posterId);
        if(tbPoster!=null){
            return tbPoster;
        }
        return null;
    }

    @Override
    public int addPoster(TbPoster tbPoster) {
        return tbPosterMapper.insert(tbPoster);
    }

    @Override
    public int updatePoster(TbPoster tbPoster) {
        return tbPosterMapper.updateByPrimaryKeySelective(tbPoster);
    }

    @Override
    public int deletePoster(Integer posterId) {
        tbPosterMapper.deleteByPrimaryKey(posterId);
        tbPosterMapper.updateSerialNumber(posterId);
        return 1;
    }
}
