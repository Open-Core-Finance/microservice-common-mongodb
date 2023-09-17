package tech.corefinance.common.mongodb.support.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.corefinance.common.dto.BasicUserDto;

import java.util.Date;

@Data
@Document("userrole")
public class RoleTest {

    @Id
    private String id;
    private String name;

    @CreatedDate
    private Date createDate;
    @CreatedBy
    private BasicUserDto creator;
    @LastModifiedBy
    private BasicUserDto lastModifiedBy;
}
