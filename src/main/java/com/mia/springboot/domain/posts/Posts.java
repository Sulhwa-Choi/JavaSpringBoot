package com.mia.springboot.domain.posts;

import com.mia.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    /***
     * Posts 클래스는 실제 DB의 테이블과 매칭될 클래스로 보통 Entity 클래스라고도 함.
     * 실제쿼리를 날리기보다 이 Entity 클래스의 수정을 통해 작업가능
     */

    // @Entity : 테이블과 링크될 클래스임을 나타냄
    // @Id : 해당 테이블의 PK필드
    // @GeneratedValue(strategy = GenerationType.IDENTITY) : PK의 생성규칙 --> auto_increment
    // @Column : 테이블의 컬럼을 나타내며 굳이 선언하지 않아도 해당 클래스의 필드는 모두 컬럼이 됨.
    //           사용이유? 기본값 외에 추가로 변경이 필요한 옵션이 있는 경우
    //           문자열의 경우 VARCHAR(255)가 기본값인데 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경원하는 경우 사용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
