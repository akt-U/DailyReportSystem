package com.techacademy.entity;
import java.sql.Clob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne; // 追加
import javax.persistence.PreRemove; // 追加
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length; // 追加
import javax.persistence.CascadeType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional; // 追加

import lombok.Data;

@Data
@Entity
@Table(name = "report")
public class Report {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 日報の日付null不許可 */
    @Column(nullable = false)
    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;

    /** タイトル255桁。null不許可 */
    @Column(length = 255, nullable = false)
    @NotEmpty
    @Length(max=255,message="255文字以内で入力してください")
    private String title;

    /** 内容null不許可 */
    @Column(nullable = false)
    @Type(type="text")
    private String content;


    /** 従業員ID */
    @ManyToOne
    @JoinColumn(name="employee_id",nullable = false, referencedColumnName="id")
    private Employee employee;


    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;



}
