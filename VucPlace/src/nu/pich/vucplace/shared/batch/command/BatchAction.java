package nu.pich.vucplace.shared.batch.command;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class BatchAction implements Action<BatchResult>, Serializable {

	private static final long serialVersionUID = 1L;

	private String url;
	private boolean deleteAll;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void deleteAll(boolean deleteAll) {
		this.deleteAll = deleteAll;
	}

	public boolean shouldDeleteAll() {
		return deleteAll;
	}

	public boolean hasUrl() {
		return getUrl() != null;
	}

}
