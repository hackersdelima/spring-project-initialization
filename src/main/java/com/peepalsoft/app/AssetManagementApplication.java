package com.peepalsoft.app;

import com.peepalsoft.app.component.InitialDataComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EntityScan(basePackages = "com.peepalsoft.app")
public class AssetManagementApplication extends SpringBootServletInitializer {
    private InitialDataComponent initialDataComponent;

    @Autowired
    public void setInitialDataComponent(InitialDataComponent initialDataComponent) {
        this.initialDataComponent = initialDataComponent;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AssetManagementApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(AssetManagementApplication.class, args);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        initialDataComponent.saveInitialData();
    }


}
