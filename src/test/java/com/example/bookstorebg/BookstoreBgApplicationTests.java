package com.example.bookstorebg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootTest
class BookstoreBgApplicationTests {

	@Resource
	DataSource dataSource;

	@Test
	public void getConnection() throws Exception {
		System.out.println("数据库连接："+dataSource.getConnection());
	}

}
