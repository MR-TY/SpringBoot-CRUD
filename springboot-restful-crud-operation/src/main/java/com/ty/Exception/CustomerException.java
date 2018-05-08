package com.ty.Exception;

/**
 * Created by Administrator on 2018/5/8.
 */
public class CustomerException extends RuntimeException {
  public CustomerException(){
      super("用户不存在");
  }
}
