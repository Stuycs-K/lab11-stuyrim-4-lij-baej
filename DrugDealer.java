public class DrugDealer extends Adventurer{
  int powder, powderMax;
  String preferredWeapon;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public DrugDealer(String name, int hp, String weapon){
    super(name,hp);
    powderMax = 30;
    powder = powderMax/2;
    preferredWeapon = weapon;
  }

  public DrugDealer(String name, int hp){
    this(name,hp,"Syringe");
  }

  public DrugDealer(String name){
    this(name,20);
  }

  public DrugDealer(){
    this("Drug Dealer");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "powder";
  }

  public int getSpecial(){
    return powder;
  }

  public void setSpecial(int n){
    powder = n;
  }

  public int getSpecialMax(){
    return powderMax;
  }

  /*Deal 2-8 damage to opponent, restores 2 powder, if opponent is Boss, inflict poison status (-1 HP every turn)*/
  public String attack(Adventurer other){
    String result;
    int damage = (int)(multiplier() * (int)(Math.random()*7 + 2));
    other.applyDamage(damage);
    restoreSpecial(2);
    result = this + " stabbed their unsanitary "+preferredWeapon+
    " into " + other + " and injects an unknown liquid into them, dealing "+ damage +
    " points of damage.";
    if (other.getName().equals("Drunkard")){
      result += " Drunkard gets an infection, he recieves 1 point of damage every turn.";
      other.setPoison(true);
    }
    if (multiplier() != 1.0) {
      setRounds(rounds()+1);
      if (rounds() >= 2) {
        setMultiplier(1.0);
        setRounds(0);
      }
    }
    return result;
  }

  /*Gives a x150% damage buff to choosen player, only if powder is high enough.
  *Reduces powder by 5.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 5 && other.multiplier() == 1.0){
      setSpecial(getSpecial()-5);
      other.setMultiplier(1.5);
      return this + " injected "+preferredWeapon+
      " with powder into " + other +
      ". " + other + " suddenly feels rejuvenated. They now deals 150% more damage for two turns (unstackable).";
    }
    else if (getSpecial() < 5) {
      return "Not enough powder to use the strike. Instead "+attack(other);
    } else {
      return "Can't reinject powder. Instead " +attack(other);
    }

  }
  /*Restores 2 special and 5 hp to self.*/
  public String support(){
    int hp = 5;
    setHP(getHP() + hp);
    return this+" snorts some powder to restore "+restoreSpecial(2)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }

  /*Restores 5 - 10 hp to other */
  public String support(Adventurer other){
    int hp = (int)(int)(Math.random()*6 + 5);
    other.setHP(other.getHP() + hp);
    return this + " blows some mysterious substance into " + other + "'s face and restores " + hp + " HP.";
  }

}
