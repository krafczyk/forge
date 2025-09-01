package forge.item;

import java.util.List;
import java.util.TreeMap;

public class BoosterSlot {
    private final String slotName;
    private float startRange = 0.0f;
    private final TreeMap<Float, String> slotSegments = new TreeMap<>();

    public BoosterSlot(final String slotName, final List<String> contents) {
        this.slotName = slotName;
        parseContents(contents);
    }

    public final String getSlotName() {
        return slotName;
    }

    public static BoosterSlot parseSlot(final String slotName, final List<String> contents) {
        return new BoosterSlot(slotName, contents);
    }

    private void parseContents(List<String> contents) {
        for (String content : contents) {
            if (content.startsWith("#")) {
                continue;
            }
            String[] parts = content.split("=", 2);
            String key = parts[0];
            String value = parts[1];

            if (key.equalsIgnoreCase("Base")) {
                slotSegments.put((float)0., value);
            } else if (key.equalsIgnoreCase("Replace")) {
                // Are there other things?
                String[] replaceParts = value.split(" ", 2);
                float pct = Float.parseFloat(replaceParts[0]);
                startRange += pct;
                slotSegments.put(startRange, replaceParts[1]);
            }
        }
    }

    public String replaceSlot() {
        float rand = (float) Math.random();
        return slotSegments.floorEntry(rand).getValue();
    }

    public TreeMap<Float, String> getSlotSegments() {
        return slotSegments;
    }
}
