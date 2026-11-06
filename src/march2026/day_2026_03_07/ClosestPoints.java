package march2026.day_2026_03_07;

import java.util.logging.Logger;

/*
Given a set of points (x, y) on a 2D cartesian plane, find the two closest points.
For example, given the points
[(1, 1), (-1, -1), (3, 4), (6, 1), (-1, -6), (-4, -3)], return [(-1, -1), (1, 1)].
*/

import java.util.*;

public class ClosestPoints {
        private static final Logger LOGGER = Logger.getLogger(ClosestPoints.class.getName());

        private ClosestPoints() {
        }

        record Point(int x, int y) {}

        public static List<Point> closestPair(List<Point> points) {
                if (points == null || points.size() < 2) {
                        throw new IllegalArgumentException("At least 2 points are required");
                }

                // Stream API: create sorted copies
                List<Point> byX = points.stream()
                        .sorted(Comparator.comparingInt(Point::x)
                                .thenComparingInt(Point::y))
                        .toList();

                List<Point> byY = points.stream()
                        .sorted(Comparator.comparingInt(Point::y)
                                .thenComparingInt(Point::x))
                        .toList();

                Result result = closest(byX, byY);

                return List.of(result.p1(), result.p2());
        }

        private static Result closest(List<Point> px, List<Point> py) {

                int n = px.size();

                // Base case
                if (n <= 3) {
                        return bruteForce(px);
                }

                int mid = n / 2;
                Point midPoint = px.get(mid);

                List<Point> leftX = px.subList(0, mid);
                List<Point> rightX = px.subList(mid, n);

                Set<Point> leftSet = new HashSet<>(leftX);

                List<Point> leftY = new ArrayList<>();
                List<Point> rightY = new ArrayList<>();

                for (Point p : py) {
                        if (leftSet.contains(p)) {
                                leftY.add(p);
                        } else {
                                rightY.add(p);
                        }
                }

                Result leftResult = closest(leftX, leftY);
                Result rightResult = closest(rightX, rightY);

                Result best = leftResult.distanceSquared() <= rightResult.distanceSquared()
                        ? leftResult
                        : rightResult;

                // Build strip
                Result finalBest = best;
                List<Point> strip = py.stream()
                        .filter(p -> {
                                long dx = (long) p.x() - midPoint.x();
                                return dx * dx < finalBest.distanceSquared();
                        })
                        .toList();

                // Only need to compare nearby points in Y order
                for (int i = 0; i < strip.size(); i++) {
                        for (int j = i + 1; j < strip.size(); j++) {

                                long dy = (long) strip.get(j).y() - strip.get(i).y();

                                if (dy * dy >= best.distanceSquared()) {
                                        break;
                                }

                                long distance = distanceSquared(strip.get(i), strip.get(j));

                                if (distance < best.distanceSquared()) {
                                        best = new Result(
                                                strip.get(i),
                                                strip.get(j),
                                                distance
                                        );
                                }
                        }
                }

                return best;
        }

        private static Result bruteForce(List<Point> points) {

                Result best = null;

                for (int i = 0; i < points.size(); i++) {
                        for (int j = i + 1; j < points.size(); j++) {

                                long distance = distanceSquared(
                                        points.get(i),
                                        points.get(j)
                                );

                                if (best == null || distance < best.distanceSquared()) {
                                        best = new Result(
                                                points.get(i),
                                                points.get(j),
                                                distance
                                        );
                                }
                        }
                }

                return best;
        }

        private static long distanceSquared(Point a, Point b) {
                long dx = (long) a.x() - b.x();
                long dy = (long) a.y() - b.y();

                return dx * dx + dy * dy;
        }

        record Result(
                Point p1,
                Point p2,
                long distanceSquared
        ) {}

        public static void main(String[] args) {

                List<Point> points = List.of(
                        new Point(1, 1),
                        new Point(-1, -1),
                        new Point(3, 4),
                        new Point(6, 1),
                        new Point(-1, -6),
                        new Point(-4, -3)
                );

                List<Point> result = closestPair(points);

                LOGGER.info(result.toString());
        }
}
