package ch.bbcag.bubblegum.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.bbcag.bubblegum.dao.UserDao;
import ch.bbcag.bubblegum.dao.UserReadMessageDao;
import ch.bbcag.bubblegum.dao.test.util.QuerryExecutorForTest;
import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.model.UserReadMessage;

public class UserDaoTest {

	private static UserDao userDao;
	
	@BeforeClass
	public static void setUpClass() {
		userDao = new UserDao();
		userDao.setQueryExecutor(new QuerryExecutorForTest<User>());
	}
	
	@Test
	public void searchUserByName() {
		assertEquals(2,userDao.searchUserByName("Joe").size());
	}
}
