package com.sumant.learning.messaging.xrateconsumer.scsxrateconsumer;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static org.h2.tools.Server.createWebServer;

@Component
@Slf4j
public class H2ServerManual {

    private Server webServer;
    private boolean serverStarted;

    @Value("${h2-server.port}")
    Integer h2ConsolePort;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        if ( ! serverStarted ) {
            log.info("starting h2 console at port " + h2ConsolePort);
            this.webServer = createWebServer("-webPort", h2ConsolePort.toString(),
                    "-tcpAllowOthers").start();
            System.out.println(webServer.getURL());
            serverStarted = true;
        }
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        log.info("stopping h2 console at port "+h2ConsolePort);
        if ( webServer != null ) {
            this.webServer.stop();
        }
    }
}
