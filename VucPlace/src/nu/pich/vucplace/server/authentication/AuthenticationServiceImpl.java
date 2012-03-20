package nu.pich.vucplace.server.authentication;

public class AuthenticationServiceImpl implements AuthenticationService {

	private static final String PASS_KEY = "iceicebaby";

	@Override
	public boolean verifyPassKey(String passKey) {
		if (!PASS_KEY.equalsIgnoreCase(passKey)) {
			throw new RuntimeException("Permission denied....");
		}
		return true;
	}

}
