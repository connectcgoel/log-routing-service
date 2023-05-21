-- Create the log_routing database
CREATE DATABASE IF NOT EXISTS log_routing;

-- Use the log_routing database
USE log_routing;

-- Create the logs table
CREATE TABLE IF NOT EXISTS logs (
  id BIGINT AUTO_INCREMENT,
  unix_ts BIGINT,
  user_id BIGINT,
  event_name VARCHAR(255),
  PRIMARY KEY (id)
);