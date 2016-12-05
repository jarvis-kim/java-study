package kr.co.jarvisk.study.springredis.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommonUserRepository {

    private static final String REDIS_KEY_PREFIX = "commonUser-";

    @Autowired
    private RedisTemplate<String, CommonUser> redisTemplate;

    private static String key(Long id) {
        return REDIS_KEY_PREFIX + id;
    }

    public void save(CommonUser user) {
        redisTemplate.opsForValue().set(key(user.getId()), user);
    }

    public CommonUser find(Long id) {
        return redisTemplate.opsForValue().get(id);
    }

    public void delete(Long id) {
        redisTemplate.delete(key(id));
    }
}
