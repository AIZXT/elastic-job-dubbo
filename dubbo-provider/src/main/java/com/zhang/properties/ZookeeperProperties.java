package com.zhang.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "elastic.job.zk")
public class ZookeeperProperties {

    private String serverList;

    private String namespace;
}
