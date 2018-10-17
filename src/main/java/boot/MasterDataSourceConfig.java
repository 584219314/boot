package boot;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


/**
*mysql主服务配置类
*需要注意的是MapperScan中backage包地址一定要对应上主服务的mapper接口包
*@ConfigurationProperties("datasource.master")对应的是主服务器配置，它在application.properties中有对应的值配置
*sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
        .getResources("classpath*:master/*.xml"));
        这个是配置编写sql配置文件的位置，该地址千万不能写错
*/
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "boot.shedule.mapper.master",sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties("datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }
    
    @Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
        .getResources("classpath*:mappers/master/*.xml"));
        return sessionFactoryBean.getObject();
    }
}