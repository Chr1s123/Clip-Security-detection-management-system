package com.ruoyi.web.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysImage;
import com.ruoyi.system.service.ISysImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/image") // 首页相关接口，不放在 /system 下
public class SysImageController extends BaseController {
    @Autowired
    private ISysImageService sysImageService;

    /**
     * 查询图片列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysImage sysImage) {
        startPage(); // 分页
        List<SysImage> list = sysImageService.selectImageList(sysImage);
        return getDataTable(list);
    }
}