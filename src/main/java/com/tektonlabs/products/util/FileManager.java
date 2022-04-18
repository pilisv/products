package com.tektonlabs.products.util;

import com.tektonlabs.products.dto.FileInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class FileManager {

    private final static String COLUMN_SEPARATOR = "|";
    private static final String HEADER_METHOD = "METHOD";
    private static final String HEADER_START = "START TIME (ms)";
    private static final String HEADER_END = "END TIME (ms)";
    private static final String HEADER_DURATION = "DURATION (ms)";

    public static boolean createDirectory(File directory) {
        if (!directory.exists())
            return directory.mkdirs();
        return true;
    }

    public static void createHeaders(String fileUrl, FileWriter writer) {
        var file = new File(fileUrl);
        if (file.length() == 0) {
            log.debug("File is empty ...");

            StringBuilder row = new StringBuilder();
            row.append(StringUtils.leftPad(HEADER_METHOD, 15, " "));
            row.append(COLUMN_SEPARATOR);
            row.append(StringUtils.leftPad(HEADER_START, 20, " "));
            row.append(COLUMN_SEPARATOR);
            row.append(StringUtils.leftPad(HEADER_END, 20, " "));
            row.append(COLUMN_SEPARATOR);
            row.append(StringUtils.leftPad(HEADER_DURATION, 20, " "));
            row.append('\n');
            try {
                writer.write(row.toString());
            } catch (IOException e) {
                log.error("Error on file writting:{}", fileUrl, e);
            }
        }
    }

    public static StringBuilder buildRow(FileInfoDTO fileInfoDTO) {
        StringBuilder row = new StringBuilder();
        row.append(StringUtils.leftPad(fileInfoDTO.getMethod(), 15, " "));
        row.append(COLUMN_SEPARATOR);
        row.append(StringUtils.leftPad(fileInfoDTO.getStartTime().toString(), 20, " "));
        row.append(COLUMN_SEPARATOR);
        row.append(StringUtils.leftPad(fileInfoDTO.getEndTime().toString(), 20, " "));
        row.append(COLUMN_SEPARATOR);
        row.append(StringUtils.leftPad(fileInfoDTO.getDuration().toString(), 20, " "));
        row.append('\n');
        return row;
    }
}
