package ru.geekbrains.aprilmarket.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class ExampleTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //    http://www.cronmaker.com/
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("Текущее время {}", dateFormat.format(new Date()));
    }
}
