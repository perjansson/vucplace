package nu.pich.vucplace.shared.guestbook.filter;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import nu.pich.vucplace.shared.guestbook.domain.Day;
import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

public class DaySubPostsFilter extends SubPostFilter {

	private static final long serialVersionUID = 1L;

	private Day fromDay;
	private Day toDay;

	public DaySubPostsFilter(Day fromDay, Day toDay, PostFilter subPostFilter) {
		super(subPostFilter);
		this.fromDay = fromDay;
		this.toDay = toDay;
	}

	DaySubPostsFilter() {
		super();
	}

	@Override
	public List<PostDTO> filter(List<PostDTO> postDTOs) {
		List<PostDTO> filteredPostDTOs = super.filter(postDTOs);
		return filterOnFromDayAndToDay(filteredPostDTOs);
	}

	private List<PostDTO> filterOnFromDayAndToDay(List<PostDTO> postDTOs) {
		Iterator<PostDTO> iter = postDTOs.iterator();
		while (iter.hasNext()) {
			if (!isMatch(iter.next())) {
				iter.remove();
			}
		}
		return postDTOs;
	}

	private boolean isMatch(PostDTO postDTO) {
		return dateTimeIsSameOrAfterFromDay(postDTO.getDateTime()) && dateTimeIsSameOrBeforeToDay(postDTO.getDateTime());
	}

	private boolean dateTimeIsSameOrAfterFromDay(Date dateTime) {
		if (fromDay == null) {
			return true;
		}
		Day compareDay = Day.fromDate(dateTime);
		return compareDay.isSameDay(fromDay) || compareDay.isAfter(fromDay);
	}

	private boolean dateTimeIsSameOrBeforeToDay(Date dateTime) {
		if (toDay == null) {
			return true;
		}
		Day compareDay = Day.fromDate(dateTime);
		return compareDay.isSameDay(toDay) || compareDay.isBefore(toDay);
	}

}
