package com.zylear.j2eelab.dao;

import com.zylear.j2eelab.domain.TeacherChange;

public interface TeacherChangeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherChange record);

    int insertSelective(TeacherChange record);

    TeacherChange selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherChange record);

    int updateByPrimaryKey(TeacherChange record);
}