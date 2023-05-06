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
                "left join address m on p.name = m.name";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Assertions.assertFalse(result.isEmpty());
        System.out.println(result);
    }
}
