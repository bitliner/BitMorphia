package com.github.bitliner.morphia;

import java.util.Arrays;

import org.junit.Test;

import com.github.jmkgreen.morphia.TestBase;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Index;
import com.github.jmkgreen.morphia.annotations.Indexes;
import com.github.jmkgreen.morphia.annotations.Polymorphic;
import com.github.jmkgreen.morphia.query.Query;

public class IndexOnPolymorphicClassTest extends TestBase{

	@Entity
	@Polymorphic
	@Indexes( @Index("name,b.name") )
	static class A{
		String name;
		B b;
	}

	@Entity
	static class B extends A{
		String surname;
	}


	@Test
	public void test(){
		
		B b=new B();
		b.name="b";
		b.surname="surname of b";

		ds.save(b);

		ds.ensureIndexes();
		Query<B> q=ds.createQuery(B.class).hintIndex("name_1_b.name_1");
		System.out.println(q);
		System.out.println( q.asList().size() );
	}

}
