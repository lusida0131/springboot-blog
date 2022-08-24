package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { // <user 테이블을 관리하는 레파지토리야, User테이블의 PK는 Integer야>

}
