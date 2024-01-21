package com.study.springbatch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
// @EnableBatchProcessing
// spring boot 3 에서는 @EnableBatchProcessing 어노테이션 사용이 불필요하여 제거가 필요하다.
// https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide#spring-batch-changes
class SpringBatchApplication

fun main(args: Array<String>) {
  runApplication<SpringBatchApplication>(*args)
}
