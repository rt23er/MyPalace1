package com.example.service;

import com.example.mapper.RoomAnswedrMapper;
import com.example.mapper.RoomMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TalkService {

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private RoomAnswedrMapper roomAnswedrMapper;


    public Object crerateRoom(String firstId, String secondId) {
        return null;
    }

    public Object answer(String firstId, String secondId) {
        return null;
    }
}
