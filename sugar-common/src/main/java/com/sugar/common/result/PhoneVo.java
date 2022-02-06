package com.sugar.common.result;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 方糖
 * @since 2022-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("message_phone")
@ApiModel(value="Phone对象", description="")
public class PhoneVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "短信ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "短信内容")
    private String content;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "发送时间")
    private Date gmtCreate;


}
