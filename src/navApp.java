/**
 *Driver class for the main application
 *
 * @author Angelu Ferdinand A. Garcia
 * @version Feb 22, 2019
 */
import java.text.DecimalFormat;
import java.util.Scanner;
public class navApp
{
    static int modeOfTransportation = 0;
    static int totalNodes = 20;
    static int startVertex;
    static double velocity ;

    static establishments[] e = new establishments[10];
    static quickSort q = new quickSort();
    static DecimalFormat df = new DecimalFormat("###.##");

    static BST searchTree = new BST();
    static Node root = null;//initially root is null

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("[0] On-Foot");
        System.out.println("[1] Vehicle");
        System.out.print("Select mode of transportation: ");
        modeOfTransportation = in.nextInt();

        if (modeOfTransportation == 0){velocity = 84;}//relevant to calculate ETA
        else if (modeOfTransportation == 1){velocity = 667;}

        displayStreetIntersections();
        System.out.print("Select starting point: ");//choose startvertex
        startVertex = in.nextInt();

        dijkstra d = new dijkstra(modeOfTransportation,startVertex, totalNodes);//pass the values to constructors

        e[0] = new establishments("Restaurants");//naming the group
        e[0].addSpecificEstablishment("McDonalds");//adding a sub group
        e[0].addLocationInMap(14);//setting vertex location of sub group
        root = searchTree.insert(root,"McDonalds",14);//added to the binary search tree for search functionality

        e[0].addSpecificEstablishment("Burger King");
        e[0].addLocationInMap(16);
        root = searchTree.insert(root,"Burger King",16);

        e[1] = new establishments("Hotels");
        e[1].addSpecificEstablishment("Grand City Hotel");
        e[1].addLocationInMap(7);
        root = searchTree.insert(root,"Grand City Hotel",7);

        e[2] = new establishments("Bars");
        e[2].addSpecificEstablishment("Willsbar Family KTV");
        e[2].addLocationInMap(8);
        root = searchTree.insert(root,"Willsbar Family KTV",8);

        e[3] = new establishments("Coffee");
        e[3].addSpecificEstablishment("Brew Berry Cafe");
        e[3].addLocationInMap(12);
        root = searchTree.insert(root,"Willsbar Family KTV",8);

        e[4] = new establishments("Banks");
        e[4].addSpecificEstablishment("Post Bank");
        e[4].addLocationInMap(10);
        root = searchTree.insert(root,"Post Bank",10);

        e[5] = new establishments("Parking Lots");
        e[5].addSpecificEstablishment("Golden Friendship Park");
        e[5].addLocationInMap(2);
        root = searchTree.insert(root,"Golden Friendship Park",2);

        e[6] = new establishments("Post Offices");
        e[6].addSpecificEstablishment("Philippine Postal Corporation");
        e[6].addLocationInMap(10);
        root = searchTree.insert(root,"Philippine Postal Corporation",10);

        e[7] = new establishments("Gas Stations");
        e[7].addSpecificEstablishment("Sample Gas Station");
        e[7].addLocationInMap(15);
        root = searchTree.insert(root,"Sample Gas Station",15);

        e[8] = new establishments("Groceries");
        e[8].addSpecificEstablishment("Shoppe 24");
        e[8].addLocationInMap(18);
        root = searchTree.insert(root,"Shoppe 24",18);

        e[9] = new establishments("Hospitals");
        e[9].addSpecificEstablishment("Sample Hospital");
        e[9].addLocationInMap(4);
        root = searchTree.insert(root,"Sample Hospital",4);
/*        e[0].printLocations();
        System.out.println("Burger King is "+e[0].getVertex("Burger King"));*/

        displayMainMenu(in, d, e);
    }

    /**
     * Outputs to user possible junctions as destination
     */
    public static void displayStreetIntersections()
    {
        System.out.println();
        System.out.println(
                "[0] Rizal - Neri\n" +
                "[1] Tiano - Neri\n" +
                "[2] Velez - Neri\n" +
                "[3] Pabayo - Neri\n" +
                "[4] Corrales - Neri\n" +
                "[5] Rizal - Abejuela\n" +
                "[6] Tiano - Abejuela\n" +
                "[7] Velez - Abejuela\n" +
                "[8] Pabayo - Abejuela\n" +
                "[9] Corrales - Abejuela\n" +
                "[10] Rizal - Chavez\n" +
                "[11] Tiano - Chavez\n" +
                "[12] Velez - Chavez\n" +
                "[13] Pabayo - Chavez\n" +
                "[14] Corrales - Chavez\n" +
                "[15] Rizal - Hayes\n" +
                "[16] Tiano - Hayes\n" +
                "[17] Velez - Hayes\n" +
                "[18] Pabayo - Hayes\n" +
                "[19] Corrales - Hayes"
        );
    }

    /**
     * Main menu where the main functionalities are displayed
     *
     * @param in For storing user input
     * @param d Instance of dijkstra; used to retrieve solutions of dijkstra's algorithm
     * @param e Instance of establishments; used to add and retrieve information
     */

    public static void displayMainMenu(Scanner in, dijkstra d, establishments[] e)
    {
        System.out.print(
                "\n[0] Restaurants\n" +
                "[1] Hotels\n" +
                "[2] Bars\n" +
                "[3] Coffee\n" +
                "[4] Banks\n" +
                "[5] Parking lots\n" +
                "[6] Post offices\n" +
                "[7] Gas stations\n" +
                "[8] Groceries\n" +
                "[9] Hospitals\n" +
                "[10] Add Establishments\n" +
                "[11] Street Intersection\n" +
                "[12] Search\n" +
                "[13] View All\n" +
                "[14] Exit\n" +
                "Select option: " );
        int option;
        option = in.nextInt();
        in.nextLine();


        if (option<10) {
            System.out.println("");
            System.out.println(
                "[0] Alphabetical order\n" +
                "[1] Proximity");
            System.out.print("Choose listing order: ");
            int chosenOrder;
            chosenOrder = in.nextInt();
            in.nextLine();
            System.out.println(" ");

            if (chosenOrder == 0){//if chosen alpha order, extract establishments
                                  //names from String[] establishments and transfer to a temp String[]

                String[] temp = new String[e[option].sizeOfEstablishments];
                //transferring . . .
                for (int i = 0; i<temp.length; i++){
                    temp[i] = e[option].getEstablishments(i);
                }
                //then sort temp
                q.sortStr(temp, 0, temp.length-1);
                //display the contents of temp with its associated distances
                for (int i = 0; i<temp.length; i++){
                    System.out.println(temp[i]+" | "+d.getDistance(e[option].getVertex(temp[i]))+"m");
                }
                //ask user for choice
                System.out.print("Enter destination name: ");
                String dest = in.nextLine();
                //if string not found in list, redirect to Main Menu
                if (e[option].isFound(dest)==false){
                    System.out.println(" ");
                    System.out.println("Error: invalid input");
                    promptEnterKey();
                    displayMainMenu(in, d, e);
                }else{
                    //locInMap stores the vertex location of the name
                    int locInMap = e[option].getVertex(dest);
                    //distance stores the distance from the source to the vertex
                    double distance = d.getDistance(locInMap);
                    double time = (distance/velocity);
                    String mode = "";
                    if (modeOfTransportation==0)
                        mode = "Walking";
                    else
                        mode = "Vehicle";

                    //displaying the results
                    System.out.println();
                    System.out.println("Results: ");
                    System.out.println("\tStarting point: "+d.getIntersection(startVertex));
                    System.out.println("\tDestination point: "+d.getIntersection(locInMap)+"("+dest+")");
                    System.out.println("\tMode: "+mode+"("+velocity+" meters/minute)");
                    System.out.println("\tDistance: "+distance+" m");
                    System.out.println("\tETA: "+df.format(time)+" minutes");
                    System.out.print("\tPath: ");d.getPath(locInMap);
                    promptEnterKey();
                    displayMainMenu(in, d, e);
                }
            }

            else if (chosenOrder == 1){
                int[] temp = new int[e[option].sizeOfEstablishments];
                String[] temp2 = new String[e[option].sizeOfEstablishments];
                //temp stores equivalent distance of each
                //vertex location of each establishment
                for (int i = 0; i<temp.length; i++){
                    temp[i] = d.getDistance(e[option].getVertex(i));//transferring distances
                }
                //sorting temp
                temp = q.sortInt(temp, 0, temp.length-1);
                System.out.println(" ");
                //temp2 stores equivalent names of the distances in temp
                //temp2 is passed to check if 2 or more establishments have the same vertex location
                //to prevent duplicate display of names
                for (int i = 0; i<temp.length; i++){
                    temp2[i] = e[option].distanceToVertexToName(temp[i], d, temp2);
                }
                //displaying sorted names with its associated distances
                for (int j = 0; j<temp.length; j++){
                    System.out.println(temp2[j]+" | "+temp[j]+"m");
                }
                System.out.print("Enter destination name: ");
                String dest = in.nextLine();

                /*if (e[option].isFound(dest)!=false);
                {
                    System.out.println(" ");
                    System.out.println("Error: invalid input");
                    promptEnterKey();
                    displayMainMenu(in, d, e);
                }*/
                if (e[option].isFound(dest)==false){
                    System.out.println(" ");
                    System.out.println("Error: invalid input");
                    promptEnterKey();
                    displayMainMenu(in, d, e);
                }else{
                    int locInMap = e[option].getVertex(dest);
                    double distance = d.getDistance(locInMap);
                    double time = (distance/velocity);
                    String mode = "";
                    if (modeOfTransportation==0)
                        mode = "Walking";
                    else
                        mode = "Vehicle";
                    System.out.println();
                    System.out.println("Results: ");
                    System.out.println("\tStarting point: "+d.getIntersection(startVertex));
                    System.out.println("\tDestination point: "+d.getIntersection(locInMap)+"("+dest+")");
                    System.out.println("\tMode: "+mode+"("+velocity+" meters/minute)");
                    System.out.println("\tDistance: "+distance+" m");
                    System.out.println("\tETA: "+df.format(time)+" minutes");
                    System.out.print("\tPath: ");d.getPath(locInMap);
                    promptEnterKey();
                    displayMainMenu(in, d, e);
                }
            }
        }

        else if (option ==10){
            System.out.println("");
            int i;
            int type;
            char ch='"';
            String chosenName = "";

            int loc;
            for (i = 0; i <10; i++) {//printing choices
                System.out.print("[" + i + "] ");
                e[i].getEstablishmentType();
            }

            System.out.print("Select type: ");
            type = in.nextInt();
            in.nextLine();

            System.out.print("Name: ");
            chosenName = in.nextLine();

            System.out.print("Location: ");
            loc = in.nextInt();

            if ((type>-1 && type<10) && (loc>-1 && loc<20)){
                e[type].addSpecificEstablishment(chosenName);
                e[type].addLocationInMap(loc);
                root = searchTree.insert(root,chosenName,loc);

                System.out.print(ch+chosenName+ch+" has been successfully added to ");e[type].getEstablishmentType();
                System.out.print("Location: intersection #"+loc+", "+d.getIntersection(loc)+".");
                promptEnterKey();
                displayMainMenu(in, d, e);
            }
            else{
                System.out.println("Error: Invalid input");
                promptEnterKey();
                displayMainMenu(in, d, e);
            }
        }

        else if (option ==11){
            displayStreetIntersections();
            System.out.print("Enter destination: ");
            int dest = in.nextInt();

            if ((dest>-1 && dest<19) || modeOfTransportation == 0){
/*                String destName = d.getIntersection(dest);*/
                System.out.println("");
                int locInMap = dest;

                double distance = d.getDistance(locInMap);
                double time = (distance/velocity);
                String mode = "";
                if (modeOfTransportation==0)
                    mode = "Walking";
                else
                    mode = "Vehicle";
                System.out.println();
                System.out.println("Results: ");
                System.out.println("\tStarting point: "+d.getIntersection(startVertex));
                System.out.println("\tDestination point: "+d.getIntersection(locInMap));
                System.out.println("\tMode: "+mode+"("+velocity+" meters/minute)");
                System.out.println("\tDistance: "+distance+" m");
                System.out.println("\tETA: "+df.format(time)+" minutes");
                System.out.print("\tPath: ");d.getPath(locInMap);
                promptEnterKey();
                displayMainMenu(in, d, e);
            }
            else{
                if (dest ==19){
                    System.out.println("Error: cannot reach destination");
                    promptEnterKey();
                    displayMainMenu(in, d, e);
                }
                else {
                    System.out.println("Error: Invalid input");
                    promptEnterKey();
                    displayMainMenu(in, d, e);
                }
            }
        }
        else if (option == 12){
            System.out.println("");
            System.out.print("Enter search name: ");
            String searched = in.nextLine();

            int vertexOfSearched = searchTree.getVertex(root,searched);

            if (vertexOfSearched!=-1){
                int dest = vertexOfSearched;
                /*String destName = d.getIntersection(dest);*/
                int locInMap = dest;
                double distance = d.getDistance(locInMap);
                double time = (distance/velocity);
                String mode = "";
                if (modeOfTransportation==0)
                    mode = "Walking";
                else
                    mode = "Vehicle";
                System.out.println();
                System.out.println("Results: ");
                System.out.println("\tStarting point: "+d.getIntersection(startVertex));
                System.out.println("\tDestination point: "+d.getIntersection(locInMap)+"("+searched+")");
                System.out.println("\tMode: "+mode+"("+velocity+" meters/minute)");
                System.out.println("\tDistance: "+distance+" m");
                System.out.println("\tETA: "+df.format(time)+" minutes");
                System.out.print("\tPath: ");d.getPath(locInMap);
                promptEnterKey();
                displayMainMenu(in, d, e);
            }
            else{
                System.out.println(searched+" does not exist.");
                promptEnterKey();
                displayMainMenu(in, d, e);
            }
        }
        else if (option ==13){
            System.out.println();
            searchTree.printInorder(root,d);
            promptEnterKey();
            displayMainMenu(in, d, e);
        }
        else if (option ==14){
            return;
        }
    }

    /**
     * Prompts user to press enter before redirecting back to Main Menu
     */
    public static void promptEnterKey(){
        System.out.println(" ");
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}

