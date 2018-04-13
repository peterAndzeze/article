package com.article.recommend.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration
public class RedisConfig {
	private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	@Bean
	@ConfigurationProperties(prefix = "spring.redis.pool")
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		return config;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.redis")
	public JedisConnectionFactory getConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setUsePool(true);
		JedisPoolConfig config = getRedisConfig();
		factory.setPoolConfig(config);
		logger.info("JedisConnectionFactory bean init success.");
		return factory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public RedisTemplate<?, ?> getRedisTemplate() {
		RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(getConnectionFactory());
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		return redisTemplate;
	}

}
