import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
class LC957 {
    public static void main(String[] args) {
        int[] cells = {0, 1, 0, 1, 1, 0, 0, 1};
        int[] res = new LC957().prisonAfterNDays(cells, 15);

        for (int num : res) {
            System.out.println(num);
        }
    }
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> map = new HashMap<>();

        int cycle = 0;
        boolean hasCycle = false;

        for (int i = 0; i < N; i++) {
            int[] next = nextDay(cells);

            if (map.containsKey(Arrays.toString(next))) {
                hasCycle = true;
                break;
            }

            map.put(Arrays.toString(next), ++cycle);

            cells = next;
        }

        if (hasCycle) {
            String dayN = "";
            N %= cycle;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == N) {
                    dayN = entry.getKey();
                    break;
                }
            }

            int index = 0;
            for (int i = 0; i < dayN.length(); i++) {
                char c = dayN.charAt(i);
                if (Character.isDigit(c)) {
                    cells[index++] = Character.getNumericValue(c);
                }
            }
        }

        return cells;
    }
    
    private int[] nextDay(int[] cells) {
        int[] arr = new int[cells.length];
        arr[0] = 0; 
        arr[cells.length - 1] = 0;
        
        for (int i = 1; i < cells.length - 1; i++) {
            arr[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        
        return arr;
    }
}