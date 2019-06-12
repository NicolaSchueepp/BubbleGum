var wsUri = "ws://localhost:8080/BubbleGum/chatService";
var output = document.getElementById("output");
var userName = document.getElementById("userName").value;
var websocket;
var msg = {
	 text: "",
	 hash:   "",
	 chatId: ""
};


$("document").ready(function() {
	websocket = new WebSocket(buildUri());
	websocket.onerror = function(evt) { onError(evt) };
	websocket.onopen = function(evt) { onOpen(evt) };
	websocket.onmessage = function(evt) { onRecive(evt) };	
	scrollDown();
})

function buildUri() {
	msg.hash = document.getElementById("hash").value;
	msg.chatId = document.getElementById("chatId").value;
	return wsUri + "?hash=" + msg.hash + "&chatId=" + msg.chatId;
}

function onError(evt) {
	alert("An error occured, pleas try again later")
}

function addMessage(messageJSON) {
	var selfSend = messageJSON.from == userName;
	output.innerHTML += 
	"				<div style=\"width: 50.1%; background: #e9ecef; border-radius: .3rem; padding-bottom: 5px; margin: 10px; float: "+ (selfSend ? "right" : "left") +" \">\r\n" + 
	"					<div style=\"padding: 3px; width: 100%; background: #343a40; border-top-left-radius: .3rem; border-top-right-radius: .3rem;\">\r\n" + 
	"						<p style=\"color: #28a745; margin-bottom: 0px;\">"+ messageJSON.from +"</p>\r\n" + 
	"					</div>\r\n" + 
	"		 			<p style=\"margin: 3px\">"+messageJSON.text+"</p> \r\n" + 
	"				</div>"
}

function onOpen() {
    console.log("Connection opened")
}

function onRecive(evt) {
	addMessage(JSON.parse(evt.data));
	scrollDown();
}

function scrollDown() {
	var objDiv = document.getElementById("messageContainer");
	objDiv.scrollTop = objDiv.scrollHeight;
}

function send() {
	msg.text = document.getElementById("input").value;
	websocket.send(JSON.stringify(msg));
} 