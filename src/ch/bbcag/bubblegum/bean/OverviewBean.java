package ch.bbcag.bubblegum.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.model.Invite;
import ch.bbcag.bubblegum.model.Message;
import ch.bbcag.bubblegum.service.IInviteService;
import ch.bbcag.bubblegum.service.IMessageService;

@Named
@ViewScoped
public class OverviewBean implements Serializable{
	

	private static final long serialVersionUID = -7953007188364085708L;
	
	private List<Invite> invites;
	private ArrayList<Entry<Message, Integer>> quickMessages;
	private ArrayList<Entry<Message, Integer>> bubbleMessages;
	
	@Inject
	private transient IInviteService inviteService;
	
	@Inject
	private transient IMessageService messageService;
	
	@PostConstruct
	public void init() {
		invites = inviteService.getUnacceptedInvites();
		quickMessages = messageService.getNewQuickMessages();
		bubbleMessages = messageService.getNewBubbleMessages();
	}
	
	public List<Invite> getInvites(){
		return invites;
	}
	
	public ArrayList<Entry<Message, Integer>> getQuickMessages(){
		return quickMessages;
	}
	
	public ArrayList<Entry<Message, Integer>> getBubbleMessages(){
		return bubbleMessages;
	}
	
	public boolean isEmty() {
		return (invites.size() + quickMessages.size() + bubbleMessages.size()) == 0;
	}


}
