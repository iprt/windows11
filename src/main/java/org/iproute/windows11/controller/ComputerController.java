package org.iproute.windows11.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.iproute.windows11.utils.CmdUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ComputerController
 *
 * @author zhuzhenjie
 * @since 2022/7/1
 */
@RestController
@Slf4j
public class ComputerController {
    private final ConcurrentHashMap<String, String> uuidMap = new ConcurrentHashMap<>(1);

    /**
     * Unlock with uuid string.
     *
     * @return the string
     */
    @PostMapping("/uuid")
    public String unlockWithUuid() {
        return uuidMap.get("uuid");
    }

    /**
     * Restart string.
     *
     * @param req the req
     * @return the string
     */
    @PostMapping("/computer/restart")
    @SuppressWarnings("all")
    public String restart(@RequestBody Req req) {
        String uuid = req.getUuid();
        if (StringUtils.isBlank(uuid) || !StringUtils.equals(uuid, uuidMap.get("uuid"))) {
            return "restart failed";
        }
        new Thread(() -> {
            try {
                Thread.sleep(5 * 1000);
                log.info("restart computer");
            } catch (InterruptedException e) {
            }

            CmdUtils.restart();
        }).start();
        return "restart success";
    }

    @PostMapping("/computer/shutdown")
    @SuppressWarnings("all")
    public String shutdown(@RequestBody Req req) {
        String uuid = req.getUuid();
        if (StringUtils.isBlank(uuid) || !StringUtils.equals(uuid, uuidMap.get("uuid"))) {
            return "shutdown failed";
        }
        new Thread(() -> {
            try {
                Thread.sleep(5 * 1000);
                log.info("shutdown computer");
            } catch (InterruptedException e) {
            }
            CmdUtils.shutdown();
        }).start();
        return "shutdown success";
    }

    @Scheduled(fixedRate = 1000L * 30)
    public void refreshId() {
        uuidMap.put("uuid", UUID.randomUUID().toString());
    }

}
