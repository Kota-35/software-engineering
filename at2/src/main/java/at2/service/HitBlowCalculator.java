package at2.service;

import at2.domain.Answer;
import at2.domain.Guess;
import at2.domain.GameResult;

/**
 * HitとBlowを計算するサービス
 */
public class HitBlowCalculator {
    /**
     * HitとBlowを計算して結果を返す
     * 
     * @param answer 正解
     * @param guess ユーザーの推測
     * @return ゲーム結果
     */
    public GameResult calculate(Answer answer, Guess guess) {
        int hit = calculateHit(answer, guess);
        int blow = calculateBlow(answer, guess);
        return new GameResult(guess, hit, blow);
    }

    /**
     * Hit数を計算（同じ位置に同じ数字）
     * 
     * @param answer 正解
     * @param guess ユーザーの推測
     * @return Hit数
     */
    private int calculateHit(Answer answer, Guess guess) {
        int hit = 0;
        for (int i = 0; i < 4; i++) {
            if (answer.matchesAt(i, guess.getDigit(i))) {
                hit++;
            }
        }
        return hit;
    }

    /**
     * Blow数を計算（含まれるが位置が違う） 注意：Hitした数字はBlowに含めない
     * 
     * @param answer 正解
     * @param guess ユーザーの推測
     * @return Blow数
     */
    private int calculateBlow(Answer answer, Guess guess) {
        int blow = 0;
        for (int i = 0; i < 4; i++) {
            char guessDigit = guess.getDigit(i);
            // Hitしている場合はスキップ
            if (answer.matchesAt(i, guessDigit)) {
                continue;
            }
            // 正解に含まれるが位置が違う場合
            if (answer.contains(guessDigit)) {
                blow++;
            }
        }
        return blow;
    }
}

