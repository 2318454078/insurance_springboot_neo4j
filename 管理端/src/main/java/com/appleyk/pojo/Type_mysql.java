package com.appleyk.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "type")
public class Type_mysql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int type_id;
    private String type_name;
}
