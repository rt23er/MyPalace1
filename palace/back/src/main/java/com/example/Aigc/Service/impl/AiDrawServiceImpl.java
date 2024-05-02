package com.example.Aigc.Service.impl;

import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesis;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisParam;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;
import com.example.Aigc.Service.AiDrawService;
import com.example.Aigc.config.AiConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ResourceBundle;

@Service
public class AiDrawServiceImpl implements AiDrawService {

    @Resource
    AiConfig aiConfig;
    public ImageSynthesisResult basicCall(int n, String size, String prompt) throws ApiException, NoApiKeyException{
        //todo 调用Apikey
        String ApiKey = aiConfig.getApiKey();

        Constants.apiKey = ApiKey;
        ImageSynthesis is = new ImageSynthesis();
        ImageSynthesisParam param =
                ImageSynthesisParam.builder()
                        .model(ImageSynthesis.Models.WANX_V1)
                        .n(n)
                        .size(size)
                        .prompt(prompt)
                        .build();

        ImageSynthesisResult result = is.call(param);
        return result;
    }
}
