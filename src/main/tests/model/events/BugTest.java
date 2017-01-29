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
	Game game;
	Bug inc;
	@Before
	public void setUp() {

		// Create language
		Language lang = new Language("test", 0, 0);
		lang.setRobustness(20);
		lang.setFacility(35);
		ArrayList<Attribute> plt = new ArrayList<Attribute>(){{ add(0, Platform.fromPlatformType(PlatformType.Linux));}};
		lang.setAttributes(plt);

		// create bug
		inc = new Bug("Out Of Memory", -10, -2, 0, plt , new Pair<>(15, 3.0), new Pair<>(30, 5.0));
		game = GameFactory.createGame(lang, 20);
		thrEv = inc.getThrowable(game);
	}

	@Test
	public void MaxProba() throws Exception {
		Language lang = game.getLanguage();
		lang.setFacility(30);
		lang.setRobustness(15);
		thrEv = inc.getThrowable(game);
		assertEquals(thrEv.probability, 100);
	}

	@Test
	public void aleaProbaWithGoodPlateform() throws Exception {
		assertEquals(thrEv.probability, 86);
	}

	@Test
	public void aleaProbaWithBadPlateform() throws Exception {
		ArrayList<Attribute> plt = new ArrayList<Attribute>(){{ add(0, Platform.fromPlatformType(PlatformType.Unix));}};
		inc = new Bug("Out Of Memory", -10, -2, 0, plt , new Pair<>(15, 3.0), new Pair<>(30, 5.0));
		thrEv = inc.getThrowable(game);
		assertEquals(thrEv.probability, 53);
	}

}