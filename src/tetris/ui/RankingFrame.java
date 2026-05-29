package tetris.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tetris.model.Score;
import tetris.service.ScoreService;

import java.util.List;

public class RankingFrame extends JFrame {

    public RankingFrame() {

        setTitle("Top 10 Scores");

        setSize(400, 300);

        setLocationRelativeTo(null);

        List<Score> scores = new ScoreService().loadScores();

        String[] columns = {
                "Player",
                "Points"
        };

        String[][] data = new String[scores.size()][2];

        for (int i = 0; i < scores.size(); i++) {

            data[i][0] = scores.get(i).getPlayer();

            data[i][1] = String.valueOf(scores.get(i).getPoints());
        }

        JTable table = new JTable(data, columns);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

        setVisible(true);
    }
}