package com.tektonlabs.products.aop;

import com.tektonlabs.products.dto.FileInfoDTO;
import com.tektonlabs.products.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class Advice {

    private final FileService fileService;

    @Around("@annotation(com.tektonlabs.products.util.ExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        Long endtime = System.currentTimeMillis();

        log.info("Class Name: " + point.getSignature()
                .getDeclaringTypeName() + ". Method Name: " + point.getSignature()
                .getName() + ". Time taken for Execution is : " + (endtime - startTime) + "ms");

        fileService.addRegister(FileInfoDTO.builder()
                .method(point.getSignature().getName())
                .startTime(startTime)
                .endTime(endtime)
                .duration(endtime - startTime)
                .build());

        return object;
    }
}

