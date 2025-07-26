package leetcode.code.java;

public class GasStation {
    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        System.out.println(canCompleteCircuit1(gas, cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int gasArrLength = gas.length;
        if (gasArrLength == 1) {
            return gas[0] - cost[0] >= 0 ? 0 : -1;
        }
        int[] gasLeft = new int[gasArrLength];
        for (int i = 0; i < gasArrLength; i++) {
            gasLeft[i] = gas[i] - cost[i];
        }
        int g = 0;
        while (g < gasArrLength) {
            if (gasLeft[g] > 0) {
                int totalGas = gasLeft[g];
                int count = 1;
                for (int mid = g + 1; mid < gasArrLength && totalGas > 0; mid++) {
                    totalGas = totalGas + gasLeft[mid];
                    ++count;
                }
                for (int start = 0; start < g && totalGas > 0; start++) {
                    totalGas = totalGas + gasLeft[start];
                    ++count;
                }
                if (totalGas >= 0 && count == gasArrLength) {
                    return g;
                }
            }
            ++g;
        }
        return -1;
    }

    public static int canCompleteCircuit1(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0;
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }
        if (totalCost > totalGas) {
            return -1;
        }
        int total = 0, startIndex = 0;
        for (int i = 0; i < n; i++) {
            total += (gas[i] - cost[i]);
            if (total < 0) {
                total = 0;
                startIndex = i + 1;
            }
        }
        return startIndex;
    }
}
