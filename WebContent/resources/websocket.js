var wsUri = "ws://localhost:8080/BubbleGum/chatService";
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
})

function buildUri() {
	msg.hash = document.getElementById("hash").innerHTML;
	msg.chatId = document.getElementById("chatId").innerHTML;
	return wsUri + "?hash=" + msg.hash + "&chatId=" + msg.chatId;
}

function onError(evt) {
	alert("An error occured, pleas try again later")
}

function writeToScreen(message) {
    output.innerHTML += message + "<br>"; 
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}

function onRecive(evt) {
    writeToScreen(evt.data);
}

function sendMessage(msg) {
    websocket.send(JSON.stringify(msg));
}

function send() {
	text = document.getElementById("input").value;
	msg.text = text;
	sendMessage(msg);
} 