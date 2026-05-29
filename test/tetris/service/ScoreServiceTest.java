package tetris.service;

import org.junit.jupiter.api.Test;

import tetris.model.Score;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreServiceTest {

    @Test
    public void shouldSaveScore() {

        ScoreService service = new ScoreService();

        Score score = new Score("Test", 1000);

        service.saveScore(score);

        List<Score> scores = service.loadScores();

        assertFalse(scores.isEmpty());
    }

    @Test
    public void shouldLoadScores() {

        ScoreService service = new ScoreService();

        List<Score> scores = service.loadScores();

        assertNotNull(scores);
    }
}