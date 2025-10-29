package com.aizen.ApiGateway.route;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class InventoryServiceRoutes {

    @Bean
    public RouterFunction<ServerResponse> inventoryRoutes() {
        return GatewayRouterFunctions.route("inventory-service")
                .route(RequestPredicates.path("/api/v1/inventory/event/{eventId}"),
                        request -> forwardWithPathVariables(request, "eventId",
                                "http://127.0.0.1:8081/api/v1/inventory/event/"))
                .route(RequestPredicates.path("/api/v1/inventory/venue/{venueId}"),
                        request -> forwardWithPathVariables(request, "venueId",
                                "http://127.0.0.1:8081/api/v1/inventory/venue/"))
                .build();
    }

    private ServerResponse forwardWithPathVariables(ServerRequest request,
                                                    String variable,
                                                    String url) throws Exception {
        String pathVariable = request.pathVariable(variable);
        return HandlerFunctions.http(url + pathVariable).handle(request);
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceApiDocs() {
        return GatewayRouterFunctions.route("inventory-service-api-docs")
                // ✅ correct path matcher
                .route(RequestPredicates.path("/docs/inventoryservice/**"),
                        HandlerFunctions.http("http://localhost:8081"))
                .filter(setPath("/v3/api-docs"))
                .build();
    }
}
