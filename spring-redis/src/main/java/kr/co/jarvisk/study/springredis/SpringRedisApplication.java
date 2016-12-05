package kr.co.jarvisk.study.springredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class SpringRedisApplication implements CommandLineRunner {

//    @Autowired
//    CommonUserRepository commonUserRepository;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void run(String... strings) throws Exception {
//        CommonUser commonUser = CommonUser.builder()
//                .id(100L)
//                .username("jarvis.k")
//                .name("Jarvis")
//                .build();
//
//        commonUserRepository.save(commonUser);
//
//        CommonUser user = commonUserRepository.find(100L);
        redisTemplate.opsForValue().set("whereis", "on Java");
    }


    public static final void main(String[] args) {
        SpringApplication.run(SpringRedisApplication.class);
    }
}
