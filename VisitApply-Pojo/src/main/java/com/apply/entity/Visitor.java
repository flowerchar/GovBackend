package com.apply.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("访问人实体类")
public class Visitor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "到访部门")
    private String visitDepartment;

    @ApiModelProperty("审核人工号")
    private String auditLaborId;

    @ApiModelProperty("校内对接人")
    private String campusContactPerson;

    @ApiModelProperty("访客姓名")
    private String visitorName;

    @ApiModelProperty("证件号码")
    private String cardNumber;

    @ApiModelProperty("访客电话")
    private Integer visitorPhone;

    @ApiModelProperty("访客单位")
    private String visitorCompany;

    @ApiModelProperty("车牌号")
    private String carNumber;

    @ApiModelProperty("到访日期")
    private LocalDate visitDate;

    @ApiModelProperty("到访事由")
    private String visitReason;
}
