package com.study.springbatch.configuration.jobinstance

import com.study.springbatch.configuration.jobinstance.JobInstanceConfiguration.Companion.JOB_INSTANCE_NAME
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.transaction.PlatformTransactionManager

@Configuration
@Profile(JOB_INSTANCE_NAME)
class JobInstanceConfiguration {
  @Bean
  fun jobInstanceJob(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Job {
    return JobBuilder(JOB_INSTANCE_NAME, jobRepository)
      .start(jobInstanceStep01(jobRepository, transactionManager))
      .next(jobInstanceStep02(jobRepository, transactionManager))
      .build()
  }

  @Bean
  fun jobInstanceStep01(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
    return StepBuilder("Step01", jobRepository)
      .tasklet({ contribution, chunkContext ->
        println("================================")
        println("jobInstanceStep01 was executed.")
        println("================================")
        RepeatStatus.FINISHED
      }, transactionManager)
      .build()
  }

  @Bean
  fun jobInstanceStep02(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
    return StepBuilder("Step01", jobRepository)
      .tasklet({ contribution, chunkContext ->
        println("================================")
        println("jobInstanceStep02 was executed.")
        println("================================")
        RepeatStatus.FINISHED
      }, transactionManager)
      .build()
  }

  companion object {
    const val JOB_INSTANCE_NAME = "jobInstanceConfiguration"
  }
}
