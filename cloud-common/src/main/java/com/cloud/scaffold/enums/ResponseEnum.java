package com.cloud.scaffold.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseEnum {

  SUCCESS(200, "成功"),
  UNAUTHORIZED(401, "无效的token或token已过期"),
  FORBIDDEN(403, "无权限访问"),
  UNKNOWN_ERROR(000000, "未知错误");

  private int code;

  private String message;

}
