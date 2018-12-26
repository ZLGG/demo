package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.edas.configcenter.config.ConfigService;
import com.example.demo.vo.DataSourceVo;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.io.IOException;

@Configuration
public class dataSourceConfig {
    @Resource
    private Environment environment;
    //@Bean(name = "dataSource",destroyMethod = "close")
    public DruidDataSource dataSource() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String group = environment.getProperty("dtyunxi.service.group");
        String value = ConfigService.getConfig("yundt.smartsales.mall.appmgmt.datasourcevo", group, 3000L);
        DataSourceVo dataSourceVo = objectMapper.readValue(value, DataSourceVo.class);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceVo.getDriverClassName());
        dataSource.setUrl(dataSourceVo.getJdbcUrl());
        dataSource.setUsername(dataSourceVo.getJdbcUserName());
        dataSource.setPassword(dataSourceVo.getJdbcUserPassword());
        dataSource.setMaxActive(dataSourceVo.getMaxActive());
        dataSource.setValidationQuery(dataSourceVo.getValidationQuery());
        dataSource.setInitialSize(dataSourceVo.getInitialSize());
        dataSource.setMinIdle(dataSourceVo.getMinIdle());
        dataSource.setMaxWait(dataSourceVo.getMaxWait());
        return dataSource;
    }

}
