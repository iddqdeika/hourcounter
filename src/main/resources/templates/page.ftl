<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <script>
        function ajax_get(url, callback) {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4) {
                    console.log('responseText:' + xmlhttp.responseText);
                    try {
                        var data = JSON.parse(xmlhttp.responseText);
                    } catch(err) {
                        console.log(err.message + " in " + xmlhttp.responseText);
                        return;
                    }
                    callback(data);
                }
            };
            xmlhttp.open("GET", url, true);
            xmlhttp.send(null);
        }

        function getResult(){
            var user = document.counter.username.value;
            ajax_get('/worksession/result?user=' + user, function(data) {
                var result = data["sessions-sum"];
                document.getElementById("text").innerHTML = "отработал: " + result;
            });
        }

        function startSession(){
            var user = document.counter.username.value;
            ajax_get('/worksession/start?user=' + user, function(data) {
            });
        }

        function stopSession(){
            var user = document.counter.username.value;
            ajax_get('/worksession/stop?user=' + user, function(data) {
            });
        }
    </script>
</head>
<body>
<h1 id="header">Work time counter</h1>
<hr>
<div id="desc">Let's count how long you were at work:</div><br>
<div id="text">here would be info..</div>
<FORM NAME="counter">
    <TABLE>
        <TR><TD><B>User login:<B></TD>
            <TD><INPUT NAME="username" SIZE=20></TD>
            <TD>
                <INPUT TYPE="button" NAME="startbtn" VALUE="пришел"
                       onClick="startSession();">
            </TD>
            <TD>
                <INPUT TYPE="button" NAME="stopbutn" VALUE="ушел"
                       onClick="stopSession();">
            </TD>
            <TD>
                <INPUT TYPE="button" NAME="resultbtn" VALUE="отработал"
                       onClick="getResult();">
            </TD>
        </TR>
    </TABLE>
</FORM>
</body>
</html>
