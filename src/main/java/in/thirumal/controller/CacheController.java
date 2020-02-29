/**
 * 
 */
package in.thirumal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.thirumal.service.CacheService;

/**
 * @author thirumal
 *
 */
@RestController
@RequestMapping("cache")
public class CacheController {
	
	@Autowired
	CacheService cacheService;

	@Cacheable(value = "statistic")
	@GetMapping
	public String getList() {
		System.out.println("Inside cache controller");
		return cacheService.getList().toString();
	}
}
