package model.events;

import factory.GameFactory;
import model.Game;
import model.language.Attribute;
import model.language.Language;
import model.language.Platform;
import model.language.PlatformType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alex on 29/01/2017.
 */
public class IncompatibilityTest {

	ThrowableEvent thrEv;
	Game game;
	@Before
	public void setUp() {
		Language lang = new Language("test", 100, 0);
		lang.setAttributes(new ArrayList<Attribute>(){{ add(0, Platform.fromPlatformType(PlatformType.Linux));}});
		Incompatibility inc = new Incompatibility("test", 0, 0, 0, new ArrayList<Attribute>(){{ add(0, Platform.fromPlatformType(PlatformType.Linux));}});
		game = GameFactory.createGame(lang, 20);
		thrEv = inc.getThrowable(game);
	}

	@Test
	public void ProbabilityWhenSame() throws Exception {
		assertEquals(thrEv.probability, 100);
	}

	@Test
	public void ProbabilityWhenNotSame() throws Exception {
		Language lang = game.getLanguage();
		lang.setAttributes(new ArrayList<Attribute>(){{ add(Platform.fromPlatformType(PlatformType.Unix));}});
		game.setLanguage(lang);
		Incompatibility inc = new Incompatibility("test", 0, 0, 0, new ArrayList<Attribute>(){{ add(0, Platform.fromPlatformType(PlatformType.Linux));}});
		thrEv = inc.getThrowable(game);
		assertEquals(thrEv.probability, 0);
	}

	@Test
	public void ProbabilityNotEquals() throws Exception {
		assertNotEquals(thrEv.probability, 90);
	}



}