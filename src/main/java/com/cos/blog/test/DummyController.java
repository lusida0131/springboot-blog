package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@Api(tags = {"더미Test API"})
public class DummyController {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PutMapping("/dummy/user/{id}")
    @ApiOperation(value = "dummy 회원 update")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json data 받으려면 @RequestBody 써야된다.
        System.out.println("id = " + id);
        System.out.println("requestUser password = " + requestUser.getPassword());
        System.out.println("requestUser email = " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        requestUser.setPassword(requestUser.getPassword());
        requestUser.setEmail(requestUser.getEmail());
        //userRepository.save(requestUser); // save 해당 id가 없으면 insert하고, 있으면 update한다. transaction 어노테이션 붙이면 save없어도 update된다.

        //더티 체킹
        return null;
    }
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지당 2건의 데이터 리턴 받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });
        // 요청 : 웹브라우저
        // user 객체 = 자바 오브젝트
        // 변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson라이브러리)
        // 스프링부트 = MessageConverter라는 애가 응답시에 자동으로 작동.
        // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던진다.
        return user;
    }
    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("username = " + user.getUsername());
        System.out.println("password = " + user.getPassword());
        System.out.println("email = " + user.getEmail());
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }
}
