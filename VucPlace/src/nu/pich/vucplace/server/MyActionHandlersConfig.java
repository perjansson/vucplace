package nu.pich.vucplace.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import nu.pich.vucplace.server.batch.BatchHandler;
import nu.pich.vucplace.server.guestbook.CreatePostHandler;
import nu.pich.vucplace.server.guestbook.DeletePostHandler;
import nu.pich.vucplace.server.guestbook.GetPostHandler;
import nu.pich.vucplace.server.guestbook.GetPostsHandler;

public class MyActionHandlersConfig implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		DispatchUtil.registerHandler(new GetPostsHandler());
		DispatchUtil.registerHandler(new CreatePostHandler());
		DispatchUtil.registerHandler(new DeletePostHandler());
		DispatchUtil.registerHandler(new GetPostHandler());
		DispatchUtil.registerHandler(new BatchHandler());
	}

	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		// Do nothing...
	}
}