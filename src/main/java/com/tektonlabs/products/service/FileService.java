package com.tektonlabs.products.service;

import com.tektonlabs.products.dto.FileInfoDTO;
import org.springframework.scheduling.annotation.Async;

public interface FileService {
    @Async
    void addRegister(FileInfoDTO fileInfoDTO);
}
