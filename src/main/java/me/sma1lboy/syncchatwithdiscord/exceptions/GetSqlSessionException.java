package me.sma1lboy.syncchatwithdiscord.exceptions;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/8/21
 */
public class GetSqlSessionException extends RuntimeException{
    public GetSqlSessionException() {
    }

    public GetSqlSessionException(String s) {
        super(s);
    }
}
