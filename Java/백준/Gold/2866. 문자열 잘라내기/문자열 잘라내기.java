import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        
        sc.nextLine();
        String[] arr = new String[r];
        for (int i = 0; i < r; i++) {
            arr[i] = sc.nextLine();
        }
        
        List<String> words = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < r; j++) {
                sb.append(arr[j].charAt(i));
            }
            words.add(sb.toString());
        }
        
        int left = 0;
        int right = r;
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = (int) words.stream()
                .map(word -> word.substring(mid))
                .distinct()
                .count();
            if (count == c) {
                left = mid + 1;
                result = mid;
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println(result);
    }
}