package com.example.last.util;

public class Result {
    private int code;
    private String msg;
    private char access;
    private Result(int code, String msg, char perm){
        this.code=code;
        this.msg=msg;
        this.access=perm;
    }


    public static Result Success(String msg,char perm){
        System.out.println(perm+msg);
        return new Result(200,msg,perm);
    }
    public static Result Failure(String msg){
        return new Result(400,msg,' ');
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public char getAccess() {
        return access;
    }

    public void setAccess(char access) {
        this.access = access;
    }
}
