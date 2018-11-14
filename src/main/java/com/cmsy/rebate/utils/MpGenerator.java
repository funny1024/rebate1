package com.cmsy.rebate.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.stereotype.Component;

/**
 * mybatis-plus 代码生成器
 */
@Component
public class MpGenerator {

    public static void main(String[] args) {

        AutoGenerator generator = new AutoGenerator();

        /********************* 全局配置*********************/
        GlobalConfig globalConfig = new GlobalConfig();
        // 设置作者
        globalConfig.setAuthor("kaka_fun");
        // 设置生成文件输出路径(可以选择生成做盘符下,或者生成到项目中)
        // 生成到盘符下
        //globalConfig.setOutputDir("D://");
        // 生成到项目目录中
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        // 设置活动记录
        globalConfig.setActiveRecord(true);
        // xml ColumnList
        globalConfig.setBaseColumnList(false);
        // xml 二级缓存
        globalConfig.setEnableCache(false);

        /*********************数据源配置*********************/
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 设置数据库类型
        dataSourceConfig.setDbType(DbType.MYSQL);
        // 设置数据库相关信息
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/chess?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");

        /********************* 策略配置*********************/
        StrategyConfig strategyConfig = new StrategyConfig();
        // 设置生成字段名为驼峰格式
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setTablePrefix("t_");// 此处可以修改为您的表前缀
        // 设置要生成文件的表名,传入可变参数
        strategyConfig.setInclude("t_commission_detailed");

        /**********************包配置**********************/
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.cmsy.rebate");
        // 默认的controller生成的路径是web,根据需要改为controller
        packageConfig.setController("controller");

        generator.setGlobalConfig(globalConfig).setDataSource(dataSourceConfig)
                 .setPackageInfo(packageConfig).setStrategy(strategyConfig);

        generator.execute();

        System.out.println("Bingo...文件生成成功...");

    }


}
