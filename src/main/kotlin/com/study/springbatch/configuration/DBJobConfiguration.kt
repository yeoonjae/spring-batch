//package com.study.springbatch.configuration
//
//import org.springframework.batch.core.Job
//import org.springframework.batch.core.Step
//import org.springframework.batch.core.job.builder.JobBuilder
//import org.springframework.batch.core.repository.JobRepository
//import org.springframework.batch.core.step.builder.StepBuilder
//import org.springframework.batch.repeat.RepeatStatus
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.transaction.PlatformTransactionManager
//
//@Configuration
////@Profile(DB_CONFIGURATION_JOB)
//class DBJobConfiguration {
//
//  @Bean
//  fun job(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Job {
//    return JobBuilder("dbConfigJob", jobRepository)
//      .start(step1(jobRepository, transactionManager))
//      .next(step2(jobRepository, transactionManager))
//      .build()
//  }
//
//  @Bean
//  fun step1(
//    jobRepository: JobRepository,
//    transactionManager: PlatformTransactionManager
//  ): Step {
//    return StepBuilder("step01", jobRepository)
//      .tasklet({ contribution, chunkContext ->
//        println("================================")
//        println("step01 tasklet start.")
//        println("================================")
//        RepeatStatus.FINISHED
//      }, transactionManager)// or .chunk(chunkSize, transactionManager)
//      .build()
//  }
//
//  @Bean
//  fun step2(
//    jobRepository: JobRepository,
//    transactionManager: PlatformTransactionManager
//  ): Step {
//    return StepBuilder("step02", jobRepository)
//      .tasklet({ contribution, chunkContext ->
//        println("================================")
//        println("step02 tasklet start.")
//        println("================================")
//        RepeatStatus.FINISHED
//      }, transactionManager)// or .chunk(chunkSize, transactionManager)
//      .build()
//  }
//
//  companion object {
//    const val DB_CONFIGURATION_JOB = "db-job-configuration"
//  }
//}
