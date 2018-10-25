package cn.mangowork.core.exception;

/**
 * 注解的异常
 *
 * @author
 * @create 2018-06-15 18:38
 **/

public class NoSuchAnnotation extends Exception {

    public NoSuchAnnotation() {
    }

    public NoSuchAnnotation(String message) {
        super( "Annotation Exception:" + message);
    }

    public NoSuchAnnotation(String message, Throwable cause) {
        super( "Annotation Exception:" + message, cause);
    }

    public NoSuchAnnotation(Throwable cause) {
        super(cause);
    }

    public NoSuchAnnotation(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super( "Annotation Exception:" + message, cause, enableSuppression, writableStackTrace);
    }

}
