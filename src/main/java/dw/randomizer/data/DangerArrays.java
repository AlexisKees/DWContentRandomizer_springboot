package dw.randomizer.data;

public class DangerArrays {
    /* ========== 1) CATEGORIES (d12 → 0..11) ========== */

    public static final String[] DANGER_CATEGORIES = {
            "UNNATURAL ENTITY",
            "HAZARD",
            "HAZARD",
            "HAZARD",
            "HAZARD",
            "HAZARD",
            "CREATURE",
            "CREATURE",
            "CREATURE",
            "CREATURE",
            "CREATURE",
            "CREATURE"
    };


    /* ========== 2) SUBCATEGORIES POR CATEGORÍA (d12) ========== */

    /* ---- UNNATURAL ENTITY ---- */
    public static final String[] UNNATURAL_ENTITY_SUBCATEGORIES = {
            "Divine",
            "Planar",
            "Planar",
            "Planar",
            "Undead",
            "Undead",
            "Undead",
            "Undead",
            "Undead",
            "Undead",
            "Undead",
            "Undead"
    };

    /* ---- HAZARD ---- */
    public static final String[] HAZARD_SUBCATEGORIES = {
            "Unnatural",
            "Unnatural",
            "Natural",
            "Natural",
            "Natural",
            "Natural",
            "Natural",
            "Natural",
            "Natural",
            "Natural",
            "Natural",
            "Natural"
    };

    /* ---- CREATURE ---- (sin subtítulos propios) */
    public static final String[] CREATURE_SUBCATEGORIES = {
            "Creature","Creature","Creature","Creature",
            "Creature","Creature","Creature","Creature",
            "Creature","Creature","Creature","Creature"
    };


    /* ========== 3) PROMPTS POR SUBTÍTULO (d12) ========== */
    /* ---- UNNATURAL ENTITY → DIVINE ---- */
    public static final String[] DIVINE_PROMPTS = {
            "agent",
            "agent",
            "agent",
            "agent",
            "agent",
            "champion",
            "champion",
            "champion",
            "champion",
            "army/force",
            "army/force",
            "avatar/embodiment"
    };

    /* ---- UNNATURAL ENTITY → PLANAR ---- */
    public static final String[] PLANAR_PROMPTS = {
            "imp","sprite",
            "imp","sprite",
            "imp","sprite",
            "lesser demon","lesser elemental",
            "lesser demon","lesser elemental",
            "lesser demon","lesser elemental",
            "demon","elemental",
            "demon","elemental",
            "demon","elemental",
            "greater demon","greater elemental",
            "greater demon","greater elemental",
            "devil lord","elemental lord"
    };

    /* ---- UNNATURAL ENTITY → UNDEAD ---- */
    public static final String[] UNDEAD_PROMPTS = {
            "haunt","wisp",
            "haunt","wisp",
            "haunt","wisp",
            "haunt","wisp",
            "phantom","shadow",
            "phantom","shadow",
            "ghost","specter",
            "ghost","specter",
            "ghost","specter",
            "wight","wraith","revenant",
            "wight","wraith","revenant",
            "spirit lord","lich"
    };

    /* ---- HAZARD → UNNATURAL ---- */
    public static final String[] UNNATURAL_PROMPTS = {
            "taint","blight","curse",
            "taint","blight","curse",
            "taint","blight","curse",
            "taint","blight","curse",
            "taint","blight","curse",
            "magical: natural + MAGIC TYPE",
            "magical: natural + MAGIC TYPE",
            "magical: natural + MAGIC TYPE",
            "magical: natural + MAGIC TYPE",
            "planar: natural + ELEMENT",
            "planar: natural + ELEMENT",
            "divine: natural + deity"
    };

    /* ---- HAZARD → NATURAL ---- */
    public static final String[] NATURAL_PROMPTS = {
            "oddity-based",
            "tectonic","volcanic",
            "unseen pitfall (chasm, crevasse, abyss, rift)",
            "unseen pitfall (chasm, crevasse, abyss, rift)",
            "ensnaring (bog, mire, tarpit, quicksand, etc.)",
            "ensnaring (bog, mire, tarpit, quicksand, etc.)",
            "defensive (created by local creature)",
            "meteorological (blizzard, thunderstorm, sandstorm, etc.)",
            "meteorological (blizzard, thunderstorm, sandstorm, etc.)",
            "meteorological (blizzard, thunderstorm, sandstorm, etc.)",
            "seasonal (fire, flood, avalanche, etc.)",
            "impairing (mist, fog, murk, gloom, miasma, etc.)"
    };

}
