package com.example.parking_manager_system.Pojo;

import com.example.parking_manager_system.ModelView.OptionLogResponseViewModel;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 管理员的操作日志
 * @author SWQXDBA
 */
@Data
@Entity
public class OptionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    AdminUser adminUser;


    String data;


    @CreationTimestamp
    Timestamp createTime;
    @UpdateTimestamp
    Timestamp updateTime;
    public OptionLogResponseViewModel getViewModel(){
        OptionLogResponseViewModel model = new OptionLogResponseViewModel();
        model.adminUser = adminUser.getUserName();
        model.createTime = createTime;
        model.data = data;
        return model;
    }
}
