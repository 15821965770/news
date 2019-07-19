package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Long id;
    @NotBlank(message = "商品名称不能为空")
    private String name;

    @NotNull(message = "商品价格不能为空")
    private BigDecimal price;

    @NotNull(message = "商品生产日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;

    @NotBlank(message = "商品描述不能为空")
    private String description;
    private String pic;
    private Date created;

}
