/**
 * 
 */
package com.github.jmkgreen.morphia.query;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.github.jmkgreen.morphia.TestBase;
import com.github.jmkgreen.morphia.annotations.Id;

/**
 * @author doc
 * 
 */
public class MyTestQueryImpl extends TestBase {

	public final static Logger log=Logger.getAnonymousLogger();
	
	static class E1 {
		@Id
		ObjectId id;
		
		String a;
		String b;
		int i;
		E2 e2 = new E2();
	}
	
	static class E2 {
		String foo;
	}
	
	@Test
	public void testQueryClone() throws Exception {
		Query q = ds.createQuery(E1.class).field("i").equal(5).limit(5).filter("a", "value_a").filter("b", "value_b")
				.skip(5).batchSize(10).disableCursorTimeout().hintIndex("a")
				.order("a");
		q.disableValidation().filter("foo", "bar");
		q.fetch();
		log.info(q.toString());
//		Assert.assertTrue(sameState(q, q.clone()));
	}
}
