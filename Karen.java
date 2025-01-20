public class Karen extends Adventurer{
  int demands, demandsMax;
  String preferredWeapon;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Karen(String name, int hp, String weapon){
    super(name,hp);
    demandsMax = 10;
    demands = demandsMax/2;
    preferredWeapon = weapon;
  }

  public Karen(String name, int hp){
    this(name,hp,"Purse");
  }

  public Karen(String name){
    this(name,40);
  }

  public Karen(){
    this("Karen");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "demands";
  }

  public int getSpecial(){
    return demands;
  }

  public void setSpecial(int n){
    demands = n;
  }

  public int getSpecialMax(){
    return demandsMax;
  }

  /*Deal 1-3 damage to opponent, restores 2 demands*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*3)+1;
    other.applyDamage(damage);
    restoreSpecial(2);
    return "Let me show you how it's done, sweetie! "+ this + " attacked "+ other + " and dealt "+ damage +
    " points of damage.";
  }

  /*Deal 3-12 damage to opponent, only if demands is high enough.
  *Reduces demands by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (10);
      other.applyDamage(damage);
      return "Oh, no, no, no. This is unacceptable. I need to speak to your manager, right now! "+ this + " called her Manager!" +
      " She knocked out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "What do you mean my Manager is not here?. Instead, "+attack(other);
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return "You wouldn’t want to disappoint me, would you? " + this + " restores " + other + "'s " + other.restoreSpecial((int)(Math.random()*1)+1)+" "+other.getSpecialName();
  }

  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return "It's all about self-care! "+ this + " restores " + restoreSpecial((int)(Math.random()*1)+1)+" "+ getSpecialName()+ " and "+hp+" HP";
  }
}
