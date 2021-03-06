package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Implementation of the Draw Two card in Uno.
 * 
 * @author danno
 *
 */
public class StarCard extends BasicCard {

  /**
   * Initializes a Star  card with the given color and symbol.
   * 
   * @param color the color of the resulting card.
   * @param symbol the symbol of the resulting card.
   */
  public StarCard(Color color) {
    super(color, Symbol.STAR);
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    super.executeAction(game, ctrl);
    game.addToDrawWell(5);
    game.skipPlayer();
  }
}
