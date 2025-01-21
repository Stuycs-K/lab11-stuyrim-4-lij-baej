public class Thug extends Adventurer{
  int anger, angerMax;
  String preferredWeapon;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Thug(String name, int hp, String weapon){
    super(name,hp);
    angerMax = 12;
    anger = angerMax/2;
    preferredWeapon = weapon;
  }

  public Thug(String name, int hp){
    this(name,hp,"Baseball Bat");
  }

  public Thug(String name){
    this(name,30);
  }

  public Thug(){
    this("Thug");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "anger";
  }

  public int getSpecial(){
    return anger;
  }

  public void setSpecial(int n){
    anger = n;
  }

  public int getSpecialMax(){
    return angerMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 anger*/
  public String attack(Adventurer other){
    int damage = (int)(multiplier() * (int)(Math.random()*6)+2);
    other.applyDamage(damage);
    restoreSpecial(2);
    if (multiplier() != 1.0) {
      setRounds(rounds()-1);
    }
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage. He then lets out a thick roar.";
  }

  /*Deal 3-12 damage to opponent, only if anger is high enough.
  *Reduces anger by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)(multiplier()*(15));
      other.applyDamage(damage);
      if (multiplier() != 1.0) {
        setRounds(rounds()-1);
      }
      return this + " striked their "+preferredWeapon+ " to " + other + " He knocked out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough anger to use the strike. Instead "+attack(other);
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return "Gives a encouraging yell to "+other+" and restores "
    + other.restoreSpecial((int)(Math.random()*1)+1)+" "+other.getSpecialName();
  }

  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" yells out to restore"+restoreSpecial((int)(Math.random()*1)+1)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
