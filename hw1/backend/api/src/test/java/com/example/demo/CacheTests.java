package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.concurrent.TimeUnit;

public class CacheTests {
    private Cache cache;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @BeforeEach
    void setUp() {
        cache = new Cache();
    }

    @AfterEach
    void tearDown() {
        cache.clearCache();
    }

    @Test
    void testCacheInitialState() {
        assertEquals(0, cache.size());
        assertEquals(0, cache.getRequests());
        assertEquals(0, cache.getHits());
        assertEquals(0, cache.getMisses());
    }

    @Test
    void testGenerateHash() {
        String data = "hello123";
        long hash = cache.generateHash(data);

        // Assert hash result is always the same
        assertEquals(hash, cache.generateHash(data));
    }

    @Test
    void testCacheStoreAndRetrieve() {
        String data;
        long hash;

        // store n values in cache
        int n = getRandomNumber(2, 10);
        for (int i = 0; i < n; i++) {
            data = "hello" + i;
            hash = cache.generateHash(data);
            cache.store(hash, data);

            // assert if cache contains hash and if retrieves correct data
            assertTrue(cache.contains(hash));
            assertEquals(data, cache.retrieve(hash));
        }

        assertEquals(n, cache.size());
    }

    @Test
    void testUpdateCacheStats() {
        int n;
        long hash;
        String data;

        // store n values in cache
        n = getRandomNumber(2, 10);
        for (int i = 0; i < n; i++) {
            data = "hello" + i;
            hash = cache.generateHash(data);
            cache.store(hash, data);
        }

        // Try to retrieve n values present in cache and n values not in cache
        for (int i = 0; i < n; i++) {
            cache.retrieve((long) i); // n misses
            cache.retrieve(cache.generateHash("hello" + i)); // n hits
        }

        assertEquals(n, cache.getMisses());
        assertEquals(n, cache.getHits());
        assertEquals(2 * n, cache.getRequests());

    }

    @Test
    void testClearCache() {
        int n;
        long hash;
        String data;

        // store n values in cache
        n = getRandomNumber(2, 10);
        for (int i = 0; i < n; i++) {
            data = "hello" + i;
            hash = cache.generateHash(data);
            cache.store(hash, data);
        }

        cache.clearCache();
        assertEquals(0, cache.size());
    }

    @Test
    void testFreeFromCache() throws InterruptedException {
        int n;
        long hash;
        String data;

        // store n values in cache
        n = getRandomNumber(2, 10);
        for (int i = 0; i < n; i++) {
            data = "hello" + i;
            hash = cache.generateHash(data);
            cache.store(hash, data);
            cache.freeFromCache(hash, 0); // wait 0 minutes to free cache
        }

        TimeUnit.SECONDS.sleep(1);
        assertEquals(0, cache.size());
    }
}
