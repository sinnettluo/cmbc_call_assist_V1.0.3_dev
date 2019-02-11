package com.guiji.dispatch.algorithm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.guiji.dispatch.util.LoadProperties;

import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;

@Configuration
public class ShardingDataSourceConfig {

	@Value("${jdbc_driver0}")
	private String jdbc_driver0;
	@Value("${jdbc_url0}")
	private String jdbc_url0;
	@Value("${jdbc_username0}")
	private String jdbc_username0;
	@Value("${jdbc_password0}")
	private String jdbc_password0;

	@Bean
	public DataSource getShardingDataSource() throws SQLException {
		ShardingConfiguration shardingConfiguration = new ShardingConfiguration();
		shardingConfiguration.setDataSourceMap(createDataSourceMap());
		shardingConfiguration.setMasterSlaveRuleConfigurations(new ArrayList<>());
		shardingConfiguration.setTableRuleConfigurations(tableRuleConfigurations());
		DataSource shardingDataSource = shardingConfiguration.createShardingDataSource();
		return shardingDataSource;
	}

	public Map<String, DataSource> createDataSourceMap() {
		DataSource dataSource = getDataSource();
		Map<String, DataSource> map = new HashMap<>();
		map.put("guiyu_dispatch", dataSource);
		return map;
	}

	public DataSource getDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		// dataSource.setUrl(LoadProperties.getProperty("jdbc_url0"));
		// dataSource.setUsername(LoadProperties.getProperty("jdbc_username0"));
		// dataSource.setPassword(LoadProperties.getProperty("jdbc_password0"));
		// dataSource.setDriverClassName("com.mysql.jdbc.Driver");

		dataSource.setUrl(jdbc_url0);
		dataSource.setUsername(jdbc_username0);
		dataSource.setPassword(jdbc_password0);
		dataSource.setDriverClassName(jdbc_driver0);
		
		//修改配置
		dataSource.setMinIdle(5);
		dataSource.setInitialSize(5);
		dataSource.setMaxActive(20);
		return dataSource;
	}

	/**
	 * 配置表规则
	 * 
	 * @return
	 */
	private List<TableRuleConfiguration> tableRuleConfigurations() {
		List<TableRuleConfiguration> list = new ArrayList<>();
		
		TableRuleConfiguration result = new TableRuleConfiguration();
		result.setLogicTable("dispatch_plan");
		result.setActualDataNodes(
				"guiyu_dispatch.dispatch_plan_0,guiyu_dispatch.dispatch_plan_1,guiyu_dispatch.dispatch_plan_2");
		result.setTableShardingStrategyConfig(
				new StandardShardingStrategyConfiguration("phone", new PreciseSharding(), new RangeSharding()));
		
		
		TableRuleConfiguration result1 = new TableRuleConfiguration();
		result1.setLogicTable("dispatch_lines");
		result1.setActualDataNodes(
				"guiyu_dispatch.dispatch_lines_0,guiyu_dispatch.dispatch_lines_1,guiyu_dispatch.dispatch_lines_2");
		result1.setTableShardingStrategyConfig(
				new StandardShardingStrategyConfiguration("line_id", new PreciseShardingLines(), new RangeSharding()));
		list.add(result);
		
		list.add(result1);
		return list;
	}

}
