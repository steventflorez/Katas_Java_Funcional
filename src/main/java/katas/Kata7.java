package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .map(mv -> mv.getVideos())
                .flatMap(ml -> ml.stream())
                .map(mv ->
                        ImmutableMap.of("id", mv.getId(), "title", mv.getTitle(), "boxart", mv.getBoxarts().stream()
                                .min(Comparator.comparing(bxa -> bxa.getWidth())).orElseThrow().getUrl()
                        )).collect(Collectors.toList());

                //ImmutableList.of(ImmutableMap.of("id", 5, "title", "Bad Boys", "boxart", "url"));
    }
}
