package ch.bbcag.bubblegum.dao.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.bbcag.bubblegum.dao.ChatDao;
import ch.bbcag.bubblegum.dao.ConversationAccessKeyDao;
import ch.bbcag.bubblegum.dao.test.util.QuerryExecutorForTest;
import ch.bbcag.bubblegum.model.Chat;
import ch.bbcag.bubblegum.model.ConversationAccessKey;

public class ConversationAccessKeyDaoTest {

	private static ConversationAccessKeyDao conversationAccessKeyDao;
	
	@BeforeClass
	public static void setUpClass() {
		conversationAccessKeyDao = new ConversationAccessKeyDao();
		conversationAccessKeyDao.setQueryExecutor(new QuerryExecutorForTest<ConversationAccessKey>());
	}
	
	@Test
	public void getByChatAnUser() {
		assertEquals(3, conversationAccessKeyDao.getByChatAnUser(1, 2).getId());
	}
}
