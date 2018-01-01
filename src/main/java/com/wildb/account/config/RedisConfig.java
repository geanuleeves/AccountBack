package com.wildb.account.config;

import com.wildb.account.caches.CacheNames;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 支持Redis缓存
 *
 * @author 郑邦振
 *
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{


    /**
     *   key序列化使用StringRedisSerializer, 不配置的话key前面会出现乱码。
         value序列化使用 GenericJackson2JsonRedisSerializer ，保存为Json格式。该类目前反序列化还有一些bug，只能反序列化实现了Serialize的类。
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate template = new RedisTemplate();

        template.setConnectionFactory(factory);

        RedisSerializer keySerializer = new StringRedisSerializer();

        // 设置key序列化类，否则key前面会多了一些乱码
        template.setKeySerializer(keySerializer);
        template.setHashKeySerializer(keySerializer);

        // FIXME 有些版本有bug，没有序列化的只能序列化，但无法反序列化
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();

        // 设置内容序列化类
        template.setValueSerializer(jsonSerializer);

        template.afterPropertiesSet();

        return template;
    }


    /**
     * 配置 CacheManager，包括指定缓存和默认缓存的超时时间的配置。
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

        Map<String, Long> expires = new HashMap<>();

        expires.put(CacheNames.CONFIG, 60L);

        // 设置超时
        cacheManager.setExpires(expires);

        // 没有设置的缓存默认过期时间
        cacheManager.setDefaultExpiration(60 * 60);

        return cacheManager;
    }

    /**
     * 重写 keyGenerator，可以支持使用@Cacheable不指定Key。
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();

                sb.append(target.getClass().getSimpleName());
                sb.append('.').append(method.getName());

                // FIXME 参数太长的时候请指定key属性，否则key太长
                for (Object obj : params) {
                    if (obj != null) {
                        sb.append(obj.toString());
                    }
                }

                return sb.toString();
            }
        };
    }
}
