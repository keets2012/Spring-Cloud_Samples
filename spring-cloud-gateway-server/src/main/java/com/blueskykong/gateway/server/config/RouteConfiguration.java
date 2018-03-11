package com.blueskykong.gateway.server.config;

import com.blueskykong.gateway.server.AdditionalRoutes;
import com.blueskykong.gateway.server.ThrottleGatewayFilterFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.concurrent.TimeUnit;


/**
 * Created by keets on 2018/2/11.
 */
@Configuration
@Import(AdditionalRoutes.class)
public class RouteConfiguration {

    @Bean
    public RouteDefinitionLocator discoveryClientRouteDefinitionLocator(DiscoveryClient discoveryClient) {
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, ThrottleGatewayFilterFactory throttle) {
        //@formatter:off
        return builder.routes()
                .route(r -> r.host("**.abc.org").and().path("/image/png")
                        .filters(f ->
                                f.addResponseHeader("X-TestHeader", "foobar"))
                        .uri("http://httpbin.org:80")
                )
                .route(r -> r.path("/image/webp")
                        .filters(f ->
                                f.addResponseHeader("X-AnotherHeader", "baz"))
                        .uri("http://httpbin.org:80")
                )
                .route(r -> r.order(-1)
                        .host("**.throttle.org").and().path("/get")
                        .filters(f -> f.filter(throttle.apply(1,
                                1,
                                10,
                                TimeUnit.SECONDS)))
                        .uri("http://httpbin.org:80")
                )
                .build();
        //@formatter:on
    }

    @Bean
    public ThrottleGatewayFilterFactory throttleWebFilterFactory() {
        return new ThrottleGatewayFilterFactory();
    }

    @Bean
    public RouterFunction<ServerResponse> testFunRouterFunction() {
        RouterFunction<ServerResponse> route = RouterFunctions.route(
                RequestPredicates.path("/testfun"),
                request -> ServerResponse.ok().body(BodyInserters.fromObject("hello")));
        return route;
    }

}
