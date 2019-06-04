var wsUri = "ws://localhost:8080/BubbleGum/chatService";
var websocket;
var msg = {
	 text: "",
	 key:   "",
};


$("document").ready(function() {
	msg.key = document.getElementById("chatKey").innerHTML;
	websocket = new WebSocket(wsUri + "?key=" + key);
	websocket.onerror = function(evt) { onError(evt) };
	websocket.onopen = function(evt) { onOpen(evt) };
	websocket.onmessage = function(evt) { onRecive(evt) };
})


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