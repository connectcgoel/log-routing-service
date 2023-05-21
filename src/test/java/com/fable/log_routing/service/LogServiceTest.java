package com.fable.log_routing.service;

import com.fable.log_routing.entity.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @BeforeEach
    public void setUp() {
        // Clear the test logs before each test
        logService.resetTestLogs();
    }

    @Test
    public void testFireMultipleRequests() {
        int requestCount = 100_000;
        List<Log> testLogs = new ArrayList<>();

        for (int i = 0; i < requestCount; i++) {
            Log log = createDummyLog();
            testLogs.add(log);
            logService.saveLog(log);
        }

        List<Log> savedLogs = logService.getTestLogs();
        assertThat(savedLogs).containsExactlyElementsOf(testLogs);
    }

    private Log createDummyLog() {
        Random random = new Random();
        long id = random.nextLong();
        long unixTs = random.nextLong();
        long userId = random.nextLong();
        String eventName = generateRandomEventName();

        return new Log(id, unixTs, userId, eventName);
    }

    private String generateRandomEventName() {
        String[] eventNames = {"login", "logout"};
        Random random = new Random();
        int index = random.nextInt(eventNames.length);
        return eventNames[index];
    }

}