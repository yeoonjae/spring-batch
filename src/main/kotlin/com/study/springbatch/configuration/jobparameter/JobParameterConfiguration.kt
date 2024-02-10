package com.study.springbatch.configuration.jobparameter

import com.study.springbatch.configuration.jobparameter.JobParameterConfiguration.Companion.JOB_PARAMETER_NAME
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
@Profile(JOB_PARAMETER_NAME)
class JobParameterConfiguration {
  @Bean
  fun jobInstanceJob(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Job {
    return JobBuilder(JOB_PARAMETER_NAME, jobRepository)
      .start(jobInstanceStep01(jobRepository, transactionManager))
      .next(jobInstanceStep02(jobRepository, transactionManager))
      .build()
  }

  @Bean
  fun jobInstanceStep01(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
    return StepBuilder("Step01", jobRepository)
      .tasklet({ contribution, _ ->

        val jobParameters = contribution.stepExecution.jobParameters.parameters
        jobParameters["name"]
        jobParameters["seq"]
        jobParameters["date"]
        jobParameters["age"]

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

        val jobParameters = contribution.stepExecution.jobParameters.parameters
        jobParameters["name"]
        jobParameters["seq"]
        jobParameters["date"]
        jobParameters["age"]

        val chunkParameters  = chunkContext.stepContext.jobParameters
        chunkParameters["name"]
        chunkParameters["seq"]
        chunkParameters["date"]
        chunkParameters["age"]

        println("================================")
        println("jobInstanceStep02 was executed.")
        println("================================")
        RepeatStatus.FINISHED
      }, transactionManager)
      .build()
  }

  companion object {
    const val JOB_PARAMETER_NAME = "JobParameterConfiguration"
  }
}
