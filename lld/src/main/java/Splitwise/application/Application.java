package Splitwise.application;

import Splitwise.Utility.Constants;
import Splitwise.Utility.Logger;
import Splitwise.repository.UserRepo;
import Splitwise.service.SplitwiseContext;
import Splitwise.service.SpliwiseServiceInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static Splitwise.Utility.Constants.FILE_PARSING_ERROR;

public class Application {
    public static void main(String[] args)
    {
        init();
        try {
            List<String> lines = Files.readAllLines(Paths.get(Constants.FILE_PATH));
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).trim().length() > 0) {
                    SpliwiseServiceInterface strategy = SplitwiseContext.getInstance();
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
        UserRepo userRepo=UserRepo.getInstance();
        userRepo.createUsers();
    }
}
