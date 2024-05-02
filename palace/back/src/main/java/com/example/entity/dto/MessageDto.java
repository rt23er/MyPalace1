package com.example.entity.dto;

import lombok.Data;

@Data
public class MessageDto {
    private String content;
    private String roomId;
    private String userId;
}

