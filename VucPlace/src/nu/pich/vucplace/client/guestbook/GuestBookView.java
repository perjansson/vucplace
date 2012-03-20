package nu.pich.vucplace.client.guestbook;

import java.util.List;

import nu.pich.vucplace.shared.guestbook.domain.Day;
import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

import com.google.gwt.user.client.ui.IsWidget;

public interface GuestBookView extends IsWidget {

	void setPresenter(Presenter presenter);

	void updatePosts(List<PostDTO> posts);

	void updateNoofPosts(int noofPosts);

	void removePost(PostDTO postDTO);

	void updateClientIp(String clientIp);

	void updateClientOs(String clientOs);

	void clearPosts();

	void hideAddPostPanel();

	void showPostsContentPanel();

	void clearName();

	void clearMessage();

	void clearLink();

	void disableCreatePostButton();

	void enableCreatePostButton();

	void disableName();

	void enableName();

	void disableLink();

	void enableLink();

	void disableMessage();

	void enableMessage();

	void showCreatePostMessage();

	void hideCreatePostMessage();

	void showLoadingPostsMessage();

	void hideLoadingPostsMessage();

	void showErrorMessage(String message);

	String getFilterText();

	Day getFilterFromDay();

	Day getFilterToDay();

	void updateFilterText(String text);

	void updateFilterFromDate(Day fromDay);

	void updateFilterToDate(Day toDay);

	interface Presenter {

		void onCreatePost(String name, String message, String link);

		void onDeletePost(Long postKey);

		void onWhois(Long postKey);

		void onLoadMorePosts();

		void onUpdatePosts();

	}

}
