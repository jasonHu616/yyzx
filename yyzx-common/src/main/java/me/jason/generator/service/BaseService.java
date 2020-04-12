package me.jason.generator.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务层代码自定义生成: 重写Context,ConfigurationParser,MyBatisGeneratorConfigurationParser, 增加服务层生成逻辑
 * @author Jason
 * @param <T>
 * @param <Example>
 * @param <ID>
 */
public interface BaseService<T, Example, ID> {

    long countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(ID id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(Example example);
    
    /**
     * return T object
     * @author Jason
     * @date 2020年4月8日 14:15:12
     * @param example
     * @return
     */
    T selectByCondition(Example example);
    /**
     * if pageInfo == null
     * then return result of selectByExample(example)
     * @author Jason
     * @date 2020年4月8日 14:16:30
     * @param example
     * @return
     */
    List<T> selectByPageExample(Example example);

    T selectByPrimaryKey(ID id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") Example example);

    int updateByExample(@Param("record") T record, @Param("example") Example example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}