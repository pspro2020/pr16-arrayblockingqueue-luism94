package codes;

import java.time.format.DateTimeFormatter;

public class Constants {

	static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
	static final String INTERRUPTED_THREAD_PICKING_DISH = "Time %s: --- Thread %s interrupted while picking or doing his chores with dish nº %d...";
	static final String INTERRUPTED_THREAD_PLACING_DISH = "Time %s: --- Thread %s interrupted while placing dish nº %d...";
	static final String DRYER_DOING_CHORES = "Time %s: %s picked up dish %d and starts drying...";
	static final String WASHER_DOING_CHORES = "Time %s: %s picked up dish %d and starts cleaning...";
	static final String MAIN_PROGRAM_END = "Time %s: (everyone) HAPPY BIRTHDAY!!!";
	static final String MAIN_INTERRUPTED = "Time %s: ERROR! MAIN THREAD INTERRUPTED";
	static final int DISH_LIMIT = 20;
	static final int WASHING_TIME_MIN = 4;
	static final int WASHING_TIME_SPAN = 5;
	static final int DRYING_TIME_MIN = 1;
	static final int DRYING_TIME_SPAN = 2;
	static final int ORGANIZING_TIME_MIN = 1;
	static final int ORGANIZING_TIME_SPAN = 1;
	static final int THREAD_LIMIT = 3;
	static final int MAX_TIME_SECONDS = 60;	
}
