package tetris.service;
import java.util.Comparator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tetris.model.Score;

import java.io.FileReader;
import java.io.FileWriter;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

public class ScoreService {

    private static final String FILE_PATH = "data/scores.json";

    private Gson gson = new Gson();

   public void saveScore(Score score) {

    List<Score> scores = loadScores();

    scores.add(score);

    System.out.println("Saving...");
    System.out.println(score.getPlayer());

    scores.sort(Comparator.comparingInt(Score::getPoints).reversed());

    if (scores.size() > 10) {

        scores = scores.subList(0, 10);
    }

    try (FileWriter writer = new FileWriter(FILE_PATH)) {

        gson.toJson(scores, writer);

        System.out.println("Saved!");

    } catch (Exception e) {

        e.printStackTrace();
    }
}

    public List<Score> loadScores() {

        try (FileReader reader = new FileReader(FILE_PATH)) {

            Type listType = new TypeToken<List<Score>>() {}.getType();

            List<Score> scores = gson.fromJson(reader, listType);

            return scores != null ? scores : new ArrayList<>();

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }
}