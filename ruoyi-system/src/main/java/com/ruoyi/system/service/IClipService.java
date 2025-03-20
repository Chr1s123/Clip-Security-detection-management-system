//IClipService.java
package com.ruoyi.system.service;

import org.springframework.web.multipart.MultipartFile;

public interface IClipService {
    String analyzeImage(MultipartFile image) throws Exception;
}