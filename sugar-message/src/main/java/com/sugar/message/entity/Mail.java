package com.sugar.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("message_mail")
@ApiModel(value="Mail对象", description="")
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "邮件ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "邮箱地址")
    private String mail;

    @ApiModelProperty(value = "邮件标题")
    private String title;

    @ApiModelProperty(value = "邮件内容")
    private String content;

    @ApiModelProperty(value = "抄送地址")
    private String people;

    @ApiModelProperty(value = "发送时间")
    private Date gmtCreate;


}
