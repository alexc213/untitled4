import java.util.ArrayList;

public class Main {
    private static ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
    public static void main(String[] args) {

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
        //baconMoviesList.size()>0
        //for(int i = 0;i<=1;i++) {
            baconMovies.clear();
            for(SimpleMovie movie : baconMoviesList){
                movies.remove(movie);
            }
            ArrayList<SimpleMovie> tempBaconMoviesList = new ArrayList<>();
            for (SimpleMovie movie : baconMoviesList) {
                for (String actor : movie.getActors()) {
                    for (SimpleMovie movie1 : moviesWithActor(actor)) {
                        ArrayList<String> tempBlock = new ArrayList<>();
                        tempBlock.add(actor);
                        tempBlock.add(movie.getTitle());
                        tempBlock.add(movie1.getTitle());
                        tempBaconMoviesList.add(movie1);
                        baconMovies.add(tempBlock);
                    }
                }
            }
            answerKey.add(baconMovies);
            baconMoviesList.clear();
            System.out.println("Progress");
            for (SimpleMovie movie : tempBaconMoviesList) {
                baconMoviesList.add(movie);
            }
        //}
        System.out.println(answerKey.size());
        System.out.println(answerKey.get(0).size());
        System.out.println(answerKey.get(1).size());
        //System.out.println(answerKey.get(2).size());
        System.out.println(movies.size());


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



    public int bSearch(int[] arr, int left, int right, int target) {
        if (right >= left) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                return bSearch(arr, left, mid - 1, target);
            } else {
                return bSearch(arr, mid + 1, right, target);
            }
        }
        return -1;
    }


}