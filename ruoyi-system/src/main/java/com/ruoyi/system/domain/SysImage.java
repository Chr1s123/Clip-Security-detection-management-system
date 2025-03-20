package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class SysImage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 图片ID */
    private Long imageId;

    /** 图片名称 */
    @Excel(name = "图片名称")
    private String imageName;

    /** 图片URL */
    @Excel(name = "图片URL")
    private String imageUrl;

    // Getters and Setters
    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}