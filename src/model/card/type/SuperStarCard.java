package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Implementation of the Skip card in Uno.
 * 
 * @author danno
 *
 */
public class SuperStarCard extends AbstractCard {

  /**
   * Initializes a wild card with no color and a basic wild card symbol
   */
  public SuperStarCard() {
    super(Color.NONE, Symbol.WILD);
  }

  /**
   * Initializes a wild card with the given color and symbol. Meant for class that inherit from
   * this class.
   * 
   * @param color the color of the resulting card.
   * @param symbol the symbol of the resulting card.
   */
  protected SuperStarCard(Color color, Symbol symbol) {
    super(color, symbol);
  }

  @Override
  public boolean isFirstPlayable() {
    return false;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    super.executeAction(game, ctrl);
    Color nextColor = game.getCurrentPlayer().selectColor(game, ctrl);
    game.playCard(new UsedWildCard(nextColor, Symbol.WILD), ctrl);
    game.skipPlayer();
    game.invertDirection();
    game.skipPlayer();
  }

  @Override
  public boolean isPlayableOver(ICard otherCard) {
    return true;
  }
}

