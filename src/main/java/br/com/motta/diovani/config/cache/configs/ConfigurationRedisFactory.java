package br.com.motta.diovani.config.cache.configs;

import br.com.motta.diovani.config.cache.CacheConfiguration;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.util.StringUtils;

@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
class ConfigurationRedisFactory implements ConfigurationComponent<RedisConnectionFactory> {

    CacheConfiguration configuration;

    @Override
    public RedisConnectionFactory execute() {
        var host = configuration.getHost();
        var port = configuration.getPort();
        var password = configuration.getPassword();
        var redisConfiguration = new RedisStandaloneConfiguration(host, port);
        if(StringUtils.hasText(password)) {
            redisConfiguration.setPassword(password);
        }
        var connectionFactory = new LettuceConnectionFactory(redisConfiguration);
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }
}
