package edu.fiuba.algo3.models.sections;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.specials.weathers.ClearWeather;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.specials.Scorch;
import edu.fiuba.algo3.models.cards.specials.weathers.Weather;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.SpecialType;

import java.util.ArrayList;
import java.util.List;

public class SpecialZone extends Observable implements Section {
    private final List<CloseCombat> closeCombatRows;
    private final List<Ranged> rangedRows;
    private final List<Siege> siegeRows;
    private final SpecialType sectionType;
    private final List<Card> weathersCards;
    private final DiscardPile aDiscardPile;
    private final DiscardPile otherDicardPile;


    public SpecialZone(CloseCombat aPlayerCloseCombat, Ranged aPlayerRangedRow, Siege aPlayerSiegeRow, CloseCombat otherPlayerCloseCombat, Ranged otherPlayerRangedRow, Siege otherPlayerSiegeRow, DiscardPile aDiscardPile, DiscardPile otherDicardPile) {
        if(aPlayerCloseCombat.equals(otherPlayerCloseCombat) || aPlayerRangedRow.equals(otherPlayerRangedRow) || aPlayerSiegeRow.equals(otherPlayerSiegeRow)) {
            throw new IllegalArgumentException("Close combat, ranged and siege rows must be different");
        }
        this.closeCombatRows = List.of(aPlayerCloseCombat, otherPlayerCloseCombat);
        this.rangedRows = List.of(aPlayerRangedRow, otherPlayerRangedRow);
        this.siegeRows = List.of(aPlayerSiegeRow, otherPlayerSiegeRow);
        this.sectionType = new SpecialType();
        this.weathersCards = new ArrayList<Card>();
        this.aDiscardPile = aDiscardPile;
        this.otherDicardPile = otherDicardPile;
    }

    @Override
    public void placeCard(Card card, Round round) {
        card.verifySectionType(this.sectionType);
        card.play(this);
        round.playerPlayedCard();
    }

    public void applyCloseCombatWeather(Weather weather) {
        for (CloseCombat row : closeCombatRows) {
            row.applyWeather(weather);
        }
    }

    public void applyRangedWeather(Weather weather) {
        for (Ranged row : rangedRows) {
            row.applyWeather(weather);
        }
    }

    public void applySiegeWeather(Weather weather) {
        for (Siege row : siegeRows) {
            row.applyWeather(weather);
        }
    }

    public void addCard(Card card){
        this.weathersCards.add(card);
    }

    public void clearZone() {
        for(Card weather: weathersCards){
            aDiscardPile.addCardIfHasSameColor(weather);
            otherDicardPile.addCardIfHasSameColor(weather);
        }
        new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima").play(this);
        weathersCards.clear();
    }

    public void applyScorchInCloseCombat(Scorch scorch) {
        for (CloseCombat row : closeCombatRows) {
            row.applyScorch(scorch);
        }
    }

    public void applyScorchInRanged(Scorch scorch) {
        for (Ranged row : rangedRows) {
            row.applyScorch(scorch);
        }
    }

    public void applyScorchInSiege(Scorch scorch) {
        for (Siege row : siegeRows) {
            row.applyScorch(scorch);
        }
    }

    public void applyScorchInAllRows(Scorch scorch) {
        for (CloseCombat row : closeCombatRows) {
            row.findStrongestCardWithoutHeroModifier(scorch);
        }

        for (Ranged row : rangedRows) {
            row.findStrongestCardWithoutHeroModifier(scorch);
        }

        for (Siege row : siegeRows) {
            row.findStrongestCardWithoutHeroModifier(scorch);
        }

        applyScorchInCloseCombat(scorch);
        applyScorchInRanged(scorch);
        applyScorchInSiege(scorch);

        aDiscardPile.addCardIfHasSameColor(scorch);
        otherDicardPile.addCardIfHasSameColor(scorch);
    }

    public List<Card> getWeathersCards() {
        return weathersCards;
    }
}
