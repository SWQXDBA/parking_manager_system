package com.example.parking_manager_system.Pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * 管理员
 * @author SWQXDBA
 */
@Data
@Entity
@ApiModel(value ="管理员")
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "唯一标识id")
    Long id;
    @ApiModelProperty(value = "用户名")
    String userName;
    @ApiModelProperty(value = "密码")
    String password;

    @ApiModelProperty(value = "他的操作日志")
    @OneToMany
    Set<OptionLog> optionLogs;


    @CreationTimestamp
    Timestamp createTime;
    @UpdateTimestamp
    Timestamp updateTime;
}
