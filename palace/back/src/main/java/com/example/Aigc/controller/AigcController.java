package com.example.Aigc.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.example.Aigc.Service.AigcService;

import com.example.Aigc.entity.AiQuestion;
import com.example.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/Ai")
public class AigcController {

    @Resource
    AigcService aigcService;

// 问答
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Result AI(@RequestBody AiQuestion Question) throws NoApiKeyException, InputRequiredException, IOException {

        return aigcService.getAnswer(Question, Question.getId());
    }

//查询历史记录
    @RequestMapping(value = "/hisData",method = RequestMethod.GET)
    public Result getHIstoryData(String id){
        String userId = id;
        //查询前10条问答数据
        return aigcService.selectStateByUserId1(userId);
    }

    @Operation(summary = "AI绘画")
    @RequestMapping(value = "/AiDraw",method = RequestMethod.GET)
    public Result AiDraw(String Question, String id) throws NoApiKeyException, IOException{
        System.out.println(Question);
        return aigcService.getAiDraw(Question,id);
    }

    @Operation(summary = "AI语音对话")
    @PostMapping(value = "/AiConversations",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> AiConversations(@RequestParam MultipartFile file, String id) throws NoApiKeyException, IOException, InputRequiredException, LineUnavailableException, UnsupportedAudioFileException {
        return aigcService.getAiConversations(file,id);
    }

    /**
     * 获取文件
     *
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}")
    @Operation(summary = "获取文件")
    public void avatarPath(@PathVariable String flag, String id, HttpServletResponse response) {
        OutputStream os;
        try {
            if (StrUtil.isNotEmpty(flag)) {

                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(flag, "UTF-8"));
                response.setContentType("application/octet-stream");
                String userId = String.valueOf(id);
                //截取今天的日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String today = sdf.format(new Date());
                String filePath = Paths.get(System.getProperty("user.dir"), File.separator + "file").toString() +"\\aiDraw\\"+userId+"\\" + today + "\\" + flag;
                byte[] bytes = FileUtil.readBytes(filePath);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }
}
