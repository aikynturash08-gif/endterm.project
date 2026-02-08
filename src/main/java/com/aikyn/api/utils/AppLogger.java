package com.aikyn.api.utils;

import java.time.Instant;

public final class AppLogger {
  private static volatile AppLogger instance;

  private AppLogger() {}

  public static AppLogger getInstance() {
    AppLogger local = instance;
    if (local == null) {
      synchronized (AppLogger.class) {
        local = instance;
        if (local == null) {
          instance = local = new AppLogger();
        }
      }
    }
    return local;
  }

  public void info(String msg) {
    System.out.println("[INFO] " + Instant.now() + " - " + msg);
  }

  public void warn(String msg) {
    System.out.println("[WARN] " + Instant.now() + " - " + msg);
  }

  public void error(String msg) {
    System.err.println("[ERROR] " + Instant.now() + " - " + msg);
  }
}
