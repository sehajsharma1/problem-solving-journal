package com.ocean.problemsolvingjournal.systemdesign.parkinglot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.enumeration.VehicleSize;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.util.ParkingLotUtil;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.util.Set;

@Configuration
public class RedisStartupConfig {

    @EventListener(ApplicationReadyEvent.class)
    public void loadDataOnStartup(ApplicationReadyEvent event) {
        @SuppressWarnings("unchecked")
        RedisTemplate<String, Object> redisTemplate =
                (RedisTemplate<String, Object>) event.getApplicationContext()
                        .getBean("redisTemplate");

        // 🔹 Remove any ticket* keys
        deleteTicketKeys(redisTemplate);

        // 🔹 Known keys only (safe deletion)
        deleteFloorKeys(redisTemplate, 0);
        deleteFloorKeys(redisTemplate, 1);

        // 🔹 Load floor 0
        loadSpots(redisTemplate, 0, VehicleSize.SMALL, "A", 0, 10);
        loadSpots(redisTemplate, 0, VehicleSize.MEDIUM, "B", 0, 50);

        // 🔹 Load floor 1
        loadSpots(redisTemplate, 1, VehicleSize.SMALL, "A", 0, 10);
        loadSpots(redisTemplate, 1, VehicleSize.MEDIUM, "B", 0, 50);

        System.out.println("Redis parking floor data refreshed");
    }

    private void loadSpots(RedisTemplate<String, Object> redisTemplate,
                           int floorNumber, VehicleSize size,
                           String prefix, int start, int end) {

        String key = ParkingLotUtil.buildKey(floorNumber, size);

        for (int i = start; i <= end; i++) {
            redisTemplate.opsForSet().add(key, prefix + i);
        }
    }

    private void deleteFloorKeys(RedisTemplate<String, Object> redisTemplate, int floorNumber) {
        for (VehicleSize size : VehicleSize.values()) {
            redisTemplate.delete(ParkingLotUtil.buildKey(floorNumber, size));
        }
    }

    // New method: delete all keys matching pattern "ticket*"
    private void deleteTicketKeys(RedisTemplate<String, Object> redisTemplate) {
        Set<String> keys = redisTemplate.keys("ticket*");
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // 🔑 Key as String
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        GenericJackson2JsonRedisSerializer serializer =
                new GenericJackson2JsonRedisSerializer(objectMapper);
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }
}
