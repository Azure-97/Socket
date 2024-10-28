## 第一例：Socket实现客户端向服务器端通信

> 实现客户端向服务器端的通信只需要启动一个服务器端用来监听客户端发送的消息，
> 然后建立客户端和服务器端的连接，这时客户端想服务器端发送信息就可以接收到了，这也是一个最简单的例子。

接下来我们分析一下代码，首先给大家普及两个概念。

第一个是**阻塞函数**，阻塞函数是当这个函数不执行完，函数所在线程就一直停止在这里不动。
上面的例子中，涉及到了两个阻塞函数，一个是SocketServer.accept()，一个是BufferedReader.readline()。
当没有数据读取时，就一直会阻塞在那。也就是说如果这两个方法获取不到数据的话，程序就停在这了，不再继续往下走了。

第二个是两个在Socket例子中常用类，BufferedReader和PrintWriter。
BufferedReader是用来读取输入流的，包括服务器接收客户端推送过来，
或者客户端接收服务器推送过来的数据流和控制台输入的数据流。PrintWriter是用来推送输出流的，
可以将数据流从客户端推送给服务器，或者从服务器推送给客户端。

简单的来说，BufferedReader是接收InputStream；PrintWriter是发送OutputStream。
它们的实现方式分别是：
BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
br.readline(); 和
PrintWriter pw = new PrintWriter(outputStream);
pw.println(str);
pw.flush;

需要注意的是上面的br.readline()和pw.println(str)是一起使用的，
因为readline()读入的数据时必须要有/r或/n或/r/n，也就是必须要有换行符，
否则会为了等待一个换行/回车符而一直阻塞，所以必须使用println()方法。

理解了上面的两个概念后，再看上面的例子，我们来分析下代码逻辑。
首先我们是先启动服务器，服务器启动后，accept方法会阻塞等待一个socket连接，服务器的代码走到这就停了。
然后我们又启动了客户端。客户端启动后，服务器会往下走，走到循环中的readline方法，
阻塞等待获取的客户端socket中输入流的出现，而同时，客户端也会被循环中的readline方法阻塞，
等待控制台中的输入流的出现，整个项目就进入了等待中。

这时，我们在客户端的控制台中输入一串字符，客户端中的readline就会立刻读取到这段输入流，
然后客户端通过PrintWriter将该输入流通过socket的输出流推送到服务器的socket中，
然后进入下一个循环的等待。而服务器中的readline就会立刻读取到socket的输入流，
然后打印到服务器的控制台，之后又会进入下一个循环的等待。

理解了逻辑后不难看出，在这个例子中，之所以使用while(true)是为了能够一直循环的进入下一轮的输入输出，
如果去掉循环，那么就只能实现一次数据的通信。

> 客户端启动成功
> aaa
> bbb
> ccc
> 客户端说：aaa
> 服务器说：123
> 客户端说：bbb
> 服务器说：456
> 客户端说：ccc
> 服务器说：789

> 服务器启动成功
> 123
> 456
> 789
> 客户端说：aaa
> 服务器说：123
> 客户端说：bbb
> 服务器说：456
> 客户端说：ccc
> 服务器说：789
