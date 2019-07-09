package ch.bbcag.bubblegum.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.bbcag.bubblegum.dao.InviteDao;
import ch.bbcag.bubblegum.dao.UserInChatDao;
import ch.bbcag.bubblegum.dao.test.util.QuerryExecutorForTest;
import ch.bbcag.bubblegum.model.Invite;
import ch.bbcag.bubblegum.model.UserInChat;

public class UserInChatDaoTest {

	private static UserInChatDao userInChatDao;
	
	@BeforeClass
	public static void setUpClass() {
		userInChatDao = new UserInChatDao();
		userInChatDao.setQueryExecutor(new QuerryExecutorForTest<UserInChat>());
	}
	
	@Test
	public void getByUserIdAndChatId() {
		assertEquals(true,userInChatDao.getByUserIdAndChatId(1,1).isAdmin());
	}
}
