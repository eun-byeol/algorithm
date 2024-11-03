import java.util.*;

class Solution {

    public String[] solution(String[] expressions) {
        int N = expressions.length;
        
        String[] outputs = new String[N];
        
        for (int num=2; num<10; num++) {
            Map<Integer, String> xs = new HashMap<>();
            boolean isValid = true;
            
            for (int i=0; i<N; i++) {
                String ep = expressions[i];
                String[] sep = ep.split(" ");
            
                if (!validate(sep, num)) {
                    isValid = false;
                    break;
                }
                
                int a = Integer.parseInt(sep[0], num);
                int b = Integer.parseInt(sep[2], num);
                int c = a + b;
                if (sep[1].equals("-")) {
                    c = a - b;
                }
                String sc = Integer.toString(c, num);
                
                if (sep[4].equals("X")) {
                    xs.put(i, sc);
                    continue;
                }
                if (!sep[4].equals(sc)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                for (Integer idx : xs.keySet()) {
                    if (outputs[idx] != null && !outputs[idx].equals(xs.get(idx))) {
                        outputs[idx] = "?";
                        continue;
                    }
                    outputs[idx] = xs.get(idx);
                }
            }
        }
        
        List<String> ans = new ArrayList<>();
        for (int i=0; i<N; i++) {
            if (outputs[i] != null) {
                String rp = expressions[i].replace("X", outputs[i]);
                
                ans.add(rp);
            }
        }
        
        return ans.toArray(new String[ans.size()]);
    }
    
    boolean validate(String[] sep, int num) {
        char[] a = sep[0].toCharArray();
        int tn = a.length;
        for (int i=0; i<tn; i++) {
            if ((a[i] - '0') >= num) {
                return false;
            }
        }
        char[] b = sep[2].toCharArray();
        tn = b.length;
        for (int i=0; i<tn; i++) {
            if ((b[i]-'0') >= num) {
                return false;
            }
        }
        return true;
    }
}
