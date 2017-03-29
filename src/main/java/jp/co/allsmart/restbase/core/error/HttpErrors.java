package jp.co.allsmart.restbase.core.error;

import org.springframework.http.HttpStatus;

public interface HttpErrors {

    HttpStatus getStatus();

    String getMessage();

    String name();

}
