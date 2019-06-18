package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.model.Invite;

public interface IInviteDao {

	public Invite create(Invite invite);
	
	public List<Invite> getbyUser(long userId);
	
	public Invite getByUserAndChatId(long userId, long chatId);
	
	public Invite update(Invite invite);
	
	public Invite getById(long id);
	
}
