package com.zql.app_ji.Bean;

/**
 * 为EventBus创建的消息实体
 */
public class MessageEvent {
    public final static int RECYCLERVIEW_UP=0;
    public final static int RECYCLERVIEW_DOWN=1;
    public final static int RECYCLERVIEW_TOP=2;
    public final static int RECYCLERVIEW_BOTTOM=3;
    public final static int NIGHTCHAGE=4;


    private int messagetype;
    private String message;
    public MessageEvent(int messagetype){
        this.messagetype=messagetype;
    }
    public MessageEvent(int messagetype,String message){
        this.messagetype=messagetype;
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
    public int getMessagetype(){
        return this.messagetype;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessagetype(int messagetype) {
        this.messagetype = messagetype;
    }
}
