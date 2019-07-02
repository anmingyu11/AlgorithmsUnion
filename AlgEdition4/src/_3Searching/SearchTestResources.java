package _3Searching;

public class SearchTestResources {

    public static class Local {
        // Todo can i do this better for resouces loc
        private final static String resourceLoc = "/home/amy/github/AlgEssentialsSRC/AlgEdition4/src/resources/4search/";

        public static final String list = resourceLoc + "list.txt";
        public static final String tale = resourceLoc + "tale.txt";
        public static final String tinyTale = resourceLoc + "tinyTale.txt";
        public static final String tinyST = resourceLoc + "tinyST.txt";
        public static final String ip = resourceLoc + "ip.csv";
        public static final String morse = resourceLoc + "morse.csv";
        public static final String amino = resourceLoc + "amino.csv";
        public static final String elements = resourceLoc + "elements.csv";
        public static final String[] fileIndex = {
                resourceLoc + "ex1.txt",
                resourceLoc + "ex2.txt",
                resourceLoc + "ex3.txt",
                resourceLoc + "ex4.txt"
        };
    }

    public static class Cloud {
        public static final String tales = "https://algs4.cs.princeton.edu/31elementary/tale.txt";
        public static final String tinyST = "https://algs4.cs.princeton.edu/31elementary/tinyST.txt";
        public static final String[] fileIndex = {
                "https://algs4.cs.princeton.edu/35applications/ex1.txt",
                "https://algs4.cs.princeton.edu/35applications/ex2.txt",
                "https://algs4.cs.princeton.edu/35applications/ex3.txt",
                "https://algs4.cs.princeton.edu/35applications/ex4.txt",
        };
    }
}
