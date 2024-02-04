package com.study.springbatch.configuration

import com.study.springbatch.configuration.JobConfiguration.Companion.JOB_DOMAIN_STUDY
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
@Profile(JOB_DOMAIN_STUDY)
class JobConfiguration {
  @Bean
  fun job(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Job {
    return JobBuilder(JOB_NAME, jobRepository)
      .start(step01(jobRepository, transactionManager))
      .next(step02(jobRepository, transactionManager))
      .build()
  }

  @Bean
  fun step01(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
    return StepBuilder("Step01", jobRepository)
      .tasklet({ contribution, chunkContext ->
        println("================================")
        println("step01 was executed.")
        println("================================")
        RepeatStatus.FINISHED
      }, transactionManager)
      .build()
  }

  @Bean
  fun step02(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
    return StepBuilder("Step01", jobRepository)
      .tasklet({ contribution, chunkContext ->
        println("================================")
        println("step02 was executed.")
        println("================================")
        RepeatStatus.FINISHED
      }, transactionManager)
      .build()
  }

  companion object {
    const val JOB_NAME = "jobConfiguration"
    const val JOB_DOMAIN_STUDY = "job-domain-study"
  }
}
