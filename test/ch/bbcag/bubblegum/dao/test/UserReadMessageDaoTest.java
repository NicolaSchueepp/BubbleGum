package ch.bbcag.bubblegum.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.bbcag.bubblegum.dao.UserInChatDao;
import ch.bbcag.bubblegum.dao.UserReadMessageDao;
import ch.bbcag.bubblegum.dao.test.util.QuerryExecutorForTest;
import ch.bbcag.bubblegum.model.UserInChat;
import ch.bbcag.bubblegum.model.UserReadMessage;

public class UserReadMessageDaoTest {

	private static UserReadMessageDao userReadMessageDao;
	
	@BeforeClass
	public static void setUpClass() {
		userReadMessageDao = new UserReadMessageDao();
		userReadMessageDao.setQueryExecutor(new QuerryExecutorForTest<UserReadMessage>());
	}
	
	@Test
	public void getByUserIdAndMessageId() {
		assertNotNull(userReadMessageDao.getByUserIdAndMessageId(1, 1));
	}
}
