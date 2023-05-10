import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    //if you want a smaller data set change src/movie_data to src/movie_data2
    private static ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data2");
    public static void main(String[] args) {
        System.out.println("Loading...");
        System.out.println("This will take a while so you should probably grab a coffee or something ");
        System.out.println("actually it might take longer than that");
        System.out.println("you should probably go to sleep or something");
        System.out.println("it might even take longer than that");
        ArrayList<SimpleMovie> temp = new ArrayList<>();
        for(SimpleMovie movie : movies){
            temp.add(movie);
        }
        movies.sort(null);
        ArrayList<ArrayList<ArrayList<String>>> answerKey = new ArrayList<>();
        ArrayList<ArrayList<String>> baconMovies = new ArrayList<>();
        ArrayList<SimpleMovie> baconMoviesList = new ArrayList<>();
        for(SimpleMovie movie : movies){
            for(String actor : movie.getActors()){
                if(actor.equals("Kevin Bacon")){
                    ArrayList<String> block = new ArrayList<>();
                    block.add(actor);
                    block.add(null);
                    block.add(movie.getTitle());
                    baconMoviesList.add(movie);
                    baconMovies.add(block);
                }
            }
        }
        answerKey.add(baconMovies);
        for (SimpleMovie movie : baconMoviesList) {
            remove(movie);
        }

        while(baconMoviesList.size()>0) {
            ArrayList<ArrayList<String>> reference = new ArrayList<>();

            ArrayList<SimpleMovie> tempBaconMoviesList = new ArrayList<>();
            for (SimpleMovie movie : baconMoviesList) {
                for (String actor : movie.getActors()) {
                    for (SimpleMovie movie1 : moviesWithActor(actor)) {
                        ArrayList<String> tempBlock = new ArrayList<>();
                        //reference actor
                        tempBlock.add(actor);
                        //reference movie
                        tempBlock.add(movie.getTitle());
                        //current movie
                        tempBlock.add(movie1.getTitle());
                        tempBaconMoviesList.add(movie1);
                        reference.add(tempBlock);
                    }
                }
            }

            answerKey.add(reference);
            baconMoviesList.clear();
            for (SimpleMovie movie : tempBaconMoviesList) {
                baconMoviesList.add(movie);
                remove(movie);
            }
        }
        //start
        movies = temp;
        Scanner scan = new Scanner(System.in);
        System.out.println("Loaded!\nWelcome to Kevin Bacon");
        //always runs so you don't accidentally exit because if you do then you will have to wait for the answer key to regenerate. Just in case:)
        while(1==1) {
            System.out.print("Please enter an actor's name: ");
            String actor = scan.nextLine();
            int temp1 = 0;
            int temp2 = 0;
            boolean run = true;
            ArrayList<String> movieNames = new ArrayList<>();
            for (SimpleMovie movie : moviesWithActor(actor)) {
                movieNames.add(movie.getTitle());
            }
            for (int i = 0; i < answerKey.size(); i++) {
                for (int j = 0; j < answerKey.get(i).size(); j++) {
                    for (String movieName : movieNames) {
                        if (movieName.equals(answerKey.get(i).get(j).get(2))) {
                            run = false;
                            temp1 = i;
                            temp2 = j;
                            break;
                        }
                    }
                    if (!run) {
                        break;
                    }
                }
                if (!run) {
                    break;
                }
            }

            ArrayList<String> finalPath = new ArrayList<>();
            finalPath.add(actor);
            finalPath.add(0, answerKey.get(temp1).get(temp2).get(2));
            finalPath.add(0, answerKey.get(temp1).get(temp2).get(0));
            for (int i = temp1 - 1; i >= 0; i--) {
                for (int j = 0; j < answerKey.get(i).size(); j++) {
                    if (answerKey.get(i).get(j).get(2).equals(answerKey.get(i + 1).get(temp2).get(1))) {
                        finalPath.add(0, answerKey.get(i).get(j).get(2));
                        finalPath.add(0, answerKey.get(i).get(j).get(0));
                        temp2 = j;
                        break;
                    }
                }
            }
            if(run){
                System.out.println("Sorry there is no connection to that actor in the database");
            }else{
                System.out.println(actor + " has a bacon number of " + (finalPath.size()-1)/2);
                for(int i = finalPath.size()-1;i>=0;i--){
                    System.out.print(finalPath.get(i));
                    if(i%2==1){
                        System.out.println(" with ");
                    }else{
                        if(i != 0){
                            System.out.println(" was in");
                        }else{
                            System.out.println();
                        }
                    }
                }
            }
        }
    }
    public static ArrayList<SimpleMovie> moviesWithActor(String actor){
        ArrayList<SimpleMovie> moviesWithActor = new ArrayList<>();
        for(SimpleMovie movie : movies){
            if(movie.getActors().contains(actor)){
                moviesWithActor.add(movie);
            }
        }
        return moviesWithActor;
    }


    //cuts down on a little bit of time that adds up but doesn't cut out enough time
    public static int bSearch(int left, int right, SimpleMovie target) {
        if (right >= left) {
            int mid = (left + right) / 2;
            if (movies.get(mid) == target) {
                return mid;
            } else if (movies.get(mid).compareTo(target) > 0) {
                return bSearch(left, mid - 1, target);
            } else {
                return bSearch(mid + 1, right, target);
            }
        }
        return -1;
    }

    public static void remove(SimpleMovie movie){
        int x = bSearch(0,movies.size()-1,movie);
        if(x != -1){
            movies.remove(x);
        }
    }


}