package b_jdkpackage;

import com.google.common.util.concurrent.RateLimiter;

public class RaterLimiterDemo implements Runnable{

    // 每秒只能处理2个请求
    static RateLimiter rateLimiter=RateLimiter.create(2);

    @Override
    public void run() {
        System.out.println(System.currentTimeMillis());
    }

    public static void main(String[] args) {

        for (int i = 0; i < 50; i++) {

            rateLimiter.acquire();
            new Thread(new RaterLimiterDemo()).start();

        }

    }
}
