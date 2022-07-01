package org.iproute.windows11.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.iproute.windows11.Utils.CmdUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ComputerController
 *
 * @author zhuzhenjie
 * @since 2022/7/1
 */
@RestController
@Slf4j
public class ComputerController implements CommandLineRunner {
    private ReentrantLock restartLock = new ReentrantLock(true);
    private String uuid;

    @GetMapping("/sayHello")
    public String sayHello() {
        return "hello world";
    }

    /**
     * Unlock with uuid string.
     *
     * @return the string
     */
    @PostMapping("/uuid")
    public String unlockWithUuid() {
        restartLock.lock();
        try {
            return this.uuid;
        } catch (Exception e) {
            return "get uuid failed";
        } finally {
            restartLock.unlock();
        }
    }

    @PostMapping("/computer/restart")
    @SuppressWarnings("all")
    public String restart(HttpServletRequest request) {
        String id = request.getHeader("uuid");
        if (StringUtils.isBlank(id) || uuid.equals(id)) {
            return "restart failed";
        }
        new Thread(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
            }

            CmdUtils.restartComputer();
        }).start();

        return "restart success";
    }

    @Override
    public void run(String... args) throws Exception {
        restartLock.lock();
        try {
            this.uuid = UUID.randomUUID().toString();
            log.info("init uuid :{}", uuid);
        } finally {
            restartLock.unlock();
        }
    }

    @Scheduled(fixedRate = 1000L * 30)
    public void refreshId() {
        restartLock.lock();
        try {
            this.uuid = UUID.randomUUID().toString();
            log.info("refresh uuid : {}", this.uuid);
        } finally {
            restartLock.unlock();
        }
    }


}
