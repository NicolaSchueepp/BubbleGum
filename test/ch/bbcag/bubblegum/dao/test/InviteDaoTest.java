package ch.bbcag.bubblegum.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.bbcag.bubblegum.dao.ConversationAccessKeyDao;
import ch.bbcag.bubblegum.dao.InviteDao;
import ch.bbcag.bubblegum.dao.test.util.QuerryExecutorForTest;
import ch.bbcag.bubblegum.model.ConversationAccessKey;
import ch.bbcag.bubblegum.model.Invite;

public class InviteDaoTest {

	private static InviteDao inviteDao;
	
	@BeforeClass
	public static void setUpClass() {
		inviteDao = new InviteDao();
		inviteDao.setQueryExecutor(new QuerryExecutorForTest<Invite>());
	}
	
	@Test
	public void getByUserAndChatId() {
		assertEquals(1,inviteDao.getByUserAndChatId(2, 1).getId());
	}
}
