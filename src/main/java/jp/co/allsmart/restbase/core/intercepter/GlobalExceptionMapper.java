package jp.co.allsmart.restbase.core.intercepter;

import javax.annotation.Priority;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jp.co.allsmart.restbase.core.error.Errors;
import jp.co.allsmart.restbase.core.exception.ApplicationException;
import jp.co.allsmart.restbase.dto.response.Error;

/**
 * 共通例外ハンドラ。
 *
 * 発生した例外に応じ、ログ出力/レスポンス返送を行う。
 *
 * @author Nakai
 *
 */
@Provider
@Priority(Integer.MIN_VALUE)
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {


    private static final Logger log = LogManager.getLogger(GlobalExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {

        ApplicationException appEx;

        if(exception instanceof ApplicationException){

            appEx = (ApplicationException)exception;
        }else{
            appEx = new ApplicationException(
                    Errors.UNEXPEXTED_ERROR
                    , exception
                    , exception.getMessage()
                    );
        }


        if(appEx.getCause() != null){
            log.error(appEx.getMessage(), appEx.getCause());
        }else{
            log.error(appEx.getMessage());
        }

        Error resp = new Error();
        resp.setMessage(appEx.getMessage());

        return Response
                .status(Status.fromStatusCode(appEx.getError().getStatus().value()))
                .entity(resp)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

}
