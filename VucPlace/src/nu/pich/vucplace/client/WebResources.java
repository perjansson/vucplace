package nu.pich.vucplace.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface WebResources extends ClientBundle {

	public static final WebResources INSTANCE = GWT.create(WebResources.class);

	@Source("webresources/style.css")
	Style style();

	@Source("webresources/ajax-loader.gif")
	ImageResource spinner();

	@Source("webresources/trash_can.png")
	ImageResource trashCan();

	@Source("webresources/question_mark.png")
	ImageResource questionMark();
}
