package joh.faust;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/multiple")
public class MultipleCacheApi {

    @Cacheable(cacheNames = {"cache_1", "cache_2"})
    @GetMapping("/")
    public String getHello() {
        return "Twelfth Service: Multiple Cache! Call time: " + LocalDateTime.now() + " CACHEABLE";
    }

    @CachePut(cacheNames = {"cache_1"})
    @GetMapping("/put1")
    public String getPutMessage() {
        return "Twelfth Service: Multiple Cache! Call time: " + LocalDateTime.now() + " PUT1";
    }

    @CacheEvict(cacheNames = {"cache_1"})
    @GetMapping("/evict1")
    public String getRemovalMessage() {
        return "Twelfth Service: Multiple Cache! Call time: " + LocalDateTime.now() + " EVICT_1";
    }

    @CachePut(cacheNames = {"cache_2"})
    @GetMapping("/put2")
    public String getPutMessage2() {
        return "Twelfth Service: Multiple Cache! Call time: " + LocalDateTime.now() + " PUT2";
    }

    @CacheEvict(cacheNames = {"cache_2"})
    @GetMapping("/evict2")
    public String getRemovalMessage2() {
        return "Twelfth Service: Multiple Cache! Call time: " + LocalDateTime.now() + " EVICT_2";
    }
}
