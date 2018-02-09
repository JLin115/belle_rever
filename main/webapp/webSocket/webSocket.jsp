<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
 <script src="js/webSocket.js"></script>
</head>
 <script>
//         var wsLoc = "ws://localhost:8080/Belle_Rever/EchoServer";
//         //WebSocket可以選擇ws或是wss通訊協定，ws就相當於一般的http，wss則相當於https
//         var ws = new WebSocket(wsLoc);

//         //當連線開啟時觸發
//         ws.onopen = function () {
//             console.log("Websocket is opened!!");
//         };
//         //收到訊息時觸發
//         ws.onmessage = function (msgEvent) {
//             console.log("Received Message!!", msgEvent);
//             var msgDisplay = document.getElementById("msgDisplay");
//             msgDisplay.innerText = msgEvent.data;
//         };

//         //直接測試傳送訊息
//         ws.send('Hello WebSocket.');
</script>
<body>
 <div>
          <form id="chatRoomForm" onsubmit="return false;">
                聊天室
                名字: <input type="text" id="userNameInput" /> 
 
                <input type="button" id="loginBtn" value="登入" /> <span id="infoWindow"></span>
 
                <input type="text" id="userinput" /> 
  
                <input type="submit" value="送出訊息" />
            </form>
        </div>
        <div id="messageDisplay"></div>
</body>
</html>