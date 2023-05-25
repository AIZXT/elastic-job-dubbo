package com.zhang;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.zhang.job.MyJob;
import com.zhang.properties.MyJobProperties;
import com.zhang.properties.ZookeeperProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ZookeeperProperties.class, MyJobProperties.class})
public class ProviderAutoConfiguration {

    @Autowired
    private ZookeeperProperties zookeeperProperties;

    @Autowired
    private MyJobProperties myJobProperties;

    @Autowired
    private MyElasticJobListener myElasticJobListener;

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter(){
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(zookeeperProperties.getServerList(), zookeeperProperties.getNamespace()));
    }

    @Bean
    public MyJob myJob(){
        return new MyJob();
    }

    @Bean(initMethod = "init")
    public JobScheduler jobScheduler(MyJob myJob){
        return new SpringJobScheduler(myJob,zookeeperRegistryCenter(),getLiteJobConfiguration(),myElasticJobListener);
    }

    private LiteJobConfiguration getLiteJobConfiguration() {
        // 定义作业核心配置
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("myJob", myJobProperties.getCron(), myJobProperties.getShardingTotalCount()).
                shardingItemParameters(myJobProperties.getShardingItemParameters()).jobParameter(myJobProperties.getJobParameters()).build();
        // 定义SIMPLE类型配置
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, MyJob.class.getCanonicalName());
        // 定义Lite作业根配置
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();
        return simpleJobRootConfig;

    }
}
