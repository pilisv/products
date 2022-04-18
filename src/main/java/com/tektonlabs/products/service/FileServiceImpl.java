package com.tektonlabs.products.service;

import com.tektonlabs.products.config.FileConfig;
import com.tektonlabs.products.dto.FileInfoDTO;
import com.tektonlabs.products.util.FileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService{

    private final FileConfig fileConfig;

    @Override
    public void addRegister(FileInfoDTO fileInfoDTO){

        File directory = new File(fileConfig.getPath());
        FileManager.createDirectory(directory);

        String url = fileConfig.getPath() + File.separator + fileConfig.getName();

        try (FileWriter writer = new FileWriter(url, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)){

            FileManager.createHeaders(url, writer);
            var row = FileManager.buildRow(fileInfoDTO);

            try {
                bufferedWriter.write(row.toString());
            } catch (IOException e) {
                log.error("Error on file writting:{}", fileConfig.getName(), e);
            }

        } catch (IOException e) {
            log.error("Error on file writting:{}", fileConfig.getName(), e);
        }
    }

}
