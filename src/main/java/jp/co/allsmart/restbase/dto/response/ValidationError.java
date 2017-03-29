package jp.co.allsmart.restbase.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {

    private List<Detail> errorParam = new ArrayList<>();

    /**
     * @return errorParam
     */
    public List<Detail> getErrorParam() {
        return errorParam;
    }

    /**
     * @param errorParam セットする errorParam
     */
    public void setErrorParam(List<Detail> errorParam) {
        this.errorParam = errorParam;
    }

    public static class Detail {

        private String param;
        private String message;
        /**
         * @return param
         */
        public String getParam() {
            return param;
        }
        /**
         * @param param セットする param
         */
        public void setParam(String param) {
            this.param = param;
        }
        /**
         * @return message
         */
        public String getMessage() {
            return message;
        }
        /**
         * @param message セットする message
         */
        public void setMessage(String message) {
            this.message = message;
        }
    }

}
