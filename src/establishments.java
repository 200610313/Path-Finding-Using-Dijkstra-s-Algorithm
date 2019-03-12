/**
 * Custom class containing a name(group, establishmentType),and two dynamic arrays(subgroup). One of the arrays is used to
 * store establishment names(establishments), and the other array stores their respective vertex location(locationsInMap.
 */

import java.util.Arrays;
public class establishments {
    private String establishmentType;
    private String[] establishments;
    public int sizeOfEstablishments;
    private int[] locationsInMap;
    private int sizeOfLocations;

    /**
     * Initializes and constructs establishments and locationsInMap with their sizes
     * @param e
     */
    establishments(String e) {
        establishments = new String[5];
        locationsInMap = new int[5];

        this.establishmentType = e;
    }

    /**
     *Adds an establishment to the list
     * @param e
     */
    public void addSpecificEstablishment(String e) {
        if (establishments.length - sizeOfEstablishments <= 0)//if almost full
        {
            establishments = Arrays.copyOf(establishments, establishments.length + 5); //add 5 spaces } //increases size
        }
        establishments[sizeOfEstablishments] = e;
        sizeOfEstablishments++;
    }

    /**
     *Set the vertex location of the establishment
     * @param l vertex location
     */
    public void addLocationInMap(int l) {
        if (locationsInMap.length - sizeOfLocations <= 0) {
            locationsInMap = Arrays.copyOf(locationsInMap, locationsInMap.length + 5);
        }
        locationsInMap[sizeOfLocations] = l;
        sizeOfLocations++;
    }
    /**
     * Prints the string value of establishmentType
     */
    public void getEstablishmentType() {
        System.out.println(this.establishmentType);
    }

    /**
     * returns Name of establishment based on their vertex location
     *
     * @param e The Vertex location
     * @return String Name of establishment
     */

    public String getEstablishments(int e){
        return establishments[e];
    }
    /**
     * returns vetex number in the graph of establishment based on index.
     *
     * @param l
     * @return int
     */
    public int getVertex(int l){
        return locationsInMap[l];
    }
    /**
     * returns vetex # in the graph of est based on String.
     *
     * @param l String
     * @return integer
     */
    public int getVertex(String l)
    {
        int i;
        int ve = 0;
        for (i = 0; i < sizeOfLocations; i++)
        {
            if (establishments[i].equals(l)==true)
            {
                ve = locationsInMap[i];
            }
        }
        return ve;
    }
    /**
     * Returns the equivalent name of the distance from source to destination
     *
     * @param v destination vertex location
     * @return String
     */
    //used once to categorize the sorted distances according to their names
    public String distanceToVertexToName(int v, dijkstra d, String[] s){
        int i;
        int vertex = 0;
        for (i = 0; i<20; i++){
            if (d.getDistance(i)==v){//if found vertex equivalent of the distance
                vertex = i;//save it
            }
        }
        int j;

        for (j = 0; j<sizeOfLocations; j++){
            if (locationsInMap[j] == vertex ){// if found in list
                //prevents repetition of string output when two establishments have the same vertex location
                if (!(isRepeated(establishments[j],s))){
                    return establishments[j];
                }
            }
        }
        return "";
    }
    /**
     * Checks if a vertex location is repeated
     *
     * @param word the word to be compared with
     * @return boolean Returns true if the
     */
    public boolean isRepeated(String word, String[] s){
        int i;

        for (i = 0; i<s.length;i++){
            if (word.equals(s[i])==true){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a string is found in the list
     * @param est the String to be searched for in the list
     * @return returns true if it is found else false
     */

    public boolean isFound(String est){
        boolean flag;
        flag =false;

        for (int i = 0; i < establishments.length; i++)
        {
            if (est.equals(establishments[i]))
            {
                flag = true;
                return flag;
            }
        }
        return flag;
    }
}


