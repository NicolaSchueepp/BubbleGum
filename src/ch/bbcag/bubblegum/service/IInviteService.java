package ch.bbcag.bubblegum.service;

public interface IInviteService {

	public boolean inviteUser(long chatId, long userId);
	
	public boolean acceptInvite(long inviteId);
}
