package model.card.type;

public class StarCardBuilder implements ICardBuilder {

  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return new StarCard(aColor);
  }

}