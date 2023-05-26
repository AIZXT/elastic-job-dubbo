package com.zhang.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class MyDataJob implements DataflowJob<String> {

    private static final ThreadLocal<Integer> LOOP_COUNTER = new ThreadLocal<>();
    private static final int LOOP_TIMES = 10;
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    @Override
    public List<String> fetchData(ShardingContext shardingContext) {
        Integer current = LOOP_COUNTER.get();
        if(ObjectUtils.isEmpty(current)){
            current = 1;
        }else {
            current += 1;
        }
        LOOP_COUNTER.set(current);

        if(current<LOOP_TIMES){
           return Arrays.asList(shardingContext.getJobName()+current,shardingContext.getShardingParameter());
        }else{
            return null;
        }
    }

    @Override
    public void processData(ShardingContext shardingContext, List<String> data) {
        log.info(data.toString());
    }
}
