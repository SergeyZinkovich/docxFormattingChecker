package com.formatChecker.comparer.differ;

public class RunsCountDiffer {
    Integer actualRuns;
    Integer maxRunsCount;
    Integer minRunsCount;
    String difference;

    public RunsCountDiffer(Integer actualRuns, Double minRunsCount, Double maxRunsCount) {
        this.actualRuns = actualRuns;
        this.minRunsCount = minRunsCount == null ? null : minRunsCount.intValue();
        this.maxRunsCount = maxRunsCount == null ? null : maxRunsCount.intValue();
        this.difference = compareParagraphsCount();
    }

    String compareParagraphsCount() {
        if (actualRuns == null)
            return "Could not read number of runs";
        if (minRunsCount != null && maxRunsCount != null) {
            if (!(actualRuns >= minRunsCount && actualRuns <= maxRunsCount)) {
                return String.format("paragraph has %s runs, should have %d-%d runs",
                        actualRuns, minRunsCount, maxRunsCount);
            }
        } else if (minRunsCount != null) {
            if (actualRuns < minRunsCount) {
                return String.format("paragraph has %s runs, should have more then %d runs",
                        actualRuns, minRunsCount);
            }
        } else if (maxRunsCount != null) {
            if (actualRuns > maxRunsCount) {
                return String.format("paragraph has %s runs, should least then %d runs",
                        actualRuns, maxRunsCount);
            }
        }

        return null;
    }

    public String getDifference() {
        return difference;
    }
}
