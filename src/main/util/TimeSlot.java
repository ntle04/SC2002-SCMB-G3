package main.util;

import java.util.ArrayList;
import java.util.List;

public enum TimeSlot {
	SLOT_9_00(1,"0900 - 0930"),
	SLOT_9_30(2,"0930 - 1000"),
	SLOT_10_00(3,"1000 - 1030"),
	SLOT_10_30(4,"1030 - 1100"),
	SLOT_11_00(5,"1100 - 1130"),
	SLOT_11_30(6,"1130 - 1200"),
	SLOT_12_00(7,"1200 - 1230"),
	SLOT_12_30(8,"1230 - 1300"),
	SLOT_13_00(9,"1300 - 1330"),
	SLOT_13_30(10,"1330 - 1430"),
	SLOT_14_00(11,"1400 - 1430"),
	SLOT_14_30(12,"1430 - 1500"),
	SLOT_15_00(13,"1500 - 1530"),
	SLOT_15_30(14,"1530 - 1600"),
	SLOT_16_00(15,"1600 - 1630"),
	SLOT_16_30(16,"1630 - 1700"),
	SLOT_17_00(17,"1700 - 1730"),
	SLOT_17_30(18,"1730 - 1800");

	private int index;
    private String time;

    TimeSlot(int index, String time) {
        this.index = index;
        this.time = time;
    }

    public int getIndex() {
        return index;
    }

    public String getTime() {
        return time;
    }

    public static void printAllTimeSlots() {
        System.out.println("Available Time Slots:");
        for (TimeSlot slot : TimeSlot.values()) {
            System.out.println(slot.getIndex() + ": " + slot.getTime());
        }
    }

    public static TimeSlot getByIndex(int index) {
        for (TimeSlot slot : TimeSlot.values()) {
            if (slot.index == index) {
                return slot;
            }
        }
        throw new IllegalArgumentException("Invalid index: " + index);
    }

    public static TimeSlot getByTime(String input) {

        for (TimeSlot slot : TimeSlot.values()) {
            if (slot.getTime().equalsIgnoreCase(input)) {
                return slot;
            }
        }
        throw new IllegalArgumentException("No enum constant for input: " + input);
    }

    public static List<TimeSlot> getSlotsByIndices(String indices) {
        List<TimeSlot> slots = new ArrayList<>();
        String[] splitIndices = indices.split("\\s+"); // Split by spaces
        for (String indexStr : splitIndices) {
            try {
                int index = Integer.parseInt(indexStr);
                slots.add(getByIndex(index));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid index input: " + indexStr);
            }
        }
        return slots;
    }
}

