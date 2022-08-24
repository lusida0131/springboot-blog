package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER) // 연관관계 Board가 Many User가 one
    @JoinColumn(name = "userId") // 실제로 database에 만들어질 때는 userid로 만들어진다.
    private User user; // 데이터베이스는 object를 저장하지 않지만 JPA FK는 object 저장 가능

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy(필드이름)가 적혀있으면 연관관계의 주인이 아니다.(난 FK가 아니에요) DB에 칼럼을 만들지 마세요
    private List<Reply> reply;                              // EAGER과 LAZY 차이 EAGER은 바로 가져온다 LAZY는 어떤 이벤트가 발생했을 때 가져온다.

    @CreationTimestamp
    private Timestamp createDate;
}
