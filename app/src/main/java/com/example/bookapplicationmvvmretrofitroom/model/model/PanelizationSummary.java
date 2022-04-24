package com.example.bookapplicationmvvmretrofitroom.model.model;

import java.io.Serializable;

public class PanelizationSummary implements Serializable {
        public boolean containsEpubBubbles;
        public boolean containsImageBubbles;

    public PanelizationSummary(boolean containsEpubBubbles, boolean containsImageBubbles) {
        this.containsEpubBubbles = containsEpubBubbles;
        this.containsImageBubbles = containsImageBubbles;
    }

    public boolean isContainsEpubBubbles() {
        return containsEpubBubbles;
    }

    public void setContainsEpubBubbles(boolean containsEpubBubbles) {
        this.containsEpubBubbles = containsEpubBubbles;
    }

    public boolean isContainsImageBubbles() {
        return containsImageBubbles;
    }

    public void setContainsImageBubbles(boolean containsImageBubbles) {
        this.containsImageBubbles = containsImageBubbles;
    }
}
