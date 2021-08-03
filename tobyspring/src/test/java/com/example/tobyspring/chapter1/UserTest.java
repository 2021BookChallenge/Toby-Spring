package com.example.tobyspring.chapter1;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.example.tobyspring.chapter1.config.DaoFactory;
import com.example.tobyspring.chapter1.config.MySQLConnectionMaker;
import com.example.tobyspring.chapter1.dao.UserDao;
import com.example.tobyspring.chapter1.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = {DaoFactory.class})
@DataJdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserTest {

	@AfterEach
	void tearDown() throws SQLException, ClassNotFoundException {
		Connection connection = new MySQLConnectionMaker().makeConnection();
		Statement stmt = connection.createStatement();

		stmt.executeUpdate("TRUNCATE users");

		stmt.close();
		connection.close();
	}

	@Test
	@DisplayName("DaoFactory 성공 테스트")
	void testDaoFactory_Success() throws SQLException, ClassNotFoundException {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("ID1");
		user.setName("name");
		user.setPassword("password");

		dao.add(user);

		User user2 = dao.get(user.getId());
		Assertions.assertAll(
			() -> Assertions.assertEquals(user.getId(), user2.getId()),
			() -> Assertions.assertEquals(user.getName(), user2.getName()),
			() -> Assertions.assertEquals(user.getPassword(), user2.getPassword())
		);
	}
}
