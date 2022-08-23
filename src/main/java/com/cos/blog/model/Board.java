package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count;

    @ManyToOne // 연관관계 Board가 Many User가 one
    @JoinColumn(name = "userId") // 실제로 database에 만들어질 때는 userid로 만들어진다.
    private User user; // 데이터베이스는 object를 저장하지 않지만 JPA FK는 object 저장 가능

    @CreationTimestamp
    private Timestamp createDate;
}
