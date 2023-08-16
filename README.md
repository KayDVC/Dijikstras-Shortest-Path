## Table of Contents
1. [Objective](#objective)
2. [Use](#use)
    * [Input Files](#input-files)
    * [Example Run](#example-run)
3. [Final Thoughts](#final-thoughts)


## Objective
The objective of this project was to implement Dijkstra's algorithm in a casual application. This was completed in 2021 as 
an assignment.

The application features a movable character, “Tron”, and multiple “bugs” attempting to move closer to Tron based on pathing calculated using Dijkstra’s Algorithm.

- _Note_ : App currently supports singular move.

## Use

Requires Java. Tested `openjdk 17.0.8`.

### Input Files

The app requires the use of an input text file. The expected format is 

<pre>
R C
######
#G  T#
#    #
#b   #
#  b #
######
</pre>

Where:
- R = The number of **rows** to on the game board
- C = The number of **columns** to on the game board
- A - representation of borders or obstacles on the board.
- G - A representation of a **goal** point for Tron.
- T - A representation of **Tron**, the movable character.
- [a-z] - A representation of different bugs on the board.

_Notes_:
 - The game board is expected to be surrounded by border.
 - Bugs can be named using any alphanumeric. Its identifier must be 
   1 character long and should be unique.

_See examples in `samples`_

### Example Run

```bash
cd src
javac TronGame.java
java TronGame ../samples/One.txt

Game board: 
-----------

  012345
0 ######
1 #G  T#
2 #    #
3 #b   #
4 #  a #
5 ######

Please enter your move [u(p), d(own), l(eft), or r(ight)]: U
	❌ Failed to move Tron up

	Invalid entry! Try again.

Please enter your move [u(p), d(own), l(eft), or r(ight)]: R
	❌ Failed to move Tron right

	Invalid entry! Try again.

Please enter your move [u(p), d(own), l(eft), or r(ight)]: D
	✅ Moved Tron down


Game board: 
-----------

  012345
0 ######
1 #G   #
2 #   T#
3 #b   #
4 #  a #
5 ######

Bugs   Move  Distance  | Path To Tron
-------------------------------------
👾 a : ur    3         | (3, 4) (4, 4), (4, 3), (4, 2)
👾 b : ur    4         | (1, 3) (2, 3), (3, 3), (4, 3), (4, 2)

```

Don't degrade yourself by using Eclipse; [here's a tutorial](https://code.visualstudio.com/docs/java/java-tutorial) on how to setup VS Code for Java. Basic, but at least you'll be able to sleep at night with dignity and self-respect.

## Final Thoughts

If I was to recreate this project I would implement the full game, not just a single move. I initially wanted to grasp the main principles and move on; something that didn't require multiple moves. I would also design the system to align more with OOP principles such as encapsulation of data.

All in all, revisiting this project and cleaning it up has been a treat. Coming back to this app that was such a hurdle for me before and easily refactoring, patching, and reworking major components has helped me realize how much I've grown – both in my capacity as a software developer and in my knowledge of theoretical computation. On to bigger things!

If used as "inspiration," please link back to this repo.

Thanks,

\- Kay

