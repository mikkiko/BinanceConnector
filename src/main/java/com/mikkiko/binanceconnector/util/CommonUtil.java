package com.mikkiko.binanceconnector.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CommonUtil {

    public static String readResourceAsString(String path) throws IOException {
        return IOUtils.resourceToString(path, StandardCharsets.UTF_8);
    }

    public static String readResourceAsString(Resource resource) throws IOException {
        return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    public static void waitFor(Integer timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (InterruptedException e) {
            log.error("Thread was Interrupted", e);
        }
    }
}
