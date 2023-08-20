package com.mia.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/***
 * DB Layer 접근자 (DAO의 역할)
 * 단순 인터페이스를 생성 후, JpaRepository<Entity 클래스, Pk타입 > 을 상속하면 기본적인 CRUD 메소드가 자동으로 생성
 * 반드시 기본 Entity 클래스와 기본 Entity Repository는 함께 위치해야 함. Entity 클래스는 기본 Repository 없이 제대로 역할을 X
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
