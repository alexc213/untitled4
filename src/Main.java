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
        for(SimpleMovie movie : baconMoviesList){
            movies.remove(movie);
            for(String actor : movie.getActors()){

            }
        }
        System.out.println(answerKey);


    }
    public ArrayList<SimpleMovie> moviesWithActor(String actor){
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