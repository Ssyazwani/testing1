package testtest.surah.list.surahlisttest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import testtest.surah.list.surahlisttest.model.SavedData;



@Configuration
public class AppConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.database}")
    private int redisDatabase;

    @Value("${spring.redis.user}")
    private String redisUser;


    @Value("${spring.redis.password}")
    private String redisPassword;

    @Bean()
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
        config.setDatabase(redisDatabase);
        config.setPassword(redisPassword);
        config.setUsername(redisUser);
        config.setHostName(redisHost);
        config.setPort(redisPort);

        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, SavedData> redisTemplate() {
        RedisTemplate<String, SavedData> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
    
        return template;
    }
}










