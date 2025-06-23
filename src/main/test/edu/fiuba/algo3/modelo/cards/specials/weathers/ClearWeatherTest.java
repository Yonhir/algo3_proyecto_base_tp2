package edu.fiuba.algo3.modelo.cards.specials.weathers;

import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.units.modifiers.MoraleBoostModifier;
import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearWeatherTest {
    @Test
    public void testSeAplicaElClimaClearWeatherCorrectamenteEnLasFilasCloseCombat() {
        Weather clear = new ClearWeather("nombre", "descripcion");
        Weather bitingFrost = new BitingFrost("nombre", "descripcion");
        CloseCombat closeCombat1 = new CloseCombat();
        CloseCombat closeCombat2 = new CloseCombat();
        Unit carta = new Unit("nombre", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        closeCombat1.placeCard(carta);
        closeCombat2.placeCard(carta);

        bitingFrost.apply(carta, closeCombat1);
        bitingFrost.apply(carta, closeCombat2);

        clear.apply(carta, closeCombat1);
        clear.apply(carta, closeCombat2);

        assertEquals(5, carta.calculatePoints());
    }

    @Test
    public void testSeAplicaElClimaClearWeatherCorrectamenteEnLasFilasRanged() {
        Weather clear = new ClearWeather("nombre", "descripcion");
        Weather fog = new ImpenetrableFog("nombre", "descripcion");
        Ranged ranged1 = new Ranged();
        Ranged ranged2 = new Ranged();
        Unit carta = new Unit("nombre", "descripcion", 5, new RangedType(), List.of(new MoraleBoostModifier()));

        ranged1.placeCard(carta);
        ranged2.placeCard(carta);

        fog.apply(carta, ranged1);
        fog.apply(carta, ranged2);

        clear.apply(carta, ranged1);
        clear.apply(carta, ranged2);

        assertEquals(5, carta.calculatePoints());
    }

    @Test
    public void testSeAplicaElClimaClearWeatherCorrectamenteEnLasFilasSiege() {
        Weather clear = new ClearWeather("nombre", "descripcion");
        Weather rain = new TorrentialRain("nombre", "descripcion");
        Siege siege1 = new Siege();
        Siege siege2 = new Siege();
        Unit carta = new Unit("nombre", "descripcion", 7, new SiegeType(), List.of(new MoraleBoostModifier()));

        siege1.placeCard(carta);
        siege2.placeCard(carta);

        rain.apply(carta, siege1);
        rain.apply(carta, siege2);

        clear.apply(carta, siege1);
        clear.apply(carta, siege2);

        assertEquals(7, carta.calculatePoints());
    }

    @Test
    public void testLaCartaClearWeatherSeAplicaSobreTodoElTablero() {
        Weather clear = new ClearWeather("nombre", "descripcion");
        Weather rain = new TorrentialRain("nombre", "descripcion");
        CloseCombat closeCombat1 = new CloseCombat();
        CloseCombat closeCombat2 = new CloseCombat();
        Ranged ranged1 = new Ranged();
        Ranged ranged2 = new Ranged();
        Siege siege1 = new Siege();
        Siege siege2 = new Siege();
        Section specialZone = new SpecialZone(closeCombat1, ranged1, siege1, closeCombat2, ranged2, siege2);
        Unit cartaCC = new Unit("nombre", "descripcion", 7, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Unit cartaR = new Unit("nombre", "descripcion", 5, new RangedType(), List.of(new MoraleBoostModifier()));
        Unit cartaS = new Unit("nombre", "descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));

        closeCombat1.placeCard(cartaCC);
        closeCombat2.placeCard(cartaCC);
        ranged1.placeCard(cartaR);
        ranged2.placeCard(cartaR);
        siege1.placeCard(cartaS);
        siege2.placeCard(cartaS);

        rain.play(specialZone);
        clear.play(specialZone);

        assertEquals(7, cartaCC.calculatePoints());
        assertEquals(5, cartaR.calculatePoints());
        assertEquals(6, cartaS.calculatePoints());
    }
}
