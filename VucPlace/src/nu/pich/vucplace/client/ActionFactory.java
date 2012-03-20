package nu.pich.vucplace.client;

import nu.pich.vucplace.shared.batch.command.BatchAction;
import nu.pich.vucplace.shared.guestbook.command.GetPostsAction;
import nu.pich.vucplace.shared.guestbook.domain.Day;
import nu.pich.vucplace.shared.guestbook.filter.DaySubPostsFilter;
import nu.pich.vucplace.shared.guestbook.filter.NumberOfPostsFilter;
import nu.pich.vucplace.shared.guestbook.filter.PostFilter;
import nu.pich.vucplace.shared.guestbook.filter.TextContainsSubPostsFilter;

public class ActionFactory {

	public static GetPostsAction createGetPostsAction(int from, int to, String message, Day fromDay, Day toDay) {
		PostFilter filter = buildFilter(from, to, message, fromDay, toDay);
		return new GetPostsAction(filter);
	}

	private static PostFilter buildFilter(int from, int to, String text, Day fromDay, Day toDay) {
		PostFilter filter = new NumberOfPostsFilter(from, to);
		if (text != null) {
			filter = new TextContainsSubPostsFilter(text, filter);
		}
		if (fromDay != null || toDay != null) {
			filter = new DaySubPostsFilter(fromDay, toDay, filter);
		}
		return filter;
	}

	public static BatchAction createBatch(String url, boolean deleteAll) {
		BatchAction batchAction = new BatchAction();
		batchAction.setUrl(url);
		batchAction.deleteAll(deleteAll);
		return batchAction;
	}

}
