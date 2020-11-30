package com.cloud.scaffold.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ScaffoldException extends RuntimeException{

  private int code;

  private String message;

  public ScaffoldException(int code, String message) {
    super(message);
    this.code = code;
    this.message = message;
  }

}
