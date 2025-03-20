package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysImage;
import com.ruoyi.system.service.ISysImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图片管理控制器
 */
@RestController
@RequestMapping("/system/image")
public class ImageController extends BaseController {

    @Autowired
    private ISysImageService imageService;

    /**
     * 查询图片列表
     */
    @PreAuthorize("@ss.hasPermi('system:image:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysImage image) {
        startPage();
        List<SysImage> list = imageService.selectImageList(image);
        return getDataTable(list);
    }

    /**
     * 新增图片
     */
    @PreAuthorize("@ss.hasPermi('system:image:add')")
    @Log(title = "图片管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysImage image) {
        return toAjax(imageService.insertImage(image));
    }

    /**
     * 修改图片
     */
    @PreAuthorize("@ss.hasPermi('system:image:edit')")
    @Log(title = "图片管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysImage image) {
        return toAjax(imageService.updateImage(image));
    }

    /**
     * 删除图片
     */
    @PreAuthorize("@ss.hasPermi('system:image:remove')")
    @Log(title = "图片管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{imageIds}")
    public AjaxResult remove(@PathVariable Long[] imageIds) {
        return toAjax(imageService.deleteImageByIds(imageIds));
    }
}