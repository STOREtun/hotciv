package hotciv.variants.EpsilonCiv;

import hotciv.framework.AttackStrategy;
import hotciv.framework.Die;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.Utility;

/**
 * Created by asger on 25/11/15.
 */
public class EpsilonAttackStrategy implements AttackStrategy {

    private Die die;

    public EpsilonAttackStrategy(Die die) {
        this.die = die;
    }

    @Override
    public boolean attackSuccessful(Position from, Position to, GameImpl game) {
        UnitImpl attackingUnit = (UnitImpl) game.getUnitAt(from);
        UnitImpl defendingUnit = (UnitImpl) game.getUnitAt(to);

        // basic combat strength
        int attackStrength = attackingUnit.getAttackingStrength();
        int defenseStrength = defendingUnit.getDefensiveStrength();

        // support factor
        Player attackingOwner = game.getPlayerInTurn();
        Player defendingOwner = game.getUnitAt(to).getOwner();
        attackStrength += Utility.getFriendlySupport(game, from, attackingOwner);
        defenseStrength += Utility.getFriendlySupport(game, to, defendingOwner);

        // terrain factor
        attackStrength *= Utility.getTerrainFactor(game, from);
        defenseStrength *= Utility.getTerrainFactor(game, to);

        // random factor
        attackStrength *= die.rollDie();
        defenseStrength *= die.rollDie();


        if(attackStrength > defenseStrength){
            return true;
        }
        return false;
    }

}
