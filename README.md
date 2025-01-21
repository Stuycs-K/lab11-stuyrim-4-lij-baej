[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Road Rage: Fury

## Features

Make a clear list of features that work/dont work

:white_check_mark: This feature works.

:question: This feature works partially.

:ballot_box_with_check: This extra (beyond the things that the lab was supposed to do) feature works.

:x: This required feature does not work.

:beetle: This is a bug that affects the game.

<br/><br/>

:white_check_mark: play with a group of 3 different types adventurers

:white_check_mark: play against 1-3 randomly chosen adventurer opponents

:white_check_mark: use attack/special operations on your opponents

:white_check_mark: use support operations on your team

:white_check_mark: program ends when the user choosed to quit, or all enemies is defeated, or the entire party is defeated. Contains win/lose screen.

:white_check_mark: display the results of the attack/special/support inside your border

:white_check_mark: Contains a special boss class adventurer for when a single opponent is selected.

:ballot_box_with_check: Display the past several actions by keeping a list of the prior actions in some kind of list.

:ballot_box_with_check: Special attack types, including AOE attack / DOT damage.


## Adventurer Subclasses

replace this with your documentation for your two Adventurer subclasses. If you modify or replace the provided CodeWarrior class, please provide documentation for that as well.


### Thug
![Alt text](thug.png?raw=true "Title" )
> "Better pray, 'cause when you step on my block, you won’t see the next day!"

| Stats | Description |
| :---- | :---------- |
| Max HP | 30 |
| Weapon | Baseball Bat |
| Special | Charisma |
| Max Special | 10 |
| Special Attack | Strike \ Deals 15 damage to opponent \ Costs 8 Charisma|
| Attack | Deals 2-10 damage to opponent \ Restores 2 Anger |
| Self-Heal | Restores 1-2 special and 1 HP to self |
| Support (other) | Restores 1-2 special to other |


### Drug Dealer

![Alt text](drugdealer.png?raw=true "Title" )
> "I have the control here...don't cross the line."

| Stats | Description |
| :---- | :---------- |
| Max HP | 20 |
| Weapon | Syringe |
| Special | Powder |
| Max Special | 30 |
| Special Attack | Sprinkle \ Gives a 150% damage multiplier to choosen player \ Costs 5 Powder |
| Attack | Deals 2-8 damage to opponent \ Restores 2 Powder \ If opponent is Boss, inflicts poison status (-1 HP every turn)|
| Self-Heal | Restores 2 special and 5 hp to self |
| Support (other) | Restores 5 - 10 HP to other |

### Karen

![Alt text](karen.png?raw=true "Title" )
> "Excuse me, but I know my rights, and this street is my property."

| Stats | Description |
| :---- | :---------- |
| Max HP | 40 |
| Weapon | Purse |
| Special | Demands |
| Max Special | 10 |
| Special Attack | Call the Manager! \ Deals 10 damage to all opponents \ Costs 8 Demands |
| Attack | Deals 1-3 damage to opponent \ Restores 2 Demands |
| Self-Heal | Restores 1-2 special |
| Support (other) | Restores 1 special to other |

### Boss: Drunkard

![Alt text](drunkard.png?raw=true "Title" )
> "Jessica, darling... I'm here in your street! Where’s my Timothy? Hmmph... give him back to me! I need to see his smile, and play ball with him just one more time!"

| Stats | Description |
| :---- | :---------- |
| Max HP | 100 |
| Weapon | Broken Bottle |
| Special | Intoxication |
| Max Special | 55 |
| Special Attack | Spray \ Deals 3-12 damage to opponent \ Costs 8 Intoxications |
| Attack | Deal 2-7 damage to opponent \ Restores 2 Intoxication |
| Self-Heal | Restores 1-2 special and 1 HP to self |
| Support (other) | Restores 1-2 special to other |
