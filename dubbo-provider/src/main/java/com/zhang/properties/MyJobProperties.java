package com.zhang.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "elastic.job.myjob")
public class MyJobProperties {

    private String cron;

    private int shardingTotalCount;

    private String shardingItemParameters;

    private String jobParameters;
}
