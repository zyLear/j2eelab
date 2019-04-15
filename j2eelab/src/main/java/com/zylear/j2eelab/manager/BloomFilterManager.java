package com.zylear.j2eelab.manager;

import orestes.bloomfilter.BloomFilter;
import orestes.bloomfilter.FilterBuilder;
import orestes.bloomfilter.memory.BloomFilterMemory;
import orestes.bloomfilter.redis.BloomFilterRedis;
import orestes.bloomfilter.redis.helper.RedisPool;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by xiezongyu on 2019/4/10.
 */
@Component
public class BloomFilterManager {


//    @PostConstruct
//    public void init() {
//        String host = "172.22.12.12";
//        int port = 6392;
//        String filterName = "normalbloomfilter";
//        BloomFilter<String> bfr = new FilterBuilder(1000, 0.01)
//                .redisBacked(true)
//                .redisHost(host) //Default is localhost
//                .redisPort(port) //Default is standard 6379
//                .buildBloomFilter();
//
//        bfr.add("www");
//        System.out.println(bfr.contains("www"));
//        System.out.println(bfr.contains("dfd"));
//
//
//    }

    public static void main(String[] args) {
        String name = "sentinelxx_redis_bloom_filter";
        FilterBuilder filterBuilder = new FilterBuilder(/*100 **/ 5000 * 10000, 0.0000001)
                .name(name)
                .redisBacked(true)
                .overwriteIfExists(true)
                .pool(RedisPool.sentinelBuilder()
                        .master("sentinel-172.22.12.12-6392")
                        .sentinels(new HashSet<>(Arrays.asList("172.22.12.14:6418", "172.22.12.13:6389", "172.22.12.12:6393")))
                        .redisConnections(1024)
                        .timeout(2000)
                        .build());
        BloomFilterRedis<Object> objectBloomFilterRedis = new BloomFilterRedis<>(filterBuilder);
//        objectBloomFilterRedis.config().name()
//        System.out.println(objectBloomFilterRedis.add("dd"));
//        System.out.println(objectBloomFilterRedis.add("ddd"));
//        System.out.print|ln(objectBloomFilterRedis.contains("22"));
//        System.out.println(objectBloomFilterRedis.add("ddd"));
//        objectBloomFilterRedis.clear();
//        System.out.println(objectBloomFilterRedis.add("ddd"));
        System.out.println(objectBloomFilterRedis.contains("ddd"));
        Jedis jedis = objectBloomFilterRedis.config().pool().getResource();
        long currentTimeMillis = System.currentTimeMillis();
        jedis.setbit(name + ":bits", 15 * 10000 * 10000, true);
        System.out.println("assign span: " + (System.currentTimeMillis() - currentTimeMillis));

//        BloomFilter<Object> objectBloomFilter = new FilterBuilder(1000, 0.01).buildBloomFilter();
//
//        System.out.println(objectBloomFilter.add("dd"));
//        System.out.println(objectBloomFilter.add("ddd"));

        System.out.println("run....");

    }
}
