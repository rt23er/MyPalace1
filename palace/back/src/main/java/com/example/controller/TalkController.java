package com.example.controller;

import com.example.common.Result;
import com.example.entity.Notice;
import com.example.service.TalkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/talk")
public class TalkController {

    @Resource
    private TalkService talkService;

    @GetMapping("/talkRoom")
    public Result talkRoom(@PathVariable String firstId,String SecondId) {
        return Result.success(talkService.crerateRoom(firstId,SecondId));
    }

    @GetMapping("/answer")
    public Result answer(@PathVariable String firstId,String SecondId) {
        return Result.success(talkService.answer(firstId,SecondId));
    }


}
