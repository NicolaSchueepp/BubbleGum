package ch.bbcag.bubblegum.dao.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.bbcag.bubblegum.dao.ChatDao;
import ch.bbcag.bubblegum.dao.IChatDao;
import ch.bbcag.bubblegum.dao.test.util.QuerryExecutorForTest;
import ch.bbcag.bubblegum.model.Chat;

public class ChatDaoTest{

	private static ChatDao chatDao;
	
	@BeforeClass
	public static void setUpClass() {
		chatDao = new ChatDao();
		chatDao.setQueryExecutor(new QuerryExecutorForTest<Chat>());
	}
	
	@Test
	public void searchChatByName() {
		assertEquals("Quick-Chat", chatDao.getQuickChatByMembers(1, 2).getName());
	}

	@Test
	public void getQuickChatByMembers() {
		assertEquals(2, chatDao.searchChatByName("a").size());
	}
}
