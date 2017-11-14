package grade;

import java.util.Collection;
import java.util.stream.IntStream;

public class ProjectGrade {

    /**
     * Returns a grade depending on if you used git and the project compiles.
     * @param usedGit true if git was used
     * @param compiles true if the project compiled
     * @param grade grade calculated from the rubric
     * @return grade
     */
    public static double calculateGrade(boolean usedGit, boolean compiles, double grade) {
        if (!usedGit || !compiles) {
            return 1.0;
        }

        return grade;
    }

    public static boolean usesLambda() {
        return IntStream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter(i -> i < 9).allMatch(i -> i > 9);
    }

    /**
     * Tells you when the ta is happy.
     * @param bringCake true if you brought cake
     * @return happyness of the TA
     */
    public static boolean taIsHappy(boolean bringCake) {
        return bringCake;
    }

}
