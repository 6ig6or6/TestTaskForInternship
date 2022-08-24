package com.game.util;

import com.game.dto.PlayerDTO;
import com.game.exception.WrongParamException;

import java.util.Date;

public class PlayerDtoChecker {
    /** Spring validation isn't included into pom.xml so this class has to exist */
    private static final int YEAR_2000 = 2000;
    private static final int YEAR_3000 = 3000;
    private static final int MAX_EXPERIENCE = 10_000_000;
    private static final int MAX_NAME_LENGTH = 12;
    private static final int MAX_TITLE_LENGTH = 30;
    public static void checkPlayerFieldsForRegistration(PlayerDTO playerDto) {
        if (playerDto.getName() == null
                || playerDto.getTitle() == null
                || playerDto.getName().length() > MAX_NAME_LENGTH
                || playerDto.getTitle().length() > MAX_TITLE_LENGTH
                || playerDto.getName().equals("")
                || checkBirthday(playerDto.getBirthday())
                || checkExperience(playerDto.getExperience())) {
            throw new WrongParamException();
        }
    }
    public static void checkPlayerFieldsForUpdate(Long birthday, Integer experience) {
        boolean notCorrect = birthday != null && checkBirthday(birthday);

        if (experience != null && checkExperience(experience)) {
            notCorrect = true;
        }
        if (notCorrect) {
            throw new WrongParamException();
        }
    }
    private static boolean checkBirthday(Long birthday) {
        Date date = new Date(birthday);
        int year = date.getYear() + 1900;
        return YEAR_2000 > year || YEAR_3000 < year;
    }
    private static boolean checkExperience(Integer experience) {
        return experience < 0 || experience > MAX_EXPERIENCE;
    }
}
