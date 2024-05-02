package com.example.service;

import com.example.entity.Category;
import com.example.entity.Label;
import com.example.mapper.LabelMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LabelService {

    @Resource
    LabelMapper labelMapper;

    public void add(Label label) {
        labelMapper.add(label);
    }

    public void deleteById(Integer id) {
        labelMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id:ids) {
            labelMapper.deleteById(id);
        }
    }

    public void updateById(Label label) {
        labelMapper.updateById(label);
    }

    public Label selectById(Integer id) {
        return labelMapper.selectById(id);
    }

    public List<Label> selectAll(Label label) {
        return labelMapper.selectAll(label);
    }

    public PageInfo<Label> selectPage(Label label, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Label> list = labelMapper.selectAll(label);
        return PageInfo.of(list);
    }
}
