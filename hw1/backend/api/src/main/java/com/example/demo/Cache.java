package com.example.demo;

import java.util.HashMap;
import java.util.zip.CRC32;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Cache {
    private static Logger logger = LogManager.getLogger(Cache.class);
    public HashMap<Long, String> cache = new HashMap<>();
    private int hits, misses, requests = 0;

    public Cache() {
    }

    public int getHits() {
        return this.hits;
    }

    private void updateHits() {
        this.hits = hits + 1;
    }

    public int getMisses() {
        return this.misses;
    }

    private void updateMisses() {
        this.misses = misses + 1;
    }

    public int getRequests() {
        return this.requests;
    }

    private void updateRequests() {
        this.requests = requests + 1;
    }

    public void store(long hash, String response) {
        cache.put(hash, response);
        this.freeFromCache(hash, 12);
        logger.info("Stored new item {} in cache", hash);
    }

    public boolean contains(long hash) {
        return cache.containsKey(hash);
    }

    public String retrieve(long hash) {
        this.updateRequests();

        if (cache.containsKey(hash)) {
            this.updateHits();
            logger.info("Retrieved item {} from cache. requests: {} hits: {}", hash, this.getRequests(), this.getHits());
            
            return cache.get(hash);
        } else {
            this.updateMisses();
            logger.info("Failed to retrieve {} from cache (not cached). requests: {} misses: {}", hash, this.getRequests(), this.getHits());
            
            return null;
        }
    }

    public void clearCache() {
        cache.clear();
    }

    private void freeFromCache(long hash, int time) {
        int timeInMs = time * 60 * 1000; // min to ms

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        cache.remove(hash);
                        logger.info("Removed item {} from cache.", hash);
                    }
                },
                timeInMs);
    }

    public long generateHash(String data){
        CRC32 fileCRC32 = new CRC32();
        fileCRC32.update(data.getBytes());
        return fileCRC32.getValue();
    }
}
