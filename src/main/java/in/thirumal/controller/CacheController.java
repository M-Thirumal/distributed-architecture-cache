/**
 * 
 */
package in.thirumal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@Autowired
	CacheManager cacheManager;

	@Cacheable(value = "statistic")
	@GetMapping
	public String getList() {
		System.out.println("Inside cache controller");
		return cacheService.getList().toString();
	}
	
	@Cacheable(value = "hello")
	@GetMapping("/simple-hello")
	public String simpleCache() {
		System.out.println("Not from cache");
		return "hello";
	}
	
	@CachePut(value = "hello")
	@GetMapping("/update-hello")
	public String updateHelloCache() {
		System.out.println("Not from cache");
		return "hi";
	}
	
	@Cacheable(value = "case", key= "{#pk, #fk}")
	@PostMapping("/multiple-key/{pk}/{fk}")
	public int multiLevel(@PathVariable(value = "pk") int pk, @PathVariable(value = "fk") int fk) {
		System.out.println("Not from cache");
		return pk*fk;
	}
	
	
	@DeleteMapping("/multiple-key/{pk}/{fk}")
	public int evictMultiLevel(@PathVariable(value = "pk") int pk, @PathVariable(value = "fk") int fk) {
		System.out.println("Not from cache");
		String key = pk + "," + fk;
		System.out.println(cacheManager.getCache("case").get(key));
		cacheManager.getCache("case").evict(key);
		int newValue = pk*fk * 100;
		cacheManager.getCache("case").putIfAbsent(key, newValue);
		return newValue;
	}
	
	@DeleteMapping("/evict-all-cache")
	public boolean evictAllCache() {
		cacheManager.getCacheNames().stream().forEach(cacheName->{
			System.out.println(cacheName);
			cacheManager.getCache(cacheName).clear();
		});
		return true;
	}
}
