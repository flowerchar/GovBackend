package com.apply.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Gov implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("标题名称")
    private String title;

    @ApiModelProperty("新闻链接")
    private String href;

    @ApiModelProperty("发布日期")
    private LocalDateTime date;

    @ApiModelProperty("所属地区")
    private String ref;
}
