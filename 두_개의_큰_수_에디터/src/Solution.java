import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    private static BufferedReader br;    
    private static UserSolution usersolution = new UserSolution();

    private final static int CMD_INIT = 1;
    private final static int CMD_APPEND = 2;
    private final static int CMD_COUNT = 3;

    private static int mDigitList1[] = new int[30000];
    private static int mDigitList2[] = new int[30000];

    private static boolean run() throws Exception {

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");
        int query_num = Integer.parseInt(stdin.nextToken());
        boolean ok = false;

        for (int q = 0; q < query_num; q++) {
            stdin = new StringTokenizer(br.readLine(), " ");
            int query = Integer.parseInt(stdin.nextToken());

            if (query == CMD_INIT) {
                stdin = new StringTokenizer(br.readLine(), " ");
                int mCnt1 = Integer.parseInt(stdin.nextToken());
                String temp1 = stdin.nextToken();
                for (int i = 0; i < mCnt1; i++) {
                    mDigitList1[i] = temp1.charAt(i) - '0';
                }
                for (int i = mCnt1; i < 30000; i++){
                    mDigitList1[i] = 0;
                }
                stdin = new StringTokenizer(br.readLine(), " ");
                int mCnt2 = Integer.parseInt(stdin.nextToken());
                String temp2 = stdin.nextToken();
                for (int i = 0; i < mCnt2; i++) {
                    mDigitList2[i] = temp2.charAt(i) - '0';
                }
                for (int i = mCnt2; i < 30000; i++){
                    mDigitList2[i] = 0;
                }
                usersolution.init(mCnt1, mDigitList1, mCnt2, mDigitList2);
                ok = true;
            } else if (query == CMD_APPEND) {
                int mDir = Integer.parseInt(stdin.nextToken());
                int mNum1 = Integer.parseInt(stdin.nextToken());
                int mNum2 = Integer.parseInt(stdin.nextToken());
                usersolution.append(mDir, mNum1, mNum2);
            } else if (query == CMD_COUNT) {
                int mNum = Integer.parseInt(stdin.nextToken());
                int ret = usersolution.countNum(mNum);
                int ans = Integer.parseInt(stdin.nextToken());
                if (ans != ret) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;
        // System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");

        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            int score = run() ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }
        br.close();
    }
}