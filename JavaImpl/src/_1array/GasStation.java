package _1array;

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int startIndex = 0;
        int tank = 0;
        int total = 0;

        for (int i = 0; i < gas.length; ++i) {
            total += gas[i] - cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                startIndex = i + 1;
                tank = 0;
            }
        }

        return (total < 0) ? -1 : startIndex;
    }

}
