package model.card.type;

public class SuperStarBuilder implements ICardBuilder {

  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return new SuperStarCard();
  }

}
