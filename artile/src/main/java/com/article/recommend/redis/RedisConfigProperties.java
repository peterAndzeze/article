//package com.article.recommend.redis;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.stereotype.Component;
//
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//@Configuration
//@ConfigurationProperties(prefix = "redis")
//@PropertySource(value="classpath:redis.properties")
//public class RedisConfigProperties {
//	private String host;
//	private Integer port;
//	private String password;
//	//redis【超时时间】
//	private Integer timeout;
//	//redis【是否使用连接池】
//	private Boolean usePool;
//	//redis【最大连接数】
//	private Integer maxTotal;
//	//redis【最大空闲连接数】
//	private Integer maxIdle;
//	//redis【每次释放连接的最大数目】
//	private Integer numTestsPerEvictionRun;
//	//redis【释放连接的扫描间隔（毫秒）】
//	private Long timeBetweenEvictionRunsMillis;
//	//redis【连接最小空闲时间】
//	private Long minEvictableIdleTimeMillis;
//	//redis【 连接空闲多久后释放, 当空闲时间>该值 且 空闲连接>最大空闲连接数 时直接释放】
//	private Long softMinEvictableIdleTimeMillis;
//	//redis【获取连接时的最大等待毫秒数,小于零:阻塞不确定的时间,默认-1】
//	private Long maxWaitMillis;
//	//redis【在获取连接的时候检查有效性, 默认false】
//	private Boolean testOnBorrow;
//	//redis【在空闲时检查有效性, 默认false】
//	private Boolean testWhileIdle;
//	//redis【连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true】
//	private Boolean blockWhenExhausted;
//	private Integer db ;
//	public String getHost() {
//		return host;
//	}
//	public void setHost(String host) {
//		this.host = host;
//	}
//	public Integer getPort() {
//		return port;
//	}
//	public void setPort(Integer port) {
//		this.port = port;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public Integer getTimeout() {
//		return timeout;
//	}
//	public void setTimeout(Integer timeout) {
//		this.timeout = timeout;
//	}
//	public Boolean getUsePool() {
//		return usePool;
//	}
//	public void setUsePool(Boolean usePool) {
//		this.usePool = usePool;
//	}
//	public int getMaxTotal() {
//		return maxTotal;
//	}
//	public void setMaxTotal(Integer maxTotal) {
//		this.maxTotal = maxTotal;
//	}
//	public int getMaxIdle() {
//		return maxIdle;
//	}
//	public void setMaxIdle(Integer maxIdle) {
//		this.maxIdle = maxIdle;
//	}
//	public Integer getNumTestsPerEvictionRun() {
//		return numTestsPerEvictionRun;
//	}
//	public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
//		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
//	}
//	public Long getTimeBetweenEvictionRunsMillis() {
//		return timeBetweenEvictionRunsMillis;
//	}
//	public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
//		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
//	}
//	public Long getMinEvictableIdleTimeMillis() {
//		return minEvictableIdleTimeMillis;
//	}
//	public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
//		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
//	}
//	public Long getSoftMinEvictableIdleTimeMillis() {
//		return softMinEvictableIdleTimeMillis;
//	}
//	public void setSoftMinEvictableIdleTimeMillis(Long softMinEvictableIdleTimeMillis) {
//		this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
//	}
//	public Long getMaxWaitMillis() {
//		return maxWaitMillis;
//	}
//	public void setMaxWaitMillis(Long maxWaitMillis) {
//		this.maxWaitMillis = maxWaitMillis;
//	}
//	public Boolean getTestOnBorrow() {
//		return testOnBorrow;
//	}
//	public void setTestOnBorrow(Boolean testOnBorrow) {
//		this.testOnBorrow = testOnBorrow;
//	}
//	public Boolean getTestWhileIdle() {
//		return testWhileIdle;
//	}
//	public void setTestWhileIdle(Boolean testWhileIdle) {
//		this.testWhileIdle = testWhileIdle;
//	}
//	public boolean getBlockWhenExhausted() {
//		return blockWhenExhausted;
//	}
//	public void setBlockWhenExhausted(Boolean blockWhenExhausted) {
//		this.blockWhenExhausted = blockWhenExhausted;
//	}
//	public Integer getDb() {
//		return db;
//	}
//	public void setDb(Integer db) {
//		this.db = db;
//	}
//	
//	
//	@Bean("jedisPoolConfig")
//	public JedisPoolConfig getJedisPoolConfig() {
//		JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
//		jedisPoolConfig.setBlockWhenExhausted(getBlockWhenExhausted());
//		jedisPoolConfig.setMaxIdle(getMaxIdle());
//		jedisPoolConfig.setMaxTotal(getMaxTotal());
//		jedisPoolConfig.setMaxWaitMillis(getMaxWaitMillis());
//		jedisPoolConfig.setMinEvictableIdleTimeMillis(getMinEvictableIdleTimeMillis());
//		jedisPoolConfig.setNumTestsPerEvictionRun(getNumTestsPerEvictionRun());
//		jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(getSoftMinEvictableIdleTimeMillis());
//		jedisPoolConfig.setTestOnBorrow(getTestOnBorrow());
//		jedisPoolConfig.setTestWhileIdle(getTestWhileIdle());
//		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(getTimeBetweenEvictionRunsMillis());
//		return jedisPoolConfig;
//	}
//	
//	@Bean("jedisConnectionFactory")
//	public JedisConnectionFactory jedisConnectionFactory(@Qualifier("jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {
//		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
//		jedisConnectionFactory.setDatabase(getDb());
//		jedisConnectionFactory.setHostName(getHost());
//		jedisConnectionFactory.setPort(getPort());
//		jedisConnectionFactory.setUsePool(getUsePool());
//		jedisConnectionFactory.setTimeout(getTimeout());
//		return jedisConnectionFactory;
//	}
//	
//	
//	
//	@Bean
//	public JedisPool getJedisPool(@Qualifier("jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {
//		JedisPool jedisPool=new JedisPool(jedisPoolConfig, getHost(), getPort(), getTimeout(),getPassword(),getDb());
//		//jedisPool.initPool(jedisPoolConfig, factory);
//		return jedisPool;
//	}
//
//	@Bean
//	public StringRedisSerializer stringRedisSerializer() {
//		return new StringRedisSerializer();
//	}
//	
//	
//	  //主动配置RedisTemplate<String, String>而不是RedisTemplate<Serializable, Serializable>
//    //RedisTemplate<String, String>自动设置的以下Serializer都是StringRedisSerializer，
//    // 如果此处配置的bean是RedisTemplate<Serializable, Serializable>，在使用的地方@Autowired，以下Serializer都是StringRedisSerializer。
//    //如果不配置RedisConfig，@Autowired，以下Serializer都是StringRedisSerializer。
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory){
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        return redisTemplate;
//    }
//	
//	
//}
