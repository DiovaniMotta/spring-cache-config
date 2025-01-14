package br.com.motta.diovani.config.cache.configs;

import br.com.motta.diovani.config.cache.CacheConfiguration;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ConfigurationCacheManager implements ConfigurationComponent<CacheManager> {

    String prefix;
    RedisSerializer<Object> serializer;
    RedisConnectionFactory redisConnectionFactory;

    @Override
    public CacheManager execute() {
        SerializationPair<Object> serializationPair = SerializationPair.fromSerializer(serializer);
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .serializeValuesWith(serializationPair)
                .prefixKeysWith(prefix);

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfig)
                .build();
    }
}
