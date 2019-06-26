package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.model.Invite;

public interface IInviteDao {

	public void create(Invite invite);
	
	public void delete(Invite invite);
	
	public List<Invite> getUnacceptedByUser(long userId);
	
	public Invite getByUserAndChatId(long userId, long chatId);
	
	public void update(Invite invite);
	
	public Invite getById(long id);
	
}
