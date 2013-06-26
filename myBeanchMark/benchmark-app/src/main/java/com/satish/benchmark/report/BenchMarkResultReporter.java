package com.satish.benchmark.report;

import com.satish.benchmark.BenchMarkServiceListner;
import com.satish.benchmark.vo.BenchMarkDataVO;

public class BenchMarkResultReporter implements BenchMarkServiceListner {

	@Override
	public void onBenchMarkData(BenchMarkDataVO benchMarkData) {
		System.out.println(benchMarkData);

	}

}
