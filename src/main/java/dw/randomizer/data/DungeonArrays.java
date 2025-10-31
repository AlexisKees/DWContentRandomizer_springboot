package dw.randomizer.data;

public class DungeonArrays {

    /* ========== 1) NAME TEMPLATES (6 únicos) ========== */
    public static final String[] DUNGEON_NAME_TEMPLATES = {
            "The [PLACE]",
            "(The) [ADJECTIVE] [PLACE]",
            "(The) [PLACE] of the [NOUN]",
            "(The) [NOUN]'s [PLACE]",
            "[PLACE] of the [ADJECTIVE] [NOUN]",
            "(The) [ADJECTIVE] [NOUN]"
    };

    /* ========== 2) PLACES (50 únicos, sin repeticiones) ========== */
    public static final String[] DUNGEON_PLACES = {
            "Archive","Blight","Boneyard","Catacomb","Cave(s)",
            "Cavern(s)","Citadel","Cliff","Crack","Crag",
            "Crypt","Curse","Deep","Delve","Den",
            "Finger","Fist","Fort","Fortress","Grave",
            "Haunt","Hold","Hole(s)","Hollow(s)","Home",
            "House","Jaws","Keep","Lair","Maw",
            "Maze","Mountain","Mouth","Peak","Pit",
            "Remnant","Retreat","Ruin","Shrine","Skull",
            "Spire","Temple","Throne","Tomb","Tooth",
            "Tower","Tunnel(s)","Vault","Warren","Wreck"
    };

    /* ========== 3) ADJECTIVES (50 únicos, sin repeticiones) ========== */
    public static final String[] DUNGEON_ADJECTIVES = {
            "Ancient","Ashen","Black","Bloody","Blue",
            "Broken","Burning","Cracked","Dark","Dead", "Doomed",
            "Endless","Evil","Fallen","Far","Fearsome",
            "Floating","Forbidden","Forgotten","Frozen","Ghostly",
            "Gloomy","Gray","Grim","Hidden","High",
            "Holy","Iron","Jagged","Lonely","Lost",
            "Low","Misty","Petrified","Red","Screaming",
            "Sharp","Shattered","Shifting","Shivering","Shrouded",
            "Stoney","Sunken","Thorny","Thundering","Unholy",
            "White","Wicked","Withered","Yellow"
    };

    /* ========== 4) NOUNS (50 únicos, sin repeticiones) ========== */
    public static final String[] DUNGEON_NOUNS = {
            "Arm","Ash","Beast","Behemoth","Blood",
            "Child","Cinder","Corpse","Crystal","Dagger",
            "Death","Demon","Devil","Doom","Dragon",
            "Eye","Fear","Finger","Fire","Foot",
            "Frog","Ghost","Giant","Goblin","God",
            "Hand","Head","Heart","Horror","Hero",
            "Horn","King","Knave","Priest","Prophet",
            "Queen","Ring","Shard","Skull","Souls",
            "Spear","Spirit","Stone","Sword","Troll",
            "Warrior","Water","Witch","Wizard","Worm"
    };


    /* ================== DUNGEON SIZE (1 nivel) ================== */

    public static final String[] DUNGEON_SIZE = {
            "small","small","small",
            "medium","medium","medium",
            "large","large","large",
            "huge","huge",
            "megadungeon"
    };


    /* ================== DUNGEON THEMES (2 niveles) ================== */

    /* Categorías (según tirada 1d12 → 0..11) */
    public static final String[] DUNGEON_THEME_CATEGORIES = {
            "HOPEFUL","HOPEFUL",
            "MYSTERIOUS","MYSTERIOUS","MYSTERIOUS",
            "GRIM","GRIM","GRIM","GRIM","GRIM","GRIM",
            "GONZO"
    };

    /* ---- HOPEFUL PROMPTS ---- */
    public static final String[] HOPEFUL_PROMPTS = {
            "nature","growth",
            "law","order",
            "beauty","wonder",
            "healing","recovery",
            "protection","defense",
            "completion",
            "inheritance","legacy",
            "balance","harmony",
            "light","life",
            "prophecy",
            "divine influence",
            "transcendence"
    };

    /* ---- MYSTERIOUS PROMPTS ---- */
    public static final String[] MYSTERIOUS_PROMPTS = {
            "burglary","theft",
            "desire","obsession",
            "secrets","deception",
            "imitation","mimicry",
            "inversion","reversal",
            "element",
            "transformation",
            "shadow","spirits",
            "cryptic knowledge",
            "divination","scrying",
            "madness",
            "magic type"
    };

    /* ---- GRIM PROMPTS ---- */
    public static final String[] GRIM_PROMPTS = {
            "pride","hubris",
            "hunger","gluttony",
            "greed","avarice",
            "wildness","savagery",
            "devotion","sacrifice",
            "forbidden knowledge",
            "control","dominance",
            "pain","torture",
            "wrath","war",
            "tragedy","loss",
            "chaos","corruption",
            "darkness","death"
    };

    /* ---- GONZO PROMPTS ---- */
    public static final String[] GONZO_PROMPTS = {
            "constructs","robots",
            "unexpected sentience",
            "space/time travel",
            "advanced technology",
            "utter insanity",
            "alien life",
            "cosmic alignment",
            "other planes",
            "demons","devils",
            "unspeakable horrors",
            "elder gods",
            "roll twice"
    };


    /* ================== DUNGEON FORM (1 nivel) ================== */

    public static final String[] DUNGEON_FORM = {
            "cave",                     // 0
            "cavern",                       // 1
            "mine",                     // 2
            "prison",                        //3
            "crypt",                        //4
            "crypt",                        //5
            "tomb",                     // 6
            "tomb",                     //7
            "catacombs",                        //8
            "catacombs",                        //9
            "stronghold",                       //10
            "fortress",                     //11
            "citadel",                      //12
            "temple",                       //13
            "sanctuary",                        //14
            "tower",                        //15
            "spire",                        //16
            "ruins of steading",
            "roll again, add oddity",
            "ruins of (roll again)",
            "ruins of (roll again)",
    };


    /* ================== DUNGEON SITUATION (1 nivel) ================== */

    public static final String[] DUNGEON_SITUATION = {
            "aboveground","aboveground",
            "part aboveground, part below","part aboveground, part below",
            "belowground","belowground","belowground","belowground","belowground","belowground","belowground",
            "extraordinary (floating, ephemeral, etc.)"
    };


    /* ================== DUNGEON ACCESSIBILITY (1 nivel) ================== */

    public static final String[] DUNGEON_ACCESSIBILITY = {
            "sealed shut",
            "purposely hidden",
            "concealed by natural feature/terrain",
            "concealed by natural feature/terrain",
            "buried (in earth, rubble, etc.)",
            "buried (in earth, rubble, etc.)",
            "blocked by obstacle/out of reach",
            "blocked by obstacle/out of reach",
            "clear/obvious",
            "clear/obvious",
            "multiple entrances",
            "multiple entrances"
    };


    /* ================== DUNGEON BUILDER (1 nivel) ================== */

    public static final String[] DUNGEON_BUILDER = {
            "demigod","demon","alien",
            "natural (caves, etc.)","natural (caves, etc.)",
            "religious order","religious cult","religious order","religious cult",
            "humanoid society","humanoid society","humanoid society","humanoid society","humanoid society",
            "wizard","lunatic",
            "monarch","warlord"
    };


    /* ================== DUNGEON FUNCTION (1 nivel) ================== */

    public static final String[] DUNGEON_FUNCTION = {
            "indiscernible","mysterious",
            "concealment","camouflage",
            "extraction","production",
            "confinement","containment",
            "lair","den","hideout",
            "archive","library","laboratory",
            "commemoration","funerary",
            "worship","devotion",
            "defense","protection",
            "observation","divination",
            "empowerment","intensification",
            "roll twice"
    };


    /* ================== DUNGEON CAUSE OF RUIN (1 nivel) ================== */

    public static final String[] DUNGEON_CAUSE_OF_RUIN = {
            "arcane disaster",
            "damnation","curse",
            "natural disaster (earthquake, etc.)","natural disaster (earthquake, etc.)",
            "plague","famine","drought","plague","famine","drought",
            "overrun by monsters","overrun by monsters",
            "hubris","hubris",
            "war","invasion","war","invasion",
            "depleted resources",
            "better prospects elsewhere"
    };

    /* ================== AREA DRESSING ================== */

    public static final String[] AREA_DRESSING = {
            "breeze","sound","echo",
            "smell","odor","stench",
            "lichen","mold","moss","fungus",
            "drip","seep","puddle","stream",
            "tracks","marks","scratches",
            "sign of activity","struggle","battle",
            "bones","remains of creature",
            "junk","debris","refuse","waste",
            "broken structure","furniture",
            "inscription","ornamentation",
            "roll twice"
    };


    /* ================== AREA EXITS ================== */
    /* En este caso hay dos columnas: NUMBER y DIRECTION */

    public static final String[] AREA_EXITS_NUMBER = {
            "0","0",
            "1",
            "1","1",
            "1",
            "2",
            "2","2",
            "3",
            "4",
            "1d6+1"
    };

    public static final String[] AREA_EXITS_DIRECTION = {
            "down",
            "back",
            "forward","forward","forward",
            "forward",
            "left","left",
            "right",
            "right",
            "up"
    };


    /* ================== DUNGEON DISCOVERY ================== */


    public static final String[] DUNGEON_DISCOVERY_CATEGORIES = {
            "Feature", "Feature", "Feature", "Feature", "Feature", "Feature", "Feature", "Feature",
            "Find", "Find", "Find", "Find"
    };


    public static final String[] DUNGEON_DISCOVERY_FEATURE_PROMPTS = {
            "cave-in","collapse","obstacle",
            "blocked","locked exit",
            "pit","shaft","chasm",
            "pillars","columns",
            "alcoves","niches",
            "bridge","stairs","ramp",
            "well","pool","fountain",
            "puzzle",
            "altar","dais","platform",
            "statue","idol",
            "multi-level","ledges","tiers",
            "hidden","secret exit"
    };


    public static final String[] DUNGEON_DISCOVERY_FIND_PROMPTS = {
            "trinkets","clothing",
            "supplies","tools","gear",
            "light source","fuel","ammo",
            "key","clue","map",
            "weapons","armor",
            "poison","antidote","potion",
            "adventurer","captive",
            "books","scrolls",
            "coins","gems","jewelry",
            "roll again, add magic type",
            "roll feature, add magic type",
            "roll twice"
    };


    /* ================== DUNGEON DANGER ================== */


    public static final String[] DUNGEON_DANGER_CATEGORIES = {
            "Trap", "Trap", "Trap", "Trap",
            "Creature", "Creature", "Creature", "Creature",
            "Creature", "Creature", "Creature", "Creature"
    };


    public static final String[] DUNGEON_DANGER_TRAP_PROMPTS = {
            "alarm",
            "pit",
            "ensnaring","paralyzing",
            "crushing","smashing",
            "piercing","puncturing",
            "chopping","slashing","slicing",
            "confusing (maze, etc.)",
            "gas (poison, etc.)",
            "ambush",
            "based on Element",
            "based on Magic Type",
            "based on Oddity"
    };


    public static final String[] DUNGEON_DANGER_CREATURE_PROMPTS = {
            "Creature leader (with minions)","Creature lord (with minions)",
            "Creature",
            "Creature",
            "Creature",
            "Creature",
            "Creature",
            "Creature",
            "Creature",
            "Creature",
            "Beast vermin (rats, bats, etc.)",
            "Beast vermin (rats, bats, etc.)",
            "Beast vermin (rats, bats, etc.)"
    };

    public static final String[] AREA_RARITY = {
            "common",
            "common",
            "common",
            "common",
            "common",
            "common",
            "common",
            "common",
            "common",
            "unique",
            "unique",
            "unique"
    };

    public static final String[] AREA_TYPE = {
            "Armory",
            "Barracks",
            "Chapel",
            "Kitchen",
            "Library",
            "Laboratory",
            "Prison",
            "Treasure Room",
            "Throne Room",
            "Hallway",
            "Courtyard",
            "Crypt",
            "Storage",
            "Workshop",
            "Dining Hall",
            "Guard Post",
            "Shrine",
            "Vault",
            "Garden",
            "Cellar",
            "Exit",
            "Entrance"
    };
}
