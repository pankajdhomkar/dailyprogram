package march2026.day_2026_03_08;


/*
You are given an N by N matrix of random letters and a dictionary of words.
Find the maximum number of words that can be packed on the board from the given dictionary.
A word is considered to be able to be packed on the board if:
It can be found in the dictionary
It can be constructed from untaken letters by other words found so far on the board
The letters are adjacent to each other (vertically and horizontally, not diagonally).
Each tile can be visited only once by any word.
 */

import java.util.ArrayList;
import java.util.List;

public class MaxPackedWords {
    static class WordPlacement {
        String word;
        List<int[]> cells;

        WordPlacement(String word, List<int[]> cells) {
            this.word = word;
            this.cells = cells;
        }
    }

    private static int maxWords = 0;

    public static int maxPackedWords(char[][] board, String[] dictionary) {

        int n = board.length;

        List<WordPlacement> placements = new ArrayList<>();

        for (String word : dictionary) {

            boolean found = false;

            for (int i = 0; i < n && !found; i++) {
                for (int j = 0; j < n && !found; j++) {

                    boolean[][] visited = new boolean[n][n];
                    List<int[]> path = new ArrayList<>();

                    if (dfs(board, word, 0, i, j, visited, path)) {
                        placements.add(new WordPlacement(word, new ArrayList<>(path)));
                        found = true;
                    }
                }
            }
        }

        boolean[][] occupied = new boolean[n][n];

        backtrack(placements, 0, occupied, 0);

        return maxWords;
    }

    private static void backtrack(List<WordPlacement> placements,
                                  int index,
                                  boolean[][] occupied,
                                  int count) {

        maxWords = Math.max(maxWords, count);

        for (int i = index; i < placements.size(); i++) {

            WordPlacement wp = placements.get(i);

            if (canPlace(wp, occupied)) {

                mark(wp, occupied, true);

                backtrack(placements, i + 1, occupied, count + 1);

                mark(wp, occupied, false);
            }
        }
    }

    private static boolean canPlace(WordPlacement wp, boolean[][] occupied) {

        for (int[] cell : wp.cells) {
            if (occupied[cell[0]][cell[1]])
                return false;
        }

        return true;
    }

    private static void mark(WordPlacement wp,
                             boolean[][] occupied,
                             boolean value) {

        for (int[] cell : wp.cells) {
            occupied[cell[0]][cell[1]] = value;
        }
    }

    private static boolean dfs(char[][] board,
                               String word,
                               int index,
                               int r,
                               int c,
                               boolean[][] visited,
                               List<int[]> path) {

        if (index == word.length())
            return true;

        int n = board.length;

        if (r < 0 || c < 0 || r >= n || c >= n)
            return false;

        if (visited[r][c])
            return false;

        if (board[r][c] != word.charAt(index))
            return false;

        visited[r][c] = true;
        path.add(new int[]{r, c});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int k = 0; k < 4; k++) {

            if (dfs(board, word, index + 1,
                    r + dr[k], c + dc[k],
                    visited, path))
                return true;
        }

        if (index + 1 == word.length())
            return true;

        visited[r][c] = false;
        path.remove(path.size() - 1);

        return false;
    }

    public static void main(String[] args) {

        char[][] board = {
                {'C', 'A', 'T'},
                {'D', 'O', 'G'},
                {'R', 'A', 'P'}
        };

        String[] dictionary = {
                "CAT",
                "DOG",
                "CAP",
                "CAR"
        };

        System.out.println(maxPackedWords(board, dictionary));
    }

}
