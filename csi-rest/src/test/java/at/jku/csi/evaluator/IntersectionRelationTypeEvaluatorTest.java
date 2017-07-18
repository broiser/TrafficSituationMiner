package at.jku.csi.evaluator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.jku.csi.determiner.RoadDirectionDeterminer;
import at.jku.tk.csi.server.datalayer.source.dynamic_.asfinag.AsfinagTrafficmessage;

@RunWith(MockitoJUnitRunner.class)
public class IntersectionRelationTypeEvaluatorTest {

	@Mock
	private RoadDirectionDeterminer roadDirectionDeterminer;

	@InjectMocks
	private IntersectionRelationTypeEvaluator intersectionRelationTypeEvaluator;

	@Before
	public void mockRoadDirectionDeterminer() {
		when(roadDirectionDeterminer.determineDirection(any(AsfinagTrafficmessage.class),
				any(AsfinagTrafficmessage.class))).thenCallRealMethod();
	}

	@Test
	public void trafficMessagesAreNotIntersectedBecauseOfDifferentRoadDirections() throws Exception {
		AsfinagTrafficmessage trafficMessage1 = mockTrafficMessage(1, 10, 20);
		AsfinagTrafficmessage trafficMessage2 = mockTrafficMessage(2, 10, 20);

		assertFalse(intersectionRelationTypeEvaluator.evaluate(trafficMessage1, trafficMessage2));
	}

	@Test
	public void trafficMessageWithDirection1AreIntersected() throws Exception {
		AsfinagTrafficmessage trafficMessage1 = mockTrafficMessage(1, 10, 40);
		AsfinagTrafficmessage trafficMessage2 = mockTrafficMessage(1, 20, 30);
		AsfinagTrafficmessage trafficMessage3 = mockTrafficMessage(1, 10, 30);
		AsfinagTrafficmessage trafficMessage4 = mockTrafficMessage(1, 20, 40);
		assertTrue(intersectionRelationTypeEvaluator.evaluate(trafficMessage1, trafficMessage2));
		assertTrue(intersectionRelationTypeEvaluator.evaluate(trafficMessage3, trafficMessage4));
		assertTrue(intersectionRelationTypeEvaluator.evaluate(trafficMessage4, trafficMessage3));
	}

	@Test
	public void trafficMessageWithDirection2AreIntersected() throws Exception {
		AsfinagTrafficmessage trafficMessage1 = mockTrafficMessage(2, 40, 10);
		AsfinagTrafficmessage trafficMessage2 = mockTrafficMessage(2, 30, 20);
		AsfinagTrafficmessage trafficMessage3 = mockTrafficMessage(2, 30, 10);
		AsfinagTrafficmessage trafficMessage4 = mockTrafficMessage(2, 40, 20);

		assertTrue(intersectionRelationTypeEvaluator.evaluate(trafficMessage1, trafficMessage2));
		assertTrue(intersectionRelationTypeEvaluator.evaluate(trafficMessage3, trafficMessage4));
		assertTrue(intersectionRelationTypeEvaluator.evaluate(trafficMessage4, trafficMessage3));
	}

	@Test
	public void trafficMessageWithDirection1AreNotIntersected() throws Exception {
		AsfinagTrafficmessage trafficMessage1 = mockTrafficMessage(1, 10, 20);
		AsfinagTrafficmessage trafficMessage2 = mockTrafficMessage(1, 30, 40);
		assertFalse(intersectionRelationTypeEvaluator.evaluate(trafficMessage1, trafficMessage2));
		assertFalse(intersectionRelationTypeEvaluator.evaluate(trafficMessage2, trafficMessage1));
	}

	@Test
	public void trafficMessageWithDirection2AreNotIntersected() throws Exception {
		AsfinagTrafficmessage trafficMessage1 = mockTrafficMessage(2, 20, 10);
		AsfinagTrafficmessage trafficMessage2 = mockTrafficMessage(2, 40, 30);
		assertFalse(intersectionRelationTypeEvaluator.evaluate(trafficMessage1, trafficMessage2));
		assertFalse(intersectionRelationTypeEvaluator.evaluate(trafficMessage2, trafficMessage1));
	}

	private AsfinagTrafficmessage mockTrafficMessage(int direction, int beginmeter, int endmeter) {
		AsfinagTrafficmessage trafficmessage = mock(AsfinagTrafficmessage.class);
		when(trafficmessage.getRoad_code()).thenReturn("A1+" + direction);
		when(trafficmessage.getRoad_direction()).thenReturn(direction);
		when(trafficmessage.getBeginmeter()).thenReturn(beginmeter);
		when(trafficmessage.getEndmeter()).thenReturn(endmeter);
		return trafficmessage;
	}
}
