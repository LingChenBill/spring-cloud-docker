package com.lc.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * File upload controller.
 *
 * @author zyz.
 */
@Controller
@Slf4j
public class FileUploadController {

    /**
     * Function: 上传文件。
     * { 测试界面：http://localhost:8050/index.html
     *   使用命令：curl -F "file=@文件全名" localhost:8050/upload.
     * }
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam(value = "file", required = true)MultipartFile file) throws Exception {
        byte[] bytes = file.getBytes();
        File fileToSave = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes, fileToSave);
        log.info("File size: " + fileToSave.length());
        log.info("File upload success!");
        // 文件在服务器上的绝对路径。
        return fileToSave.getAbsolutePath();
    }
}
