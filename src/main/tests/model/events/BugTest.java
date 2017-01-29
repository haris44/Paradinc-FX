package model.events;

import factory.GameFactory;
import javafx.util.Pair;
import model.Game;
import model.language.Attribute;
import model.language.Language;
import model.language.Platform;
import model.language.PlatformType;
import org.junit.Before;
import org.junit.Test;

import javax.smartcardio.ATR;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alex on 29/01/2017.
 */
public class BugTest {

	ThrowableEvent thrEv;
	@Before
	public void setUp() {
		Language lang = new Language("test", 100, 0);
		ArrayList<Attribute> plt = new ArrayList<Attribute>(){{ add(0, Platform.fromPlatformType(PlatformType.Linux));}};
		lang.setAttributes(plt);
		Bug inc = new Bug("Out Of Memory", -10, -2, 0, plt , new Pair<>(20, 1.0), new Pair<>(0, 0.0));
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