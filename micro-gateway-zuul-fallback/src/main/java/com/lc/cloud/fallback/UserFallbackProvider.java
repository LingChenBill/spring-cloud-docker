package com.lc.cloud.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * zuul 回退类。
 *
 * @author zyz.
 */
@Component
public class UserFallbackProvider implements ZuulFallbackProvider {

    @Override
    public String getRoute() {
        // 声明是为哪个微服务提供回退。
        return "micro-provider-user";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                // fallback时的状态码。
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                // 数字类型的状态码，返回的是200.
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                // 状态文本，返回的是OK
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("用户微服务不可用，请稍后再试。".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {

                // headers设定。
                HttpHeaders headers = new HttpHeaders();
                MediaType mediaType = new MediaType("application", "json",
                        Charset.forName("UTF-8"));
                headers.setContentType(mediaType);

                return headers;
            }
        };
    }
}
