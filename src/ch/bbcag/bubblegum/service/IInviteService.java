package ch.bbcag.bubblegum.service;

import java.util.List;

import ch.bbcag.bubblegum.model.Invite;

public interface IInviteService {

	public boolean inviteUser(long chatId, long userId);
	
	public boolean acceptInvite(long inviteId);
	
	public List<Invite> getUnacceptedInvites();
}
