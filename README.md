# Astrub

> 极简代码与配置，实现异构jdbc联合查询

## 安装
`mvn clean install -DskipTests`

## 引入依赖
```xml
<dependency>
    <groupId>com.phaeris.astrub</groupId>
    <artifactId>astrub</artifactId>
    <version>1.0-SNAPSHOT</version>
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
astrub:
  # 是否忽略大小写（默认false）
  # 开启则不要求表与字段都使用大写，但是可能会导致查询效率下降，因为Calcite将无法使用索引来加速查询
  ignore-case: true
  schemas:
    - name: ms
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
package com.phaeris.astrub;

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
public class JoinTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testJoin() {
        String sql = "select p.name, p.phone, m.area " +
                "from pg.phone p " +
                "left join ms.address m on p.name = m.name";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Assertions.assertFalse(result.isEmpty());
        System.out.println(result);
    }
}
```