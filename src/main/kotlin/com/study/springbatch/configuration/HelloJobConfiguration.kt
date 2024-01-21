package com.study.springbatch.configuration

import com.study.springbatch.configuration.HelloJobConfiguration.Companion.HELLO_SPRING_BATCH
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.transaction.PlatformTransactionManager

@Profile(HELLO_SPRING_BATCH)
@Configuration
class HelloJobConfiguration {

  // 1. Job 구동 -> Step 실행 -> Tasklet 실행
  @Bean
  fun myJob(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Job {
    return JobBuilder("myJob", jobRepository)
      .start(myStep(jobRepository, transactionManager))
      .build()
  }

  @Bean
  fun myStep(
    jobRepository: JobRepository,
    transactionManager: PlatformTransactionManager
  ): Step {
    return StepBuilder("myStep", jobRepository)
      .tasklet(MyTasklet(), transactionManager)// or .chunk(chunkSize, transactionManager)
      .build()
  }

  companion object {
    const val HELLO_SPRING_BATCH = "hello-spring-batch"
  }
}

class MyTasklet : Tasklet {
  // Tasklet : Step 안에서 단일 태스크로 수행되는 로직
  override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
    println("================================")
    println("MyTasklet start.")
    println("================================")
    return RepeatStatus.FINISHED
  }
}
