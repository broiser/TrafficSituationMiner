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
public class InFrontOfRelationTypeEvaluatorTest {

	@Mock
	private RoadDirectionDeterminer roadDirectionDeterminer;

	@InjectMocks
	private InFrontOfRelationTypeEvaluator inFrontOfRelationTypeEvaluator;

	@Before
	public void mockRoadDirectionDeterminer() {
		when(roadDirectionDeterminer.determineDirection(any(AsfinagTrafficmessage.class),
				any(AsfinagTrafficmessage.class))).thenCallRealMethod();
	}

	@Test
	public void trafficMessageIsNotInFrontOfBecauseOfDifferentRoadDirections() throws Exception {
		AsfinagTrafficmessage trafficMessage1 = mockTrafficMessage(1, 10, 20);
		AsfinagTrafficmessage trafficMessage2 = mockTrafficMessage(2, 10, 20);

		assertFalse(inFrontOfRelationTypeEvaluator.evaluate(trafficMessage1, trafficMessage2));
	}

	@Test
	public void trafficMessageWithDirection1IsInFrontOf() throws Exception {
		AsfinagTrafficmessage trafficMessage1 = mockTrafficMessage(1, 10, 20);
		AsfinagTrafficmessage trafficMessage2 = mockTrafficMessage(1, 30, 40);

		assertTrue(inFrontOfRelationTypeEvaluator.evaluate(trafficMessage1, trafficMessage2));
	}

	@Test
	public void trafficMessageWithDirection1IsNotInFrontOf() throws Exception {
		AsfinagTrafficmessage trafficMessage1 = mockTrafficMessage(1, 10, 20);
		AsfinagTrafficmessage trafficMessage2 = mockTrafficMessage(1, 20, 30);

		assertFalse(inFrontOfRelationTypeEvaluator.evaluate(trafficMessage1, trafficMessage2));
	}

	@Test
	public void trafficMessageWithDirection2IsInFrontOf() throws Exception {
		AsfinagTrafficmessage trafficMessage1 = mockTrafficMessage(2, 40, 30);
		AsfinagTrafficmessage trafficMessage2 = mockTrafficMessage(2, 20, 10);

		assertTrue(inFrontOfRelationTypeEvaluator.evaluate(trafficMessage1, trafficMessage2));
	}

	@Test
	public void trafficMessageWithDirection2IsNotInFrontOf() throws Exception {
		AsfinagTrafficmessage trafficMessage1 = mockTrafficMessage(2, 40, 30);
		AsfinagTrafficmessage trafficMessage2 = mockTrafficMessage(2, 30, 20);

		assertFalse(inFrontOfRelationTypeEvaluator.evaluate(trafficMessage1, trafficMessage2));
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
