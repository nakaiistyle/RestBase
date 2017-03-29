package jp.co.allsmart.restbase.dto.response;

import java.util.List;

import jp.co.allsmart.restbase.entity.SampleEntity;

public class SampleGetAll {

    List<SampleEntity> result;

    /**
     * @return result
     */
    public List<SampleEntity> getResult() {
        return result;
    }

    /**
     * @param result セットする result
     */
    public void setResult(List<SampleEntity> result) {
        this.result = result;
    }


}
