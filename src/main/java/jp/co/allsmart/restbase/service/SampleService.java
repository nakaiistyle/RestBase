package jp.co.allsmart.restbase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.allsmart.restbase.core.error.Errors;
import jp.co.allsmart.restbase.core.exception.ApplicationException;
import jp.co.allsmart.restbase.dto.request.SampleRegist;
import jp.co.allsmart.restbase.dto.response.SampleGet;
import jp.co.allsmart.restbase.dto.response.SampleGetAll;
import jp.co.allsmart.restbase.entity.SampleEntity;
import jp.co.allsmart.restbase.repository.SampleMapper;

@Service
@Transactional(readOnly = true)
public class SampleService {

    @Autowired
    SampleMapper sampleMapper;

    public SampleGetAll getAll() {

        SampleGetAll resp = new SampleGetAll();

        List<SampleEntity> entities = sampleMapper.selectAll();

        resp.setResult(entities);

        return resp;
    }

    public SampleGet getById(Integer id) {

        SampleGet resp = new SampleGet();

        SampleEntity entity = sampleMapper.selectById(id);

        if(entity == null){

            throw new ApplicationException(Errors.NOT_FOUND);
        }

        resp.setResult(entity);

        return resp;
    }

    @Transactional(readOnly = false)
    public void regist(SampleRegist req) {

        SampleEntity entity = new SampleEntity();
        entity.setSampleId(req.getSampleId());
        entity.setSampleData(req.getSampleData());

        sampleMapper.regist(entity);

        if(req.getSampleData().equals("exception")){
            throw new ApplicationException(Errors.UNEXPEXTED_ERROR, "rollback test");
        }
    }

}
