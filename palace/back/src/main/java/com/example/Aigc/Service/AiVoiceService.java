package com.example.Aigc.Service;

import com.alibaba.dashscope.exception.NoApiKeyException;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface AiVoiceService {
    String getVoice(MultipartFile file) throws NoApiKeyException, IOException, LineUnavailableException, UnsupportedAudioFileException;
}
