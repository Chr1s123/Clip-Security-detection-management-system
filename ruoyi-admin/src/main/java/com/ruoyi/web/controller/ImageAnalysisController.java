package com.ruoyi.web.controller;

import com.ruoyi.common.annotation.Anonymous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.system.service.IClipService;

import java.util.HashMap;
import java.util.Map;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysUserService;

@RestController

public class ImageAnalysisController {

    @Autowired
    private IClipService clipService;
    @Autowired
    private TokenService tokenService;

    @Anonymous
    @PostMapping("/analyze-image")
    public ResponseEntity<Map<String, String>> analyzeImage(@RequestParam("image") MultipartFile image) {
        Map<String, String> response = new HashMap<>();
        try {
            if (image == null || image.isEmpty()) {
                response.put("status", "error");
                response.put("message", "图片文件为空");
                return ResponseEntity.badRequest().body(response);
            }
            System.out.println("Received file: " + image.getOriginalFilename());
            String result = clipService.analyzeImage(image);
            response.put("status", "success");
            response.put("result", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}