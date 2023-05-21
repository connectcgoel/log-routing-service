package com.fable.log_routing.service;

import com.fable.log_routing.entity.Log;
import com.fable.log_routing.repository.LogRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LogService {

    @Autowired
    private LogRepository logRepository;

    private final List<Log> testLogs = new ArrayList<>();

    @Value("${log.buffer.path}")
    private String bufferFilePath;

    @Value("${log.buffer.size.limit}")
    private long bufferLimit;

    private final List<Log> logBuffer = new ArrayList<>();
    private long currentBufferSize = 0;

    public void saveLog(Log log) {
        long logSize = calculateLogSize(log);

        if (currentBufferSize + logSize >= bufferLimit) {
            flushBufferToDatabase();
        }

        logBuffer.add(log);
        currentBufferSize += logSize;

        // Add log to testLogs for testing
        testLogs.add(log);
    }

    private void flushBufferToDatabase() {
        if (!logBuffer.isEmpty()) {
            try {
                String logsJson = convertLogsToJson(logBuffer);
                FileUtils.writeStringToFile(new File(bufferFilePath), logsJson, StandardCharsets.UTF_8, true);
                logRepository.saveAll(logBuffer);
                logBuffer.clear();
                currentBufferSize = 0;
            } catch (IOException e) {
                System.err.println("Failed to flush buffer to database: " + e.getMessage());
            }
        }
    }

    private long calculateLogSize(Log log) {
        // Calculate the size of the log in bytes
        String logJson = convertLogToJson(log);
        return logJson.getBytes(StandardCharsets.UTF_8).length;
    }

    private String convertLogsToJson(List<Log> logs) {
        // Convert List<Log> to JSON string
        // You can use any JSON library of your choice
        // Example: Jackson ObjectMapper
        // Note: Below is just a placeholder implementation using a simple StringBuilder
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < logs.size(); i++) {
            Log log = logs.get(i);
            String logJson = convertLogToJson(log);
            jsonBuilder.append(logJson);
            if (i < logs.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    private String convertLogToJson(Log log) {
        // Convert Log to JSON string
        // You can use any JSON library of your choice
        // Example: Jackson ObjectMapper
        // Note: Below is just a placeholder implementation using a simple StringBuilder
        return "{" +
                "\"id\": " + log.getId() + "," +
                "\"unix_ts\": " + log.getUnixTs() + "," +
                "\"user_id\": " + log.getUserId() + "," +
                "\"event_name\": \"" + log.getEventName() + "\"" +
                "}";
    }

    public List<Log> getTestLogs() {
        return new ArrayList<>(testLogs);
    }

    public void resetTestLogs() {
        testLogs.clear();
    }
}