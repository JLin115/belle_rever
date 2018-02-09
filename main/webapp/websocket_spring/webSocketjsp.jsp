<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/sockjs-0.3.min.js"></script>
<script src="js/stomp.js"></script>
<script src="js/websocket.js"></script>


</head>
<body>
<div>    
    <div>        
        <button id="connect" onclick="connect();">Connect</button> 
       <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>    
    </div>    
    <div id="conversationDiv">        
        <p>            
            <label>notice content?</label>        
        </p>        
        <p>            
              <textarea id="name" rows="5"></textarea>        
        </p>        
        <button id="sendName" onclick="sendName();">Send</button>        
        <p id="response"></p>    
    </div>
</div>

</body>
</html>