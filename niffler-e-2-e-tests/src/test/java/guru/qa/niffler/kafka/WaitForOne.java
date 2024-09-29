package guru.qa.niffler.kafka;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class WaitForOne<K, V> {

    private static final long MAX_WAIT_TIME = 5000L;
    private final Map<K, CompletableFuture<V>> store = new ConcurrentHashMap<>();

    public void provide(K key, V value) {
        CompletableFuture<V> future = store.get(key);
        if (future != null) {
            future.complete(value);
        }
    }

    public V wait(K key) throws InterruptedException {
        CompletableFuture<V> future = store.computeIfAbsent(key, k -> new CompletableFuture<>());
        try {
            return future.get(MAX_WAIT_TIME, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            future.completeExceptionally(e);
            return null;
        }
    }
}