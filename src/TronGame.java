import java.io.*;
import java.util.ArrayList;

import types.Node;

public class TronGame {

    public static void main(String[] args) throws IOException {

        Graph game = new Graph();
        File in = null;
        if (args.length > 0) {
            in = new File(args[0]);
        } else {
            System.err.println("Did not receive a file from command line;");
            System.exit(0);
        }
        BufferedReader reader = new BufferedReader(new FileReader(in));
        String readLine = null;

        ArrayList<Node> tempQ = new ArrayList<>();
        Node tempNode;
        int lineNumb = -1;

        while ((readLine = reader.readLine()) != null) {
            char[] individualWords = readLine.toCharArray();

            // Save row and column count for future use
            if (individualWords[0] != '#'){

                StringBuilder s = new StringBuilder();
                int i = 0;
                while (Character.isDigit(individualWords[i])){
                    s.append(individualWords[i]);
                    i++;
                }
                
                // Skip white space.
                i++;
                game.setRows(Integer.valueOf(s.toString()));

                s.setLength(0);
                while (i<individualWords.length && Character.isDigit(individualWords[i])){
                    s.append(individualWords[i]);
                    i++;
                }
                game.setColumns(Integer.valueOf(s.toString()));
            } else {
                for (int i = 0; i< game.getColumns(); i++){
                    //create new node then add to game board array list
                    tempNode = new Node(new Integer[]{i, lineNumb},
                            individualWords[i]);

                    if (tempNode.val == 'T'){
                        game.setTron(tempNode);
                    }
                    else if (tempNode.val == '#' || Character.isSpaceChar(tempNode.val) || tempNode.val == 'G'){
                        //do nothing
                    }
                    else {
                        game.bugs.add(tempNode);
                    }

                    game.board.add(tempNode);

                    // Add to temp queue for linking to other Nodes. tempQ.size
                    // returns column so, this should only evaluate true if
                    // there has been a whole row added.
                    if (tempQ.size() > i){
                        //sets upper Node to
                        
                        tempQ.get(i).lower = tempNode;
                        tempNode.upper = tempQ.get(i);

                        // evaluates true when the Node is not the first of the row.
                        if ((i-1) >= 0){
                            
                            tempQ.get((i-1)).next = tempNode;
                            tempNode.prev = tempQ.get((i-1));
                        }
                    }

                    if (tempQ.size() == game.getColumns()){
                        tempQ.set(i,tempNode);
                    }
                    else{
                        tempQ.add(tempNode);
                    }
                }
            }
            lineNumb++;
        }

        reader.close();

        game.printBoard();

        game.startGame();

        game.printBugs();

    }
}
