package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysImage;

import java.util.List;

public interface SysImageMapper {
    /**
     * 查询图片列表
     */
    List<SysImage> selectImageList(SysImage image);

    /**
     * 新增图片
     */
    int insertImage(SysImage image);

    /**
     * 修改图片
     */
    int updateImage(SysImage image);

    /**
     * 删除图片
     */
    int deleteImageByIds(Long[] imageIds);
}