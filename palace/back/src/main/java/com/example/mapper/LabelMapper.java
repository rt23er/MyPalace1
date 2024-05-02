package com.example.mapper;

import com.example.entity.Category;
import com.example.entity.Label;

import java.util.List;

public interface LabelMapper {
    void add(Label label);

    void deleteById(Integer id);

    void updateById(Label label);

    Label selectById(Integer id);

    List<Label> selectAll(Label label);
}
