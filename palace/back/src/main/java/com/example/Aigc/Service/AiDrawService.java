package com.example.Aigc.Service;

import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisResult;
import com.alibaba.dashscope.exception.NoApiKeyException;

public interface AiDrawService {
    ImageSynthesisResult basicCall(int n, String size, String prompt) throws NoApiKeyException;
}
