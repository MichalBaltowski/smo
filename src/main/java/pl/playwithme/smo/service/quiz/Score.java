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
        } else if (questionScore <= 12) {
            return QuestionLevel.first;
        } else if (questionScore <= 24) {
            return QuestionLevel.second;
        } else if (questionScore <= 36) {
            return QuestionLevel.third;
        } else if (questionScore <= 48) {
            return QuestionLevel.fourth;
        } else {
            throw new RuntimeException("Bad question score, allowed value scope <0;48>");
        }
    }

    private int validateScore(int questionScore) {
        if (questionScore < 0 || questionScore > 48) {
            throw new RuntimeException("Bad question score, allowed value scope <0;48>");
        }
        return questionScore;
    }
}
