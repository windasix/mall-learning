package com.macro.mall.tiny.dto;

import com.macro.mall.tiny.annotation.FlagValidator;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shixingjie
 * @Date 2021/5/12 9:26
 */
public class PmsBrandParam {

    @ApiModelProperty(value = "是否进行显示")
    @FlagValidator(value = {"0","1"}, message = "显示状态不正确")
    private Integer showStatus;

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }
}
