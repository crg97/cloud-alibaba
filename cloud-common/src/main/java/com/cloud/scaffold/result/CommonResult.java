package com.cloud.scaffold.result;

import com.cloud.scaffold.enums.ResponseEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResult {

  private int code;

  private String message;

  public static CommonResult success() {
    return buildResultByEnum(ResponseEnum.SUCCESS);
  }

  public static CommonResult unknownError() {
    return buildResultByEnum(ResponseEnum.UNKNOWN_ERROR);
  }

  public static CommonResult buildResultByEnum(ResponseEnum responseEnum) {
    return CommonResult.builder().code(responseEnum.getCode())
        .message(responseEnum.getMessage()).build();
  }

}
