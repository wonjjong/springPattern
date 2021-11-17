package hello.advanced.app;

import java.util.*;

public class TestClass {
    public static void main(String[] args) {
        double time = 3.5;

        String[ ] logs ={"08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00"
        ,"08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00",
        "08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00","08:00","17:00"};
        String solution = solution(logs);
        System.out.println("solution = " + solution);

    }
    public static String solution(double time, String[][] plans) {
        String answer = "";
        //월 출근시간 1PM , 퇴근시간 6PM 18:00
        //금 출근 9:30 AM , 퇴근시간 6PM 퇴근

        for (int i = 0; i < plans.length; i++) {
            double holidayTime = time;
            String nation = plans[i][0];
            String depart = plans[i][1];
            String arrive = plans[i][2];

            int departNum = Integer.parseInt(plans[i][1].replaceAll("[^0-9]", ""));
            int arriveNum = Integer.parseInt(plans[i][2].replaceAll("[^0-9]", ""));

            //출발시간과 퇴근시간 계산
            if (isPM(depart)) {
                //출발시간이 오후
                if (departNum == 12) {
                    //12PM
                    holidayTime -= 6;
                } else if (departNum >= 1 && departNum <= 6) {
                    //1,2,3,4,5,6 PM
                    holidayTime -= (6 - departNum);
                } else {
                    //7,8,9,10,11 PM
                    //계산할 필요 없다.
                }

            } else {
                //출발시간이 오전
                if (departNum == 12 || (departNum >= 1 && departNum <= 9)) {
                    //근무시간이 8시간30분임.
                    holidayTime -= (8.5);
                } else {
                    //10시,11시
                    holidayTime -= (6 + 12 - departNum);
                }
            }

            //도착시간과 출근시간 계산.
            if (isPM(arrive)) {
                //도착시간이 오후
                if (arriveNum >= 6 && arriveNum < 12) {
                    // 퇴근시간이 넘어서 도착했을 때. 6,7,8,9,10,11시
                    //근무시간이 5시간
                    holidayTime -= 5;
                } else if (arriveNum > 1 && arriveNum < 6) {
                    // 업무 시간 사이에 도착했을 때. 2시~5시
                    holidayTime -= (arriveNum - 1);
                }
            } //오전인 경우, 12PM은 계산 할 필요 없음

            if (holidayTime >= 0) {
                answer = nation;
            } else break;
        }

        return answer;
    }

    public static boolean isPM(String time) {
        return time.substring(time.length() - 2).equals("PM");
    }

    public static int solution(String[] ings, String[] menu, String[] sell) {
        int answer = 0;
        Map<Character, Integer> ingredientPrice = new HashMap<>();

        //ingredient[r] = 10

        for (int i = 0; i < ings.length; i++) {
            String[] split = ings[i].split(" ");
            ingredientPrice.put(split[0].charAt(0), Integer.parseInt(split[1]));
        }

        //PIZZA arraak 145 format
        Map<String, Integer> menuProfit = new HashMap<>();
        for (int i = 0; i < menu.length; i++) {
            String[] split = menu[i].split(" ");
            String menuName = split[0];
            String ingredient = split[1];
            String menuPrice = split[2];

            int priceForMenu = 0;
            for (int j = 0; j < ingredient.length(); j++) {
                Integer integer = ingredientPrice.get(ingredient.charAt(j));
                priceForMenu += integer;
            }

            int profit = Integer.parseInt(menuPrice) - priceForMenu;
            menuProfit.put(menuName, profit);

        }

        //"BREAD 5", "ICECREAM 100", "PIZZA 7", "JUICE 10", "WATER 1"
        for (int i = 0; i < sell.length; i++) {
            String[] split = sell[i].split(" ");
            String menuName = split[0];
            String selledAmount = split[1];

            int profit = menuProfit.get(menuName) * Integer.parseInt(selledAmount);

            answer += profit;
        }


        return answer;
    }

    public static String solution(String[] log) {
        String answer = "";

        int sumMinutes = 0;

        //공부 시작후 5분이 지나기전에 중지했다면 공부시간에 포함 x
        //공부 시작후 1시간 45분이 넘어서 중지했다면 1시간 45분 까지만 공부한 시간으로 인정(105분)
        for (int i = 0; i < log.length; i += 2) {
            String start = log[i]; //08:30
            String end = log[i + 1]; //09:00

            String[] splitStart = start.split(":");
            String[] splitEnd = end.split(":");
            int startMinutes = Integer.parseInt(splitStart[0]) * 60 + Integer.parseInt(splitStart[1]);
            int endMinutes = Integer.parseInt(splitEnd[0]) * 60 + Integer.parseInt(splitEnd[1]);
            if (endMinutes - startMinutes < 5) continue;
            else {
                sumMinutes += ((endMinutes - startMinutes) >= 105 ? 105 : endMinutes - startMinutes);
            }
        }
        int hour = sumMinutes / 60;
        int minute = sumMinutes % 60;

        if (hour < 10) answer += "0" + hour;
        else answer += hour;

        answer += ":";
        if (minute < 10) answer += "0" + minute;
        else answer += minute;
        return answer;
    }
//    class Solution {
//        int[] prior = {0,1,2};
//
//        public long solution(String expression) {
//
//
//            //3가지 연산자의 우선순위를 정한다.
//            //그 우선순위를 가지고 계산을한다.
//            // 최대값을 구한다.
//            //음수인경우 절대값을 씌운다.
//            //같은 연산자끼리는 앞에있는 것의 우선순위가 더 높다.
//
//            long answer = 0;
//            int output[] = new int[3];
//            boolean isVisit[] = new boolean[3];
//
//            permutation(prior,output,isVisit,0,3,3);
//
//            return answer;
//        }
//        public static void permutation(int[] array, int[] output, boolean[] isVisit, int depth, int length, int count) {
//            if(count==0) {
//                System.out.println(Arrays.toString(Arrays.stream(output).toArray()));
//                return;
//            }
//            for(int i=0; i<length; i++) {
//                if(isVisit[i]!=true) {
//                    isVisit[i] = true;
//                    output[depth] = array[i];
//                    permutation(array, output, isVisit, depth+1, length, count-1);
//                    isVisit[i] = false;
//                }
//            }
//        }
//    }

// 백트래킹 사용
// 사용 예시 : combination(arr, visited, 0, n, r)
    /*
static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
    if(r == 0) {
        print(arr, visited, n);
        return;
    }

    for(int i=start; i<n; i++) {
        visited[i] = true;
        combination(arr, visited, i + 1, n, r - 1);
        visited[i] = false;
    }
} */


}
