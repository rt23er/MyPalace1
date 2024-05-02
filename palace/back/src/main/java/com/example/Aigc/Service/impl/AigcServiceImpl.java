package com.example.Aigc.Service.impl;

import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.MessageManager;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.example.Aigc.Service.AiDrawService;
import com.example.Aigc.Service.AiSpeechSynthesis;
import com.example.Aigc.Service.AiTalkService;
import com.example.Aigc.Service.AigcService;
import com.example.mapper.AigcDao;
import com.example.Aigc.entity.AiQuestion;
import com.example.Aigc.entity.Aigc;
import com.example.common.Result;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AigcServiceImpl implements AigcService {

    @Resource
    AigcDao aigcDao;

    @Resource
    AiTalkService aiTalkService;

    @Resource
    AiDrawService aiDrawService;

    @Resource
    AiVoiceServiceImpl aiVoiceService;

    @Resource
    AiSpeechSynthesis aiSpeechSynthesis;

    @Override
    public void insert(Aigc aigc) {
        //将对话信息保存
        aigcDao.insert(aigc);
    }


    @Override
    public Result delete(Aigc aigc) {

        return null;
    }

    @Override
    public List<Aigc> selectStateByUserId(String userId) {
        List<Aigc> aigcList = aigcDao.selectStateByUserId(userId);
        List<Aigc> newAigcList = new ArrayList<>();
        for (int i = aigcList.size()-1; i >= 0 ; i--) {
            newAigcList.add(aigcList.get(i));
        }
        return newAigcList;
    }


    @Override
    public Result selectStateByUserId1(String userId) {
        List<Aigc> aigcList = aigcDao.selectStateByUserId(userId);
        List<Aigc> newAigcList = new ArrayList<>();
        for (int i = aigcList.size()-1; i >= 0 ; i--) {
            newAigcList.add(aigcList.get(i));
        }
        return Result.success(newAigcList);
    }

    @Override
    public Result getAnswer(AiQuestion Question, String id) throws NoApiKeyException, InputRequiredException, IOException {

        //拼接完成后塞入AiTalk服务
        List<Aigc> aigcList = this.selectStateByUserId(id);
        MessageManager msgManager = new MessageManager(100);
        for (Aigc aigc:aigcList) {
            Message userMsg = Message.builder().role(Role.USER.getValue()).content(aigc.getUserMessage()).build();
            Message assistantMsg = Message.builder().role(Role.ASSISTANT.getValue()).content(aigc.getAnswer()).build();
            msgManager.add(userMsg);
            msgManager.add(assistantMsg);
        }
        Message newUserMessage = Message.builder().role(Role.USER.getValue()).content(Question.getQuestion()).build();
        msgManager.add(newUserMessage);
        String answer = (String) aiTalkService.test(msgManager).getData();
        Aigc aigc = new Aigc();
        aigc.setUserID( id);
        aigc.setUserMessage(Question.getQuestion());
        aigc.setAnswer(answer);
        aigc.setCreatDate(new Date());
        aigc.setAiID(String.valueOf(UUID.randomUUID()));
        this.insert(aigc);
        return Result.success(answer);
    }
    @Override
    public Result getAiDraw(String question, String  id) throws NoApiKeyException, IOException {
        ImageSynthesisResult imageSynthesisResult = aiDrawService.basicCall(4,"1024*1024", question);
        List<Map<String, String>> urls = imageSynthesisResult.getOutput().getResults();
        String userId = String.valueOf(id);
        //截取今天的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        String targetDirectory = Paths.get(System.getProperty("user.dir"), File.separator + "file").toString()+"\\aiDraw\\"+userId+"\\"+today;
        List<String> urlList = new ArrayList<>();
        for (Map map: urls) {
            // 获取URL
            URL url = new URL((String) map.get("url"));
            String imageUrl = url.toString();

            // 使用正则表达式匹配URL中的文件名
            Pattern pattern = Pattern.compile(".*/([^/?]+)\\?.*");
            Matcher matcher = pattern.matcher(imageUrl);
            String fileName = "";
            // 检查是否成功匹配
            if (matcher.matches()) {
                // 如果匹配成功，则提取文件名
                fileName = matcher.group(1);
                System.out.println("提取到的文件名: " + fileName);
            } else {
                System.out.println("未找到匹配的文件名");
            }

            File fileDir = new File(targetDirectory);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            // 创建目标文件
            File targetFile = new File(targetDirectory, fileName);
            // 打开输入流
            InputStream in = url.openStream();
            ReadableByteChannel rbc = Channels.newChannel(in);

            // 打开输出流，准备写入文件
            OutputStream out = new FileOutputStream(targetFile);
            ((FileOutputStream) out).getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            // 关闭流
            out.close();
            rbc.close();
            in.close();

            System.out.println("已下载: " + fileName);
            urlList.add("http://localhost:9090/Ai/"+fileName);
        }

        return Result.success(urlList);
    }

    @Override
    public ResponseEntity<byte[]> getAiConversations(MultipartFile file, String id) throws NoApiKeyException, LineUnavailableException, IOException, InputRequiredException, UnsupportedAudioFileException {
        //识别出来的问题
        String question = aiVoiceService.getVoice(file);
        System.out.println(question);
        MessageManager msgManager = new MessageManager(100);
        List<Aigc> aigcList = this.selectStateByUserId(id);
        for (Aigc aigc:aigcList) {
            Message userMsg = Message.builder().role(Role.USER.getValue()).content(aigc.getUserMessage()).build();
            Message assistantMsg = Message.builder().role(Role.ASSISTANT.getValue()).content(aigc.getAnswer()).build();
            msgManager.add(userMsg);
            msgManager.add(assistantMsg);
        }
        Message newUserMessage = Message.builder().role(Role.USER.getValue()).content(question).build();
        msgManager.add(newUserMessage);
        String answer = (String) aiTalkService.test(msgManager).getData();
        Aigc aigc = new Aigc();
        aigc.setUserID(id);
        aigc.setUserMessage(question);
        aigc.setAnswer(answer);
        aigc.setCreatDate(new Date());
        aigc.setAiID(String.valueOf(UUID.randomUUID()));
        this.insert(aigc);

        //截取今天的日期
        String userId = String.valueOf(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());

        String targetDirectory = Paths.get(System.getProperty("user.dir"), File.separator + "file").toString() + "\\aiDraw\\" + userId + "\\" + today;

        ByteArrayInputStream audioStream = aiSpeechSynthesis.StreamAuidoDataToSpeaker(answer, targetDirectory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/wav"));
        headers.setContentLength(audioStream.available());

        byte[] audioBytes = IOUtils.toByteArray(audioStream);
        // 使用 IOUtils.toByteArray
        return ResponseEntity.ok().headers(headers).body(audioBytes);

    }
}
