package jp.co.allsmart.restbase.dto.response;

import jp.co.allsmart.restbase.entity.SampleEntity;

public class SampleGet {

    SampleEntity result;

    /**
     * @return result
     */
    public SampleEntity getResult() {
        return result;
    }

    /**
     * @param result セットする result
     */
    public void setResult(SampleEntity result) {
        this.result = result;
    }

}
