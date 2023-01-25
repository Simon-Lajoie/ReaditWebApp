package com.example.readitjavaproject;

import com.example.readitjavaproject.domain.User;
import com.example.readitjavaproject.repository.IUserRepository;
import com.example.readitjavaproject.service.DTO.FriendDTO;
import com.example.readitjavaproject.service.DTO.PostDTO;
import com.example.readitjavaproject.service.DTO.UserDTO;
import com.example.readitjavaproject.service.impl.FriendService;
import com.example.readitjavaproject.service.impl.PostService;
import com.example.readitjavaproject.service.impl.UserService;
import com.example.readitjavaproject.service.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ReaditJavaProjectApplication implements CommandLineRunner{

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    FriendService friendService;

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    public ReaditJavaProjectApplication(IUserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReaditJavaProjectApplication.class, args);

    }
    @Transactional
    @Override
    public void run(String... args) throws Exception{

        /* TESTING...
        final String encryptPwd = new BCryptPasswordEncoder().encode("admin2");
        UserDTO user = new UserDTO();
        user.setUsername("admin2");
        user.setPassword(encryptPwd);
        userService.createUser(user);
        PostDTO postDTO = new PostDTO();
        postDTO.setPublicationDate(LocalDate.now());
        postDTO.setTitle("TEST POST");
        postDTO.setDescription("BLABLABLA");
        User userToAdd = userRepository.findByUsername("admin").stream().findFirst().orElse(null);
        postDTO.setUser(userMapper.toDto(userToAdd));
        postDTO.setLink("link");
        postService.createPost(postDTO);
        List<PostDTO> posts = postService.getPostByUserId(1);
        System.out.println("TESTING");
        posts.forEach(System.out::println);
        FriendDTO friend = new FriendDTO();
        friend.setOne_user_id(userService.getUserByUsername("admin").orElseThrow());
        friend.setTwo_user_id(userService.getUserByUsername("admin2").orElseThrow());
        friendService.createFriend(friend);*/
    }

}
