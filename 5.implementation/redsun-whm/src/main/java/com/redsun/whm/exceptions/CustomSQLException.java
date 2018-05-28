package com.redsun.whm.exceptions;

import org.springframework.http.HttpStatus;

/**
 * This class use for sql exception
 * Created by HauLL on 2/22/2017.
 */
public class CustomSQLException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6792607847023554262L;
	private HttpStatus code;
    private Object data;

    public CustomSQLException(final Object data) {
        this.data = data;
    }

    public CustomSQLException(final HttpStatus code, final Object data) {
        this.code = code;
        this.data = data;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(final HttpStatus code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(final Object data) {
        this.data = data;
    }
}
