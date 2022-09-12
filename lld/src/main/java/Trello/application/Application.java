package Trello.application;

import Trello.Utility.Constants;
import Trello.Utility.Logger;
import Trello.repository.UserRepository;
import Trello.service.TrelloStrategy;
import Trello.service.startegy.TrelloContextDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static Trello.Utility.Constants.FILE_PARSING_ERROR;

public class Application {
    public static void main(String[] args)
    {
        init();
        try {
            List<String> lines = Files.readAllLines(Paths.get(Constants.FILE_PATH));
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).trim().length() > 0) {
                    TrelloStrategy strategy = TrelloContextDriver.getInstance();
                    try {
                        System.out.println(strategy.doOperation(lines.get(i)));
                    } catch (Exception e) {
                        Logger.printError("input :"+lines.get(i));
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            Logger.printError(FILE_PARSING_ERROR);
            e.printStackTrace();
        }
    }

    private static void init() {
        UserRepository userRepo=UserRepository.getInstance();
        userRepo.createUsers();
    }
}
