package com.example.Aigc.Service.impl;

import com.alibaba.dashscope.audio.asr.recognition.Recognition;
import com.alibaba.dashscope.audio.asr.recognition.RecognitionParam;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.example.Aigc.Service.AiVoiceService;
import com.example.Aigc.config.AiConfig;
import io.reactivex.Flowable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AiVoiceServiceImpl implements AiVoiceService {
    @Resource
    AiConfig aiConfig;
    @Override
    public String getVoice(MultipartFile file) throws NoApiKeyException, IOException, LineUnavailableException, UnsupportedAudioFileException {
        AtomicBoolean status = new AtomicBoolean(false);
        //todo 调用Apikey
        String ApiKey = aiConfig.getApiKey();
        // 创建一个Flowable<ByteBuffer>
//        Flowable<ByteBuffer> audioSource =
//                Flowable.create(
//                        emitter -> {
//                            new Thread(
//                                    () -> {
//                                        try {
//                                            // 创建音频格式
//                                            AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, false);
//                                            // 根据格式匹配默认录音设备
//                                            TargetDataLine targetDataLine =
//                                                    AudioSystem.getTargetDataLine(audioFormat);
//                                            targetDataLine.open(audioFormat);
//                                            // 开始录音
//                                            targetDataLine.start();
//                                            ByteBuffer buffer = ByteBuffer.allocate(1024);
//                                            long start = System.currentTimeMillis();
//                                            // 录音30s并进行实时转写
//                                            while (System.currentTimeMillis() - start < 300000) {
//                                                int read = targetDataLine.read(buffer.array(), 0, buffer.capacity());
//                                                if (read > 0) {
//                                                    if(status.get() ==true){
//                                                        break;
//                                                    }
//                                                    buffer.limit(read);
//                                                    // 将录音音频数据发送给流式识别服务
//                                                    emitter.onNext(buffer);
//                                                    buffer = ByteBuffer.allocate(1024);
//                                                    // 录音速率有限，防止cpu占用过高，休眠一小会儿
//                                                    Thread.sleep(20);
//                                                }
//                                            }
//                                            // 通知结束转写
//                                            targetDataLine.close();
//                                            status.set(false);
//                                            emitter.onComplete();
//                                        } catch (Exception e) {
//                                            emitter.onError(e);
//                                        }
//                                    })
//                                    .start();
//                        },
//                        BackpressureStrategy.BUFFER);

        // 从.wav文件创建Flowable<ByteBuffer>
        Flowable<ByteBuffer> audioSource = createFlowableFromWav(file);

        // 创建Recognizer

        Recognition recognizer = new Recognition();
        // 创建RecognitionParam，audioFrames参数中传入上面创建的Flowable<ByteBuffer>
        RecognitionParam param =
                RecognitionParam.builder()
                        .model("paraformer-realtime-v1")
                        .format("pcm")
                        .sampleRate(16000)
                        .apiKey(ApiKey)
                        .build();

        AtomicReference<String> aiResult = new AtomicReference<>("");
        // 流式调用接口
        recognizer
                .streamCall(param, audioSource)
                // 调用Flowable的subscribe方法订阅结果
                .blockingForEach(
                        result -> {
                            // 打印最终结果
                            if (result.isSentenceEnd()) {
                                System.out.println("Fix:" + result.getSentence().getText());
                                status.set(true);
                                aiResult.set(aiResult+result.getSentence().getText());
                            } else {
                                System.out.println("Result:" + result.getSentence().getText());
                            }
                        });
        return String.valueOf(aiResult);
    }

    private Flowable<ByteBuffer> createFlowableFromWav(MultipartFile file) throws IOException, UnsupportedAudioFileException {
        // 加载.wav文件到AudioInputStream中
        byte[] wavBytes = file.getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(wavBytes);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bais);


        // 从AudioInputStream获取音频格式
        AudioFormat audioFormat = audioInputStream.getFormat();

        // 确保格式符合识别服务的要求（例如，采样率）
//        if (audioFormat.getSampleRate() != 16000 || audioFormat.getSampleSizeInBits() != 16 || audioFormat.getChannels() != 1 || !audioFormat.isBigEndian()) {
//            throw new UnsupportedAudioFileException("提供的.wav文件具有不受支持的音频格式。");
//        }

        AtomicInteger bytesRead = new AtomicInteger(0);

        return Flowable.generate(
                () -> {
                    byte[] buffer = new byte[1024];
                    bytesRead.set(audioInputStream.read(buffer));
                    return ByteBuffer.wrap(buffer);
                },
                (buffer, emitter) -> {
                    if (bytesRead.get() > 0) {
                        buffer.limit(bytesRead.get());
                        emitter.onNext(buffer);
                        buffer.clear();
                        bytesRead.set(audioInputStream.read(buffer.array()));
                    } else {
                        emitter.onComplete();
                    }
                    return buffer;
                });
    }

}
