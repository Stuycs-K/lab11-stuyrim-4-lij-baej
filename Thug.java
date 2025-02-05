public class Thug extends Adventurer{
  int charisma, charismaMax;
  String preferredWeapon;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Thug(String name, int hp, String weapon){
    super(name,hp);
    charismaMax = 10;
    charisma = charismaMax/2;
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
    return "charisma";
  }

  public int getSpecial(){
    return charisma;
  }

  public void setSpecial(int n){
    charisma = n;
  }

  public int getSpecialMax(){
    return charismaMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 charisma*/
  public String attack(Adventurer other){
    int damage = (int)(multiplier() * (int)(Math.random()*9)+2);
    other.applyDamage(damage);
    restoreSpecial(2);
    if (multiplier() != 1.0) {
      setRounds(rounds()+1);
      if (rounds() >= 2) {
        setMultiplier(1.0);
        setRounds(0);
      }
    }
    return "Take this! " + this + " attacked "+ other + " and dealt "+ damage +
    " points of damage. ";
  }

  /*Deal 3-12 damage to opponent, only if charisma is high enough.
  *Reduces charisma by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)(multiplier()*(15));
      other.applyDamage(damage);
      if (multiplier() != 1.0) {
        setRounds(rounds()+1);
        if (rounds() >= 2) {
          setMultiplier(1.0);
          setRounds(0);
        }
      }
      return "Watch out for my pookie! "+ this + " striked their "+preferredWeapon+ " to " + other + " He knocked out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough charisma to use the strike. Instead "+attack(other);
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return this + " gives a encouraging yell to "+other+" and restores "
    + other.restoreSpecial((int)(Math.random()*2)+1)+" "+other.getSpecialName();
  }

  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" lifts his collar and fixes his fit to restore "+restoreSpecial((int)(Math.random()*2)+1)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
