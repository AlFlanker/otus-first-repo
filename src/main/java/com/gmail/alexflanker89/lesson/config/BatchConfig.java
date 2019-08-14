package com.gmail.alexflanker89.lesson.config;

import com.gmail.alexflanker89.lesson.domain.auth.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.jdbc.core.JdbcOperations;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
@RequiredArgsConstructor
@Slf4j
@EnableBatchProcessing
@Configuration
public class BatchConfig {

    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;
    public final DataSource dataSource;

    @Bean
    public MongoItemReader<User> mongoReader(MongoOperations mongoOperations){
        return new MongoItemReaderBuilder<User>()
                .template(mongoOperations)
                .name("mongoUserItemReader")
                .jsonQuery("{}")
                .targetType(User.class)
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public ItemProcessor processor() {
        return (ItemProcessor<User, User>) person -> person;
    }

    @Bean
    public ItemWriter<User> writer(JdbcOperations jdbcOperations){
        return new H2Writer(jdbcOperations);
    }


    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory
                .get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }

    @SuppressWarnings("unchecked")
    @Bean
    public Step step1(ItemWriter<User> writer, MongoItemReader reader, ItemProcessor itemProcessor) {
        return stepBuilderFactory.get("step1")
                .chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .listener(new ItemReadListener() {
                    public void beforeRead() { log.info("Начало чтения"); }
                    public void afterRead(Object o) { log.info("Конец чтения"); }
                    public void onReadError(Exception e) { log.info("Ошибка чтения"); }
                })
                .listener(new ItemWriteListener() {
                    public void beforeWrite(List list) { log.info("Начало записи"); }
                    public void afterWrite(List list) { log.info("Конец записи"); }
                    public void onWriteError(Exception e, List list) { log.info("Ошибка записи"); }
                })
                .listener(new ItemProcessListener() {
                    public void beforeProcess(Object o) {log.info("Начало обработки");}
                    public void afterProcess(Object o, Object o2) {log.info("Конец обработки");}
                    public void onProcessError(Object o, Exception e) {log.info("Ошбка обработки");}
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {log.info("Начало пачки");}
                    public void afterChunk(ChunkContext chunkContext) {log.info("Конец пачки");}
                    public void afterChunkError(ChunkContext chunkContext) {log.info("Ошибка пачки");}
                })
                .build();
    }
}
