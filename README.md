# Astrub

> 极简代码与配置，实现异构jdbc联合查询

## 引入依赖
```xml
<dependency>
    <groupId>com.gitee.phaeris</groupId>
    <artifactId>astrub</artifactId>
    <version>${latest.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```

## 配置
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ds1?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.zaxxer.hikari.HikariDataSource

astrub:
  # 是否开启自动注册为数据源（如果本身已有数据源可能会导致覆盖的情况）
  enabled: false
  schemas:
    # 第一个schema为默认schema
    - name: mysql
      driver: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/astrub
      user: root
      password: root
    - name: pg
      driver: org.postgresql.Driver
      url: jdbc:postgresql://localhost:5432/astrub
      user: postgres
      password: 123456
```

## 数据库脚本
### mysql
```mysql
create table address
(
    name varchar(200) null comment '名字',
    area varchar(20)  null
)
    charset = utf8;
```
### pg
```postgresql

create table phone
(
    name  varchar,
    phone varchar
);
```

## 查询
```java
import com.gitee.phaeris.ConnectionHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

/**
 * @author wyh
 * @since 2023/4/24
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DatasourceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    void testLocalDatasource() {
        String sql = "select * from car";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Assertions.assertFalse(result.isEmpty());
        System.out.println(result);
    }

    @Test
    void testCalcite() {
        String sql = "select p.name, p.phone, m.area " +
                "from pg.phone p " +
                "left join address m on p.name = m.name";
        JdbcTemplate calciteTemplate = new JdbcTemplate(ConnectionHelper.getDatasource());
        List<Map<String, Object>> result = calciteTemplate.queryForList(sql);
        Assertions.assertFalse(result.isEmpty());
        System.out.println(result);
    }
}
```