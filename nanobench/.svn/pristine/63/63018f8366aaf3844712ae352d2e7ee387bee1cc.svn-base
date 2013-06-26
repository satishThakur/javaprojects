package com.alisoft.nano.bench.listener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alisoft.nano.bench.MeasureState;

public class SimpleMeasure implements MeasureListener {
	private static final double BY_SECONDS = 1000000000.0;
	private static final Log log = LogFactory.getLog(SimpleMeasure.class);
	private static final DecimalFormat integerFormat = new DecimalFormat(
			"#,##0.00");
	private List<MeasureState> timesList = new ArrayList<MeasureState>();
	private AtomicInteger count = new AtomicInteger();
	private AtomicLong startTime = new AtomicLong();

	public void onMeasure(MeasureState state) {
		count.getAndIncrement();
		startTime.compareAndSet(0, System.nanoTime());
		outputMeasureInfo(state);
	}

	private void outputMeasureInfo(MeasureState state) {
		synchronized (timesList) {
			if (log.isDebugEnabled() && timesList.size() % 50 == 0) {
				log.debug("");

			}
			if (log.isDebugEnabled()) {
				log.debug(state.getIndex() + ".");
			}
		}
		if (isEnd(state)) {
			long total = System.nanoTime() - startTime.get();

			count.set(0);
			startTime.set(0);
			StringBuffer sb = new StringBuffer("\n");
			sb.append(state.getLabel() + "\t").append("avg: ").append(
					format(total / state.getMeasurements() / 1000000.0))
					.append(" ms\t").append("total: ").append(
							format(total / 1000000.0)).append(" ms\t").append(
							"tps: ").append(
							format(state.getMeasurements()
									/ (total / BY_SECONDS))).append("\t")
					.append("running: ").append(state.getMeasurements())
					.append(" times\t").append("in ").append(
							state.getThreadCount()).append(" Threads");

			if (!state.getLabel().equals("_warmup_")) {
				log.info(sb.toString());
				return;
			}
			if (log.isDebugEnabled()) {
				log.debug(sb.toString());
			}
		}
	}

	private String format(double value) {
		return integerFormat.format(value);
	}

	private boolean isEnd(MeasureState state) {
		return count.get() == state.getMeasurements();
	}

}
