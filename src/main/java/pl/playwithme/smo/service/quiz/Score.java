package pl.playwithme.smo.service.quiz;

import pl.playwithme.smo.dto.QuestionLevel;

class Score {
    private final int questionScore;

    Score(int _questionScore) {
        questionScore = validateScore(_questionScore);
    }

    int getScore() {
        return questionScore;
    }

    QuestionLevel getQuestionLevel() {
        if (questionScore == 0) {
            return QuestionLevel.zero;
        } else if (questionScore <= 10) {
            return QuestionLevel.first;
        } else if (questionScore <= 25) {
            return QuestionLevel.second;
        } else if (questionScore <= 45) {
            return QuestionLevel.third;
        } else if (questionScore <= 100) {
            return QuestionLevel.fourth;
        } else {
            throw new RuntimeException("Bad question score, allowed value scope <0;48>");
        }
    }

    private int validateScore(int questionScore) {
        if (questionScore < 0 || questionScore > 100) {
            throw new RuntimeException("Bad question score, allowed value scope <0;100>");
        }
        return questionScore;
    }
}
