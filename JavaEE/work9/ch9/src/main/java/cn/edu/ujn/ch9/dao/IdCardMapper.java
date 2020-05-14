package cn.edu.ujn.ch9.dao;

import cn.edu.ujn.ch9.dao.IdCard;

public interface IdCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IdCard record);

    int insertSelective(IdCard record);

    IdCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IdCard record);

    int updateByPrimaryKey(IdCard record);
}