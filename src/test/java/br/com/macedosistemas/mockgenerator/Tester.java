package br.com.macedosistemas.mockgenerator;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.Test;

import org.reflections.Reflections;

public class Tester {

	@Test
	public void coverageTester() {
		Reflections reflections = new Reflections("br.com.macedosistemas.mockgenerator");
		new EntityTester(reflections);
	}
}
