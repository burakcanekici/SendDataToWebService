# Sending Data To Web Service Without Any Library In Android
Sample to sending data from android phone(client) to web service without use any other library. It uses HTTPUrlConnection and works as
AsyncTask.

There are so many library that is used to send data to android phone to web service. HTTPUrlConnection is also used for this purpose and
it doesn't need any additional library. Send data function that contains HTTPUrlConnection must be extended as AsyncTask.

In this project CustomHttpRequest class can be used to send data to android phone to web service. You can use this class as in
MainActivity.java

new CustomHttpRequest().execute(post_method, my_url, my_param); line is call CustomHttpRequest class with parameters. It can be possible
that reading server response with CustomHttpRequest.

post_method -> it may be "GET" and "POST"

my_url      -> it is our url

my_param    -> it is our parameters and it must be JSONObject. e.x. {"key1" : "value1" , "key2" : "value2"}
