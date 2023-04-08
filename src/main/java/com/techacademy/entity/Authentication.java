package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")

public class Authentication {

    public static enum Role {
        管理者, 一般
    }
    /** 社員番号 */
    @Id

    @NotEmpty
    @Column(length = 20, nullable = false)
    @Length(max=20,message="20文字以内で入力してください")
    private String code;

    /** パスワード */
    @Column(length = 255, nullable = false)
    @Length(min=12,max=255,message="パスワードは12文字以上255文字以内で入力してください")
    private String password;

    /** 列挙型（文字列） */
    @Column(name = "role",length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    /** 従業員ID */
    @OneToOne
    @JoinColumn(name="employee_id",nullable = false, referencedColumnName="id")


    private Employee employee;




}