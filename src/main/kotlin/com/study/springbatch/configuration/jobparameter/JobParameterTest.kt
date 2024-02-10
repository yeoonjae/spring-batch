package com.study.springbatch.configuration.jobparameter

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class JobParameterTest(
  private val jobLauncher: JobLauncher,
  private val job: Job,
) : ApplicationRunner {
  override fun run(args: ApplicationArguments?) {
    /**
     * JobParameter 를 만드는 방식이 2개가 있음
     * 1. JobParameterBuilder 를 이용
     * 2. jar 실행 시 인자로 넘겨주기
     *
     * 아래의 방법은 1번의 방법이다.
     */

    val jobParameter = JobParametersBuilder()
      .addString("name", "user1")
      .addLong("seq", 2L)
      .addDate("date", Date())
      .addDouble("age", 16.5)
      .toJobParameters()

    jobLauncher.run(job, jobParameter)
  }
}
