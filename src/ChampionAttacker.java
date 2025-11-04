public class ChampionAttacker implements Comparable<ChampionAttacker> {
    private String championName;
    private String championTrait;
    private int priority;    // Represents Threat or DPS
    private int timestamp;   // Order of arrival in "range"

    public ChampionAttacker(String championName, String championTrait, int priority, int timestamp) {
        this.championName = championName;
        this.championTrait = championTrait;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    // Getters
    public String getChampionName() {
        return championName;
    }

    public String getChampionTrait() {
        return championTrait;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        // Updated to be game-focused
        return championName + " (" + championTrait + ") - Priority: " + priority;
    }

    // Key logic
    @Override
    public int compareTo(ChampionAttacker other) {
        // First compare by priority (higher number = higher priority)
        if (this.priority != other.priority) {
            return other.priority - this.priority;
        }

        // If same priority, compare by timestamp (lower number = earlier arrival)
        return this.timestamp - other.timestamp;
    }
}