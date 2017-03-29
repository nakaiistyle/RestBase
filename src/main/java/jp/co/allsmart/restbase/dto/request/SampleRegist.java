package jp.co.allsmart.restbase.dto.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import jp.co.allsmart.restbase.core.validation.SampleValid;

public class SampleRegist {

    @NotNull
    @Range(min=1, max=99999)
    Integer sampleId;

    @SampleValid(min=1, max=32)
    String sampleData;

    /**
     * @return sampleId
     */
    public Integer getSampleId() {
        return sampleId;
    }

    /**
     * @param sampleId セットする sampleId
     */
    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    /**
     * @return sampleData
     */
    public String getSampleData() {
        return sampleData;
    }

    /**
     * @param sampleData セットする sampleData
     */
    public void setSampleData(String sampleData) {
        this.sampleData = sampleData;
    }

}
