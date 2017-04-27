package com.guigarage.shell;

import com.canoo.dolphin.server.spring.DolphinPlatformApplication;
import org.springframework.boot.SpringApplication;

@DolphinPlatformApplication
public class ShellServer {

    //Start with the following VM parameters "--permit-illegal-access --add-modules java.xml.bind"

    public static void main(String[] args) {
        SpringApplication.run(ShellServer.class, args);
    }

}
