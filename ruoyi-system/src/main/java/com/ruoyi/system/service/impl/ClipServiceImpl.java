//ClipServiceImpl
package com.ruoyi.system.service.impl;

import com.ruoyi.system.service.IClipService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Service
public class ClipServiceImpl implements IClipService {

    @Override
    public String analyzeImage(MultipartFile image) throws Exception {
        File tempFile = File.createTempFile("image_", ".jpg");
        image.transferTo(tempFile);

        ProcessBuilder pb = new ProcessBuilder(
                "python", "clip_analyze.py", tempFile.getAbsolutePath()
        );
        Process p = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(p.getInputStream())
        );
        String result = reader.readLine();

        tempFile.delete();
        return result != null ? result : "error";
    }
}