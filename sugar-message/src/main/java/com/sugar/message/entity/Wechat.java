package com.sugar.message.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("message_wechat")
@ApiModel(value="Wechat对象", description="")
public class Wechat implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "推送ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "群组ID")
    private String groupid;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "通知内容")
    private String content;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "发送时间")
    private Date gmtCreate;


}
