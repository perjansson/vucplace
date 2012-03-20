package nu.pich.vucplace.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class PersistenceManagerFactoryHelper {

	private static final javax.jdo.PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");

	private PersistenceManagerFactoryHelper() {
	}

	public static PersistenceManagerFactory getFactory() {
		return pmfInstance;
	}

}
