package edu.syr.ischool.mafudge.ensemblelib.tests;

import edu.syr.ischool.mafudge.ensemblelib.*;
import edu.syr.ischool.mafudge.ensemblelib.models.InstContentWebDestination;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestWebDestination {

	@Test
	public void testWebDestination() {
		String actualId = "key";
		String actualName = "name";
		InstContentWebDestination wd = new InstContentWebDestination(actualId,actualName);
		assertEquals(actualId, wd.wdId);
		assertEquals(actualName,wd.wdName);
	}

}
