/**
 * 
 */
package in.thirumal.service;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

/**
 * @author thirumal
 *
 */
@Service
public class CacheService {

	public IntSummaryStatistics getList() {
		System.out.println("list method inside Cache service");
		return IntStream.range(0, 50).summaryStatistics();
	}
}
