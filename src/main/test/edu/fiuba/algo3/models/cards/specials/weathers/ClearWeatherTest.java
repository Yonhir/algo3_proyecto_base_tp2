package edu.fiuba.algo3.models.cards.specials.weathers;

import edu.fiuba.algo3.models.colors.*;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.Section;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearWeatherTest {
    Weather clear;
    Weather bitingFrost;
    Weather fog;
    Weather rain;
    CloseCombat closeCombat1;
    CloseCombat closeCombat2;
    Ranged ranged1;
    Ranged ranged2;
    Siege siege1;
    Siege siege2;

    Round round;
    Player player1;
    Player player2;
    DiscardPile discardPile1;
    DiscardPile discardPile2;

    @BeforeEach
    void setUp() {
        player1 = new Player("nombre", new Blue());
        player2 = new Player("nombre", new Red());

        discardPile1 = player1.getDiscardPile();
        discardPile2 = player2.getDiscardPile();
        closeCombat1 = player1.getCloseCombatRow();
        closeCombat2 = player2.getCloseCombatRow();
        ranged1 = player1.getRangedRow();
        ranged2 = player2.getRangedRow();
        siege1 = player1.getSiegeRow();
        siege2 = player2.getSiegeRow();

        clear = new ClearWeather("nombre", "descripcion");
        bitingFrost = new BitingFrost("nombre", "descripcion");
        fog = new ImpenetrableFog("nombre", "descripcion");
        rain = new TorrentialRain("nombre", "descripcion");

        clear.setColor(new Blue());
        bitingFrost.setColor(new Blue());
        fog.setColor(new Blue());
        rain.setColor(new Blue());

        round = new Round(player1, player2);
    }

    @Test
    public void testSeAplicaElClimaClearWeatherCorrectamenteEnLasFilasCloseCombat() {
        Unit carta = new Unit("nombre", "descripcion", 5, new CloseCombatType(), List.of());
        Unit carta2 = new Unit("nombre", "descripcion", 5, new CloseCombatType(), List.of());
        carta.setColor(new Blue());
        carta2.setColor(new Red());

        closeCombat1.placeCard(carta, round);
        closeCombat2.placeCard(carta2, round);

        bitingFrost.apply(carta, closeCombat1);
        bitingFrost.apply(carta2, closeCombat2);

        clear.apply(carta, closeCombat1);
        clear.apply(carta2, closeCombat2);

        assertEquals(5, carta.calculatePoints());
        assertEquals(5, carta2.calculatePoints());
    }

    @Test
    public void testSeAplicaElClimaClearWeatherCorrectamenteEnLasFilasRanged() {
        Unit carta = new Unit("nombre", "descripcion", 5, new RangedType(), List.of());
        Unit carta2 = new Unit("nombre", "descripcion", 5, new RangedType(), List.of());
        carta.setColor(new Blue());
        carta2.setColor(new Red());

        ranged1.placeCard(carta, round);
        ranged2.placeCard(carta2, round);

        fog.apply(carta, ranged1);
        fog.apply(carta2, ranged2);

        clear.apply(carta, ranged1);
        clear.apply(carta2, ranged2);

        assertEquals(5, carta.calculatePoints());
        assertEquals(5, carta2.calculatePoints());
    }

    @Test
    public void testSeAplicaElClimaClearWeatherCorrectamenteEnLasFilasSiege() {
        Unit carta = new Unit("nombre", "descripcion", 5, new SiegeType(), List.of());
        Unit carta2 = new Unit("nombre", "descripcion", 5, new SiegeType(), List.of());
        carta.setColor(new Blue());
        carta2.setColor(new Red());

        siege1.placeCard(carta, round);
        siege2.placeCard(carta2, round);

        rain.apply(carta, siege1);
        rain.apply(carta2, siege2);

        clear.apply(carta, siege1);
        clear.apply(carta2, siege2);

        assertEquals(5, carta.calculatePoints());
        assertEquals(5, carta2.calculatePoints());
    }

    @Test
    public void testLaCartaClearWeatherSeAplicaSobreTodoElTablero() {
        Section specialZone = new SpecialZone(closeCombat1, ranged1, siege1, closeCombat2, ranged2, siege2, discardPile1, discardPile2);
        Unit cartaCC = new Unit("nombre", "descripcion", 7, new CloseCombatType(), List.of());
        Unit cartaR = new Unit("nombre", "descripcion", 5, new RangedType(), List.of());
        Unit cartaS = new Unit("nombre", "descripcion", 6, new SiegeType(), List.of());
        Unit cartaCC2 = new Unit("nombre", "descripcion", 7, new CloseCombatType(), List.of());
        Unit cartaR2 = new Unit("nombre", "descripcion", 5, new RangedType(), List.of());
        Unit cartaS2 = new Unit("nombre", "descripcion", 6, new SiegeType(), List.of());

        cartaCC.setColor(new Blue());
        cartaR.setColor(new Blue());
        cartaS.setColor(new Blue());
        cartaCC2.setColor(new Red());
        cartaR2.setColor(new Red());
        cartaS2.setColor(new Red());

        closeCombat1.placeCard(cartaCC, round);
        closeCombat2.placeCard(cartaCC2, round);
        ranged1.placeCard(cartaR, round);
        ranged2.placeCard(cartaR2, round);
        siege1.placeCard(cartaS, round);
        siege2.placeCard(cartaS2, round);

        bitingFrost.play(specialZone);
        fog.play(specialZone);
        rain.play(specialZone);
        clear.play(specialZone);

        assertEquals(7, cartaCC.calculatePoints());
        assertEquals(5, cartaR.calculatePoints());
        assertEquals(6, cartaS.calculatePoints());
        assertEquals(7, cartaCC2.calculatePoints());
        assertEquals(5, cartaR2.calculatePoints());
        assertEquals(6, cartaS2.calculatePoints());
    }
}
