package nu.pich.vucplace.server;

import net.customware.gwt.dispatch.client.standard.StandardDispatchService;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.DispatchException;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MyDispatchServiceServlet extends RemoteServiceServlet implements StandardDispatchService {

	private static final long serialVersionUID = 1L;

	@Override
	public Result execute(Action<?> action) throws DispatchException {
		try {
			return DispatchUtil.getDispatch().execute(action);
		} catch (RuntimeException e) {
			log("Exception while executing " + action.getClass().getName() + ": " + e.getMessage(), e);
			throw e;
		}
	}
}