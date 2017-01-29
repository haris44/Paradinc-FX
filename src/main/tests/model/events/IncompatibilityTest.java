package model.events;

import factory.GameFactory;
import model.Game;
import model.language.Attribute;
import model.language.Language;
import model.language.Platform;
import model.language.PlatformType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alex on 29/01/2017.
 */
public class IncompatibilityTest {


	ThrowableEvent thrEv;

	@Before
	public void setUp() {
		Language lang = new Language("test", 100, 0);
		lang.setAttributes(new ArrayList<Attribute>(){{ add(0, Platform.fromPlatformType(PlatformType.Linux));}});
		Incompatibility inc = new Incompatibility("test", 0, 0, 0, new ArrayList<Attribute>(){{ add(0, Platform.fromPlatformType(PlatformType.Linux));}});
		Game game = GameFactory.createGame(lang, 20);
		thrEv = inc.getThrowable(game);
	}

	@Test
	public void testCalculer() throws Exception {
		assertEquals(thrEv.probability, 100);
	}

	@Test
	public void testNotCalculer() throws Exception {
		assertNotEquals(thrEv.probability, 90);
	}

}