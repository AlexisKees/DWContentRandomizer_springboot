package dw.randomizer.data;

public class DiscoveryArrays {

    /* ========== 1) CATEGORIES (d12 → índices 0–11) ========== */

    public static final String[] DISCOVERY_CATEGORIES = {
            "UNNATURAL FEATURE",
            "NATURAL FEATURE",
            "NATURAL FEATURE",
            "NATURAL FEATURE",
            "EVIDENCE",
            "EVIDENCE",
            "CREATURE",
            "CREATURE",
            "STRUCTURE",
            "STRUCTURE",
            "STRUCTURE",
            "STRUCTURE"
    };


    /* ========== 2) SUBCATEGORIES POR CATEGORÍA (d12) ========== */
    /* ---- UNNATURAL FEATURE ---- */
    public static final String[] UNNATURAL_FEATURE_SUBCATEGORIES = {
            "Divine",
            "Planar",
            "Planar",
            "Arcane",
            "Arcane",
            "Arcane",
            "Arcane",
            "Arcane",
            "Arcane",
            "Arcane",
            "Arcane",
            "Arcane"
    };

    /* ---- NATURAL FEATURE ---- */
    public static final String[] NATURAL_FEATURE_SUBCATEGORIES = {
            "Lair",
            "Lair",
            "Terrain Change",
            "Terrain Change",
            "Water Feature",
            "Water Feature",
            "Landmark",
            "Landmark",
            "Flora","Fauna",
            "Flora","Fauna",
            "Resource",
            "Resource"
    };

    /* ---- EVIDENCE ---- */
    public static final String[] EVIDENCE_SUBCATEGORIES = {
            "Tracks","Tracks",
            "Tracks","Tracks",
            "Tracks","Tracks",
            "Tracks","Tracks",
            "Tracks","Tracks",
            "Tracks","Tracks",
            "Remains","Debris",
            "Remains","Debris",
            "Remains","Debris",
            "Remains","Debris",
            "Stash","Stash",
            "Stash","Stash"
    };

    /* ---- CREATURE ----
       (esta categoría no tiene subtítulos propios en la tabla;
        si preferís homogeneidad, podés usar un solo subcategoría repetida)
    */
    public static final String[] CREATURE_SUBCATEGORIES = {
            "Creature","Creature","Creature","Creature",
            "Creature","Creature","Creature","Creature",
            "Creature","Creature","Creature","Creature"
    };

    /* ---- STRUCTURE ---- */
    public static final String[] STRUCTURE_SUBCATEGORIES = {
            "Enigmatic",
            "Infrastructure",
            "Infrastructure",
            "Dwelling",
            "Dwelling",
            "Religious",
            "Religious",
            "Ruin",
            "Ruin",
            "Ruin",
            "Ruin",
            "Steading"
    };


    /* ========== 3) PROMPTS POR SUBTÍTULO (d12) ========== */
    /* ---- UNNATURAL FEATURE → DIVINE ---- */
    public static final String[] DIVINE_PROMPTS = {
            "presence","manifestation",
            "protected place",
            "protected place",
            "protected place",
            "protected place",
            "protected place",
            "protected place",
            "cursed place","defiled place",
            "cursed place","defiled place",
            "cursed place","defiled place",
            "blessed place","sacred place",
            "blessed place","sacred place"
    };

    /* ---- UNNATURAL FEATURE → PLANAR ---- */
    public static final String[] PLANAR_PROMPTS = {
            "outpost",
            "portal","gate",
            "portal","gate",
            "portal","gate",
            "rift","tear",
            "rift","tear",
            "rift","tear",
            "rift","tear",
            "distortion","warp",
            "distortion","warp",
            "distortion","warp",
            "distortion","warp"
    };

    /* ---- UNNATURAL FEATURE → ARCANE ---- */
    public static final String[] ARCANE_PROMPTS = {
            "blight","mutation",
            "blight","mutation",
            "blight","mutation",
            "blight","mutation",
            "enchantment","portal",
            "enchantment","portal",
            "enchantment","portal",
            "taint","residue",
            "taint","residue",
            "taint","residue",
            "source","resource",
            "source","resource"
    };

    /* ---- NATURAL FEATURE → LAIR ---- */
    public static final String[] LAIR_PROMPTS = {
            "Lair RUIN",
            "Lair RUIN",
            "Lair RUIN",
            "Lair RUIN",
            "cave","tunnel",
            "cave","tunnel",
            "cave","tunnel",
            "nest","hive","aerie",
            "nest","hive","aerie",
            "den","burrow","warren",
            "den","burrow","warren",
            "den","burrow","warren"
    };

    /* ---- NATURAL FEATURE → TERRAIN CHANGE ---- */
    public static final String[] TERRAIN_CHANGE_PROMPTS = {
            "hollow","cleft","defile",
            "hollow","cleft","defile",
            "hollow","cleft","defile",
            "canyon","valley","vale","dale",
            "canyon","valley","vale","dale",
            "canyon","valley","vale","dale",
            "multilevel","tiered",
            "multilevel","tiered",
            "pocket of TERRAIN",
            "pocket of TERRAIN",
            "slope up","down",
            "slope up","down"
    };

    /* ---- NATURAL FEATURE → WATER FEATURE ---- */
    public static final String[] WATER_FEATURE_PROMPTS = {
            "sea","ocean",
            "river",
            "river",
            "river",
            "lake","pond","mere","tarn",
            "lake","pond","mere","tarn",
            "brook","stream","rill",
            "brook","stream","rill",
            "brook","stream","rill",
            "brook","stream","rill",
            "spring","hot spring",
            "spring","hot spring"
    };

    /* ---- NATURAL FEATURE → LANDMARK ---- */
    public static final String[] LANDMARK_PROMPTS = {
            "Landmark ODDITY",
            "striking landscape",
            "striking landscape",
            "earth-based",
            "earth-based",
            "earth-based",
            "plant-based",
            "plant-based",
            "plant-based",
            "water-based",
            "water-based",
            "water-based"
    };

    /* ---- NATURAL FEATURE → FLORA/FAUNA ---- */
    public static final String[] FLORA_PROMPTS = {
            "notable plant","notable flower",
            "notable plant","notable flower",
            "notable plant","notable flower",
            "notable tree","notable brush",
            "notable tree","notable brush",
            "useful plant","useful herb","useful root",
            "useful plant","useful herb","useful root",
    };

    public static final String[] FAUNA_PROMPTS = {
            "notable BEAST",
            "notable BEAST",
            "notable BEAST",
            "useful BEAST",
            "useful BEAST"
    };

    /* ---- NATURAL FEATURE → RESOURCE ---- */
    public static final String[] RESOURCE_PROMPTS = {
            "game","fruit","vegetable",
            "game","fruit","vegetable",
            "game","fruit","vegetable",
            "timber","stone",
            "timber","stone",
            "timber","stone",
            "herbs source","spice source","dye source",
            "herbs source","spice source","dye source",
            "herbs source","spice source","dye source",
            "copper","tin","iron",
            "copper","tin","iron",
            "gold","silver","gems"
    };

    /* ---- EVIDENCE → TRACKS/SPOOR ---- */
    public static final String[] TRACKS_PROMPTS = {
            "trail of blood","trail of fluid",
            "trail of blood","trail of fluid",
            "signs of violence",
            "signs of violence",
            "many signs",
            "many signs",
            "definite","recent","clear",
            "definite","recent","clear",
            "definite","recent","clear",
            "definite","recent","clear",
            "faint","old","unclear",
            "faint","old","unclear"
    };

    /* ---- EVIDENCE → REMAINS/DEBRIS ---- */
    public static final String[] REMAINS_PROMPTS = {
            "bones of CREATURE",
            "bones of CREATURE",
            "bones of CREATURE",
            "bones of CREATURE",
            "CREATURE carcass",
            "CREATURE carcass",
            "CREATURE carcass"
    };

    public static final String[] DEBRIS_PROMPTS = {
            "junk","refuse",
            "junk","refuse",
            "junk","refuse",
            "lost supplies","gear",
            "tools","weapons","armor"
    };


    /* ---- EVIDENCE → STASH/CACHE ---- */
    public static final String[] STASH_PROMPTS = {
            "trinkets","coins",
            "trinkets","coins",
            "trinkets","coins",
            "trinkets","coins",
            "trinkets","coins",
            "tools","weapons","armor",
            "tools","weapons","armor",
            "tools","weapons","armor",
            "map","note",
            "map","note",
            "food","supplies",
            "treasure"
    };

    /* ---- STRUCTURE → ENIGMATIC ---- */
    public static final String[] ENIGMATIC_PROMPTS = {
            "Enigmatic ODDITY",
            "Enigmatic ODDITY",
            "mound","earthworks",
            "mound","earthworks",
            "mound","earthworks",
            "mound","earthworks",
            "monument","megalith",
            "monument","megalith",
            "monument","megalith",
            "statue","idol","totem",
            "statue","idol","totem",
            "statue","idol","totem"
    };

    /* ---- STRUCTURE → INFRASTRUCTURE ---- */
    public static final String[] INFRASTRUCTURE_PROMPTS = {
            "signpost","marker",
            "signpost","marker",
            "signpost","marker",
            "signpost","marker",
            "bridge","aqueduct",
            "bridge","aqueduct",
            "track","path","trail","road",
            "track","path","trail","road",
            "track","path","trail","road",
            "track","path","trail","road",
            "mine","quarry",
            "mine","quarry"
    };

    /* ---- STRUCTURE → DWELLING ---- */
    public static final String[] DWELLING_PROMPTS = {
            "campsite","hovel","hut",
            "campsite","hovel","hut",
            "campsite","hovel","hut",
            "campsite","hovel","hut",
            "homestead","farmstead",
            "homestead","farmstead",
            "homestead","farmstead",
            "homestead","farmstead",
            "inn","toll house","mill",
            "inn","toll house","mill",
            "tower","keep","castle",
            "tower","keep","castle"
    };

    /* ---- STRUCTURE → RELIGIOUS ---- */
    public static final String[] RELIGIOUS_PROMPTS = {
            "grave marker",
            "grave marker",
            "grave marker",
            "graveyard","burial ground",
            "graveyard","burial ground",
            "graveyard","burial ground",
            "tomb","crypt","barrow",
            "tomb","crypt","barrow",
            "tomb","crypt","barrow",
            "temple","monastery",
            "temple","monastery",
            "great temple","great sanctuary"
    };

    /* ---- STRUCTURE → RUIN ---- */
    public static final String[] RUIN_PROMPTS = {
            "DUNGEON",
            "DUNGEON",
            "DUNGEON",
            "STEADING",
            "STEADING",
            "STEADING",
            "religious",
            "religious",
            "dwelling",
            "dwelling",
            "infrastructure",
            "infrastructure"
    };

    /* ---- STRUCTURE → STEADING ---- */
    public static final String[] STEADING_PROMPTS = {
            "village",
            "village",
            "village",
            "village",
            "village",
            "town",
            "town",
            "town",
            "keep",
            "keep",
            "keep",
            "city"
    };

    public static final String[] TREASURE_TABLE = {
            "A few coins, 2d8 or so",
            "A useful item",
            "Several coins, about 4d10",
            "A small valuable (gem, art), worth 2d10x10 coins, 0 weight",
            "Some minor magical trinket",
            "Useful clue (map, note, etc.)",
            "Bag of coins, 1d4x100, 1 weight per 100",
            "A small item (gem, art) of great value (2d6x100 coins, 0 weight)",
            "A chest of coins and other small valuables (worth 3d6x100 coins, 1 weight per 100)",
            "A magical item or magical effect",
            "Many bags of coins, 2d4x100 or so",
            "A sign of office (crown, banner) worth 3d4x100 coins (1 weight)",
            "A large art item worth 4d4x100 coins (1 weight)",
            "Unique item worth at least 5d4x100 coins",
            "Everything needed to learn a new spell, and roll again",
            "A portal or secret path (or directions to one), and roll again",
            "Something relating to one of the characters, and roll again",
            "A hoard: 1d10x1000 coins (1 weight per 100), and 1d10x10 gems worth 2d6x100 each"
    };


}
