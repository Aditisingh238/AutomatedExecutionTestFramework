package com.myways.automatedtestexecutionframework.util;

import java.nio.file.Path;

public class ScreenshotUtil {
    public static Path takeScreenshotPlaceholder(Path dir, String name) {
        return dir.resolve(name + ".png");
    }
}
