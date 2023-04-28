import java.util.ArrayList;

public class Main {
    private static ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data2");
    public static void main(String[] args) {
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
            movies.remove(movie);
        }
        //baconMoviesList.size()>0
        for(int i = 0;i<=1;i++) {
            ArrayList<ArrayList<String>> reference = new ArrayList<>();
//            for(SimpleMovie movie : baconMoviesList){
//                movies.remove(movie);
//            }
            ArrayList<SimpleMovie> tempBaconMoviesList = new ArrayList<>();
            for (SimpleMovie movie : baconMoviesList) {
                for (String actor : movie.getActors()) {
                    for (SimpleMovie movie1 : moviesWithActor(actor)) {
                        ArrayList<String> tempBlock = new ArrayList<>();
                        tempBlock.add(actor);
                        tempBlock.add(movie.getTitle());
                        tempBlock.add(movie1.getTitle());
                        tempBaconMoviesList.add(movie1);
                        reference.add(tempBlock);
                    }
                }
            }

            answerKey.add(reference);
            baconMoviesList.clear();
            //System.out.println("Progress");
            for (SimpleMovie movie : tempBaconMoviesList) {
                baconMoviesList.add(movie);
                movies.remove(movie);
            }
        }
        System.out.println(answerKey.size());
        System.out.println(answerKey.get(0).size());
        System.out.println(answerKey.get(0));
        System.out.println(answerKey.get(1).size());
        System.out.println(answerKey.get(1));
        System.out.println(answerKey.get(2).size());
        System.out.println(answerKey.get(2));
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


}