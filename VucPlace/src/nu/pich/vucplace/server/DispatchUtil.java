package nu.pich.vucplace.server;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.DefaultActionHandlerRegistry;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.SimpleDispatch;

public final class DispatchUtil {

	private static final DefaultActionHandlerRegistry REGISTRY = new DefaultActionHandlerRegistry();

	private static final Dispatch DISPATCH = new SimpleDispatch(REGISTRY);

	public static void registerHandler(ActionHandler<?, ?> handler) {
		REGISTRY.addHandler(handler);
	}

	public static Dispatch getDispatch() {
		return DISPATCH;
	}
}