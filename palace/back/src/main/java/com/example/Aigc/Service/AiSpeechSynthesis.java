package com.example.Aigc.Service;

import java.io.ByteArrayInputStream;

public interface AiSpeechSynthesis {
    ByteArrayInputStream StreamAuidoDataToSpeaker(String Answer, String targetDirectory);
}
