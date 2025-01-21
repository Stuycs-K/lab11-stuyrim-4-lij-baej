import java.util.*;

public class Boss extends Adventurer{
  int intoxication, intoxicationMax;
  String preferredWeapon;

  public static String intro = "Jessica, darling... I'm here in your street! Where’s my Timothy? Hmmph... give him back to me!";
  String[] battleStory = {
    "Oh yauh? You guys think you can stop this daddy? My family... hic... needs me!",
    "Come on, it was just a punch, an accident! Cruel World, I didn't mean it!",
    "Jessica... I know we’re not together anymore, but... I still... hic still think about the good times, ya know? hic... I... I never meant to hurt you and Timothy..."
    };

  public static String deathMessage = "I just wanna see him smile... play ball one more time with me... hic... Timothy... stay... thud..";
  int storyCounter = 0;
  boolean FirstMove = true;
  ArrayList<Adventurer>sprayList = new ArrayList<Adventurer>();

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Boss(String name, int hp, String weapon){
    super(name,hp);
    intoxicationMax = 55;
    intoxication = intoxicationMax/2;
    preferredWeapon = weapon;
  }

  public Boss(String name, int hp){
    this(name,hp,"Broken Bottle");
  }

  public Boss(String name){
    this(name,100);
  }

  public Boss(){
    this("Drunkard");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "intoxication";
  }

  public int getSpecial(){
    return intoxication;
  }

  public void setSpecial(int n){
    intoxication = n;
  }

  public int getSpecialMax(){
    return intoxicationMax;
  }

  public void dealDotDamage(){
    int damage = (4);

    for (Adventurer member : sprayList){
      member.applyDamage(damage);
    }
  }

  /*Deal 2-7 damage to opponent, restores 2 intoxication*/
  public String attack(Adventurer other){
    storyCounter ++;
    if (storyCounter > 2){
      storyCounter = 0;
    }


    dealDotDamage();
    int damage = (int)(multiplier()* ((int)(Math.random()*6)+2));
    other.applyDamage(damage);
    restoreSpecial(2);
    if (multiplier() != 1.0) {
      setRounds(rounds()+1);
      if (rounds() >= 2) {
        setMultiplier(1.0);
        setRounds(0);
      }
    }


    return battleStory[storyCounter]+"Heh, gotcha, didn't I? Just... just don't make me swing again! "+ this + " striked their "+preferredWeapon+ " and he knocked out "+ other + " and dealt "+ damage +
    " points of damage.";
  }

  /*Deal 3-12 damage to opponent, only if intoxication is high enough.
  *Reduces intoxication by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      dealDotDamage();
      setSpecial(getSpecial()-8);
      int damage = (int)(multiplier() * ((int)(Math.random()*10)+3));

      sprayList.add(other);
      other.applyDamage(damage);
      if (multiplier() != 1.0) {
        setRounds(rounds()+1);
        if (rounds() >= 2) {
          setMultiplier(1.0);
          setRounds(0);
        }
      }
      return "This is what you get for messin' with me, buddy! A little liquid courage for ya! " + this + " unzips his pants and aims to urinate toward "+ other +", laughing uncontrollably, dealing "+ damage +" points of damage."+ other +" is drenched, and will recieve 4 damage each turn. Yuck!";
    }else{
      return attack(other);
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    dealDotDamage();
    return "Gives a encouraging yell to "+other+" and restores "
    + other.restoreSpecial((int)(Math.random()*2)+1)+" "+other.getSpecialName();
  }

  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    dealDotDamage();
    int hp = 1;
    setHP(getHP()+hp);
    return "I'm feeling it now. Nothing can slow me down after a drink like that! Let's get crazy, shall we? "+ this+" chugs gallon of whisky to restore "+restoreSpecial((int)(Math.random()*2)+1)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
