package com.example.Aigc.Service.impl;

import com.alibaba.dashscope.audio.tts.SpeechSynthesisParam;
import com.alibaba.dashscope.audio.tts.SpeechSynthesizer;
import com.example.Aigc.Service.AiSpeechSynthesis;
import com.example.Aigc.config.AiConfig;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;

@Service
public class AiSpeechSynthesisImpl implements AiSpeechSynthesis {

    @Resource
    AiConfig aiConfig;
    @Override
    public ByteArrayInputStream StreamAuidoDataToSpeaker(String Answer, String targetDirectory) {
        //todo 调用Apikey
        String ApiKey = aiConfig.getApiKey();
        SpeechSynthesizer synthesizer = new SpeechSynthesizer();
        SpeechSynthesisParam param =
                SpeechSynthesisParam.builder()
                        .apiKey(ApiKey)
                        .model("sambert-zhimiao-emo-v1")
                        .text(Answer)
                        .sampleRate(48000)
                        .build();

        File fileDir = new File(targetDirectory);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(targetDirectory,"output.wav");

        // 调用call方法，传入param参数，获取合成音频
        ByteBuffer audio = synthesizer.call(param);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(audio.array());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ByteArrayInputStream(audio.array());
    }

}
