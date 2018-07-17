package com.hizhu.crawler.brand.exception;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/4 10:33
 */
public class CrawlerServerException extends RuntimeException {
    private static final long serialVersionUID = 1427069295488572389L;

    private int errorCode;

    public CrawlerServerException() {
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public CrawlerServerException(int errorCode, String message) {
        super("错误编码：" + errorCode +  " || 错误信息 " +  message);

    }

//    public CrawlerServerException(String name, String message) {
//        this(999, name, message);
//    }

    public CrawlerServerException(String message) {
        super(message);
    }

    public CrawlerServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CrawlerServerException(Throwable cause) {
        super(cause);
    }
}
