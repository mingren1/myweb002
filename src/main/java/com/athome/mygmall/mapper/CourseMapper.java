package com.athome.mygmall.mapper;

import java.util.List;

import com.athome.mygmall.domain.Course;

public interface CourseMapper {
    int insert(Course record);

    List<Course> selectAll();
}