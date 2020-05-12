import java.util.*;
/**
 * Similar with LC1383
 */
class LC857 {
    class Worker {
        int wage;
        int quality;
        double ratio;
        
        public Worker(int w, int q) {
            wage = w;
            quality = q;
            ratio = 1.0 * w / q;
        }
    }
    
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        Worker[] workers = new Worker[n];
        
        for (int i = 0; i < n; i++) {
            workers[i] = new Worker(wage[i], quality[i]);
        }
        
        Arrays.sort(workers, (o1, o2) -> Double.compare(o1.ratio, o2.ratio));
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        double sum = 0, min = Double.MAX_VALUE;
        for (Worker worker : workers) {
            pq.offer(worker.quality);
            sum += worker.quality;
            
            if (pq.size() > K) {
                sum -= pq.poll();
            }
            
            if (pq.size() == K) {
                min = Math.min(min, sum * worker.ratio);
            }
        }
        
        return min;
    }
}