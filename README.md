Please refer to Assignment2-Report-WalidMoustafa.pdf for more details.

This is the server component of the distributed whiteboard desktop application.

This assignment is about implementing a distributed Whiteboard application. The whiteboard will be shared by several users connected to a central server using Java RMI (Remote Method Invocation) framework. The implemented architecture is a star schema with a simple messaging server in the centre and several desktop clients exchanging and synchronizing user interface as well as control events via RMI function calls the central server.
The first user connects to the server is granted the Admin role which authorizes her to receive following users requests-to-join and decide which requests to accept and grant access to the shared whiteboard and which requests to simply revoke and bounce out of the board.
Even authorized board users can be bounced out of the whiteboard at any time by the Admin user. Moreover, when the admin user quits the application, all connected board users are sent a notification message and all are bounced out and the board is cleared out.
By the end of a board session (which normally happens when all users quit or the Admin user terminates the running whiteboard session), the board server discards all the session data, but does NOT shutdown. Instead, the board server keeps waiting for the first user to connect to be granted the Admin role and start a brand new whiteboard session.
