package tetris.service;

import com.google.gson.Gson;

import tetris.model.GameState;

import java.io.FileReader;
import java.io.FileWriter;

public class SaveService {

    private static final String FILE_PATH = "./data/savegame.json";

    private Gson gson = new Gson();

    public void saveGame(GameState gameState) {

        try (FileWriter writer = new FileWriter(FILE_PATH)) {

            gson.toJson(gameState, writer);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public GameState loadGame() {

        try (FileReader reader = new FileReader(FILE_PATH)) {

            return gson.fromJson(reader, GameState.class);

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}