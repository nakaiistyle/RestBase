package jp.co.allsmart.restbase.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import jp.co.allsmart.restbase.entity.SampleEntity;

@Mapper
public interface SampleMapper {

    @Select("SELECT sample_id,sample_data FROM sample")
    List<SampleEntity> selectAll();

    SampleEntity selectById(Integer id);

    void regist(SampleEntity entity);
}
