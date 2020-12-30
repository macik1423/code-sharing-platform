<html>
    <head>
        <title>Create</title>
        <script>
            function send() {
                let object = {
                    "code": document.getElementById("code_snippet").value,
                    "views": document.getElementById("view_restriction").value,
                    "time": document.getElementById("time_restriction").value
                };

                let json = JSON.stringify(object);

                let xhr = new XMLHttpRequest();
                xhr.open("POST", '/api/code/new', false)
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send(json);

                if (xhr.status == 200) {
                  alert("Success!");
                }
            }
        </script>
    </head>
    <body>
        <form>
            <textarea id="code_snippet">// write your code here</textarea>
            <div id="timeGroup">
                <label for="time">Time restriction</span>
                <input type="text" id="time_restriction">
            </div>
            <div id="viewsGroup">
                <label for="view">View restriction</span>
                <input type="text" id="view_restriction">
            </div>
            <div><button id="send_snippet" type="submit" onclick="send()">Submit</button></div>
        </form>
    </body>
</html>

