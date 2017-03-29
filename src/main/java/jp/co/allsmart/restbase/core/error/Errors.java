package jp.co.allsmart.restbase.core.error;

import org.springframework.http.HttpStatus;

public enum Errors implements HttpErrors{

    /** 想定外エラー。 */
    UNEXPEXTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"想定外のエラーが発生しました。：{0}")
    /** データなしエラー。 */
    , NOT_FOUND(HttpStatus.NOT_FOUND, "データがありません。")
    ;


    protected HttpStatus status;
    protected String message;

    Errors(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
