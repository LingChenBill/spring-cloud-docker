package com.lc.cloud.service;

import com.lc.cloud.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

/**
 * 聚合服务类。
 *
 * @author zyz.
 */
@Service
public class AggregationService {

    @Autowired
    public RestTemplate restTemplate;

    /**
     * 获取用户接口。
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUserById(Long id) {
        // 创建一个被观察者。
        return Observable.create(observer -> {
            // 请求用户微服务的/{id}端点。
            User user = this.restTemplate.getForObject("http://micro-provider-user/{id}", User.class, id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    /**
     * 获取电影接口。
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getMovieUserByUserId(Long id) {
        return Observable.create(observer -> {
            // 请求电影微服务的/user/{id}端点。
            User movieUser= this.restTemplate.getForObject("http://micro-consumer-movie/user/{id}", User.class, id);
            observer.onNext(movieUser);
            observer.onCompleted();
        });
    }

    /**
     * HystrixCommand熔断异常处理。
     *
     * @param id
     * @return
     */
    public User fallback(Long id) {
        User user = new User();
        user.setId(-1l);
        return user;
    }

}
