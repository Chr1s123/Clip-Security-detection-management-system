package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysImage;
import com.ruoyi.system.mapper.SysImageMapper;
import com.ruoyi.system.service.ISysImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysImageServiceImpl implements ISysImageService {

    @Autowired
    private SysImageMapper imageMapper;

    @Override
    public List<SysImage> selectImageList(SysImage image) {
        return imageMapper.selectImageList(image);
    }

    @Override
    public int insertImage(SysImage image) {
        return imageMapper.insertImage(image);
    }

    @Override
    public int updateImage(SysImage image) {
        return imageMapper.updateImage(image);
    }

    @Override
    public int deleteImageByIds(Long[] imageIds) {
        return imageMapper.deleteImageByIds(imageIds);
    }
}