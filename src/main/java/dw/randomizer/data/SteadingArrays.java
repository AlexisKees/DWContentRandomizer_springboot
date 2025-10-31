package dw.randomizer.data;

public class SteadingArrays {

    /* ========= 1) NIVEL SUPERIOR: SETTLEMENT SIZE (d12) ========= */

    public static final String[] SETTLEMENT_SIZE = {
            "VILLAGE", "VILLAGE", "VILLAGE", "VILLAGE", "VILLAGE",
            "TOWN", "TOWN", "TOWN",
            "KEEP", "KEEP", "KEEP",
            "CITY"
    };


    /* ========= 2) NIVEL POR TIPO: FEATURES & PROBLEMS ========= */

    /* ----- VILLAGE ----- */

    public static final String[] VILLAGE_FEATURES = {
            "Natural defenses: safe, +defenses",
            "Natural defenses: safe, +defenses",
            "Natural defenses: safe, +defenses",
            "Abundant resources: +prosperity, resource (GM choice), enmity (GM choice)",
            "Abundant resources: +prosperity, resource (GM choice), enmity (GM choice)",
            "Abundant resources: +prosperity, resource (GM choice), enmity (GM choice)",
            "Protected by another steading: oath (that steading), +defenses",
            "Protected by another steading: oath (that steading), +defenses",
            "On a major road: trade (GM choice), +prosperity",
            "On a major road: trade (GM choice), +prosperity",
            "Built around a wizard’s tower: personage (the wizard), blight (arcane creatures)",
            "Built on a site of religious significance: divine, history (GM choice)"
    };

    public static final String[] VILLAGE_PROBLEMS = {
            "Surrounded by arid or uncultivable land: need (food)",
            "Surrounded by arid or uncultivable land: need (food)",
            "Dedicated to a deity: religious (that deity), enmity (steading of opposing deity)",
            "Dedicated to a deity: religious (that deity), enmity (steading of opposing deity)",
            "Recently at war: -population, -prosperity if fought to the end, -defenses if lost",
            "Recently at war: -population, -prosperity if fought to the end, -defenses if lost",
            "Monster problem: blight (that monster), need (adventurers)",
            "Monster problem: blight (that monster), need (adventurers)",
            "Absorbed another village: +population, lawless",
            "Absorbed another village: +population, lawless",
            "Remote or unwelcoming: -prosperity, dwarven or elven or other non-human",
            "Remote or unwelcoming: -prosperity, dwarven or elven or other non-human"
    };


    /* ----- TOWN ----- */

    public static final String[] TOWN_FEATURES = {
            "Booming: +booming, lawless",
            "At a crossroads: market, +prosperity",
            "At a crossroads: market, +prosperity",
            "Defended by another steading: oath (that steading), +defenses",
            "Defended by another steading: oath (that steading), +defenses",
            "Built around a church: power (divine)",
            "Built around a church: power (divine)",
            "Center of craft: craft (your choice), resource (something required for that craft)",
            "Center of craft: craft (your choice), resource (something required for that craft)",
            "Center of craft: craft (your choice), resource (something required for that craft)",
            "Stands around a military post: +defenses",
            "Stands around a military post: +defenses"
    };

    public static final String[] TOWN_PROBLEMS = {
            "Outgrowing a vital resource: need (that resource), trade (a steading with that resource)",
            "Outgrowing a vital resource: need (that resource), trade (a steading with that resource)",
            "Offers defense to others: oath (GM choice), -defenses",
            "Offers defense to others: oath (GM choice), -defenses",
            "Outlaw rumored to live there: personage (the outlaw), enmity (steading preyed upon)",
            "Outlaw rumored to live there: personage (the outlaw), enmity (steading preyed upon)",
            "Controls a good/service: exotic (that good/service), enmity (steading with ambition)",
            "Controls a good/service: exotic (that good/service), enmity (steading with ambition)",
            "Suffers from disease: -population",
            "Suffers from disease: -population",
            "Popular meeting place: +population, lawless",
            "Popular meeting place: +population, lawless"
    };


    /* ----- KEEP ----- */

    public static final String[] KEEP_FEATURES = {
            "Belongs to a noble family: +prosperity, power (political)",
            "Belongs to a noble family: +prosperity, power (political)",
            "Run by a skilled commander: personage (the commander), +defenses",
            "Run by a skilled commander: personage (the commander), +defenses",
            "Stands watch over a trade road: +prosperity, guild (trade)",
            "Stands watch over a trade road: +prosperity, guild (trade)",
            "Used to train special troops: arcane, +population",
            "Used to train special troops: arcane, +population",
            "Surrounded by fertile land: remove need (Supplies)",
            "Surrounded by fertile land: remove need (Supplies)",
            "Stands on a border: +defenses, enmity (steading on the other side of the border)",
            "Stands on a border: +defenses, enmity (steading on the other side of the border)"
    };

    public static final String[] KEEP_PROBLEMS = {
            "Built on a naturally defensible position: safe, -population",
            "Built on a naturally defensible position: safe, -population",
            "Built on a naturally defensible position: safe, -population",
            "Formerly occupied by another power: enmity (steadings of that power)",
            "Safe haven for brigands: lawless",
            "Built to defend from a specific threat: blight (that threat)",
            "Has seen horrible bloody war: history (battle), blight (restless spirits)",
            "Is given the worst of the worst: need (skilled recruits)",
            "Suffers from disease: -population",
            "Suffers from disease: -population",
            "Popular meeting place: +population, -law",
            "Popular meeting place: +population, -law"
    };


    /* ----- CITY ----- */

    public static final String[] CITY_FEATURES = {
            "Permanent defenses, such as walls: +defenses, oath (GM’s choice)",
            "Permanent defenses, such as walls: +defenses, oath (GM’s choice)",
            "Permanent defenses, such as walls: +defenses, oath (GM’s choice)",
            "Ruled by a single individual: personage (the ruler), power (political)",
            "Ruled by a single individual: personage (the ruler), power (political)",
            "Ruled by a single individual: personage (the ruler), power (political)",
            "Diverse: dwarven or elven or both",
            "Trade hub: trade (every nearby steading), +prosperity",
            "Trade hub: trade (every nearby steading), +prosperity",
            "Trade hub: trade (every nearby steading), +prosperity",
            "Ancient, built on top of its own ruins: history (your choice), divine",
            "Center of learning: arcane, craft (your choice), power (arcane)"
    };

    public static final String[] CITY_PROBLEMS = {
            "Outgrown its resources: +population, need (food)",
            "Outgrown its resources: +population, need (food)",
            "Outgrown its resources: +population, need (food)",
            "Designs on nearby territory: enmity (nearby steadings), +defenses",
            "Designs on nearby territory: enmity (nearby steadings), +defenses",
            "Designs on nearby territory: enmity (nearby steadings), +defenses",
            "Ruled by a theocracy: -defenses, power (divine)",
            "Ruled by a theocracy: -defenses, power (divine)",
            "Ruled by the people: -defenses, +population",
            "Ruled by the people: -defenses, +population",
            "Supernatural defenses: +defenses, blight (related supernatural creatures)",
            "Occupies a place of power: arcane, personage (watcher of the place of power), blight (arcane creatures)"
    };

    public static final String[] DANGER_LEVEL = {
            "Perilous",
            "Perilous",
            "Unsafe",
            "Unsafe",
            "Unsafe",
            "Unsafe",
            "Unsafe",
            "Unsafe",
            "Safe",
            "Safe",
            "Safe",
            "Safe",
            "Safe",
    };

    public static final String[] TAGS = {
            "Barren",
            "Blighted",
            "Civilized",
            "Contested",
            "Defensible",
            "Difficult",
            "Enchanted",
            "Holy/Unholy",
            "Property of ( ____ )",
            "Resource ( ____ )"
    };

    public static final String[] STEADING_NAMES = {
            "Aldott (Blessed)",
            "Almahid (Applebridge)",
            "Elesett (Fallen)",
            "Feketz (Black Rock)",
            "Godor (Pit)",
            "Kelegaz (Eastford)",
            "Kigyov (Snake Swamp)",
            "Kiralokas (Queen’s Castle)",
            "Kiralsir (King’s Grave)",
            "Magziklar (Highcliff)",
            "Mocsar (Fen)",
            "Nagyvros (Hightown)",
            "Okorm (Oxfield)",
            "Orkfal (Orcwall)",
            "Perov (Redwater)",
            "Soterd (Dark Wood)",
            "Tehenvar (Cow Town)",
            "Toron (Tower)",
            "Torott (Ironhold)",
            "Utoslzer (Last Stand)",
            "Valavolg (Greendale)",
            "Vastar (Dwarf Watch)",
            "Viz (Oxfield)",
            "Volgyom (Valley)",
            "Zoldom (Green Hill)",

            "Asala Ilu (Desert Town)",
            "Atijo Ina (Old Fire)",
            "Bajesia (Broken Banner)",
            "Dudu Olomi (Blackmarsh)",
            "Ebutte Meta (Three Ports)",
            "Ejodo (Snake River)",
            "Esukale (Devil’s Dinner)",
            "Fadormi (Silver Spring)",
            "Funfumi (Whitewater)",
            "Gooluna (Gold Road)",
            "Ijisofo (Storm Hollow)",
            "Ikukenu (Dearth’s Door)",
            "Jinibi (Far Place)",
            "Oba Ile (King’s Home)",
            "Oduroke (Prayer Hill)",
            "Ogbinibi (Farming Place)",
            "Ogunibi (Battle Place)",
            "Okanigi (One Tree)",
            "Okutasibo (Stone Marker)",
            "Olorusura (God’s Treasure)",
            "Olusajeki (Wizard’s Keep)",
            "Oluwakaji (Lord’s Tomb)",
            "Opolokuta (Many Stones)",
            "Opoligi (Many Trees)",

            "Etuvartio (Outpost)",
            "Hopea Kaivos (Silver Mine)",
            "Kalapunki (Fish Town)",
            "Kivimurri (Stone Wall)",
            "Maaginen (Magic)",
            "Maki Linna (Hill Castle)",
            "Merenranta (Seaside)",
            "Metsäsmaat (Hunting Ground)",
            "Mustakota (Black Hut)",
            "Mäenrinne (Hillside)",
            "Paja (Forge)",
            "Pienni Paikka (Low Place)",
            "Pyhä Paikka (Holy Place)",
            "Rantakallio (Cliff)",
            "Rikki (Broken)",
            "Suo (Swamp)",
            "Suosi (Favored)",
            "Torni (Tower)",
            "Turvapaikka (Refuge)",
            "Uusipunki (New Town)",
            "Valkoinen Kivi (Whitestone)",
            "Valtaistuin (Throne)",
            "Vapaanki (Free Town)",
            "Vihreä Paikka (Green Place)",
            "Viimeinen Koti (Last Home)",

            "Airdib (Blessed Waters)",
            "Airjinh (Clearwater)",
            "Akhir Jalan (Road’s End)",
            "Berdarah (Bloody)",
            "Bidang Bera (Fallow Field)",
            "Candibula (Moon Temple)",
            "Ditingga (Forsaken)",
            "Emasungai (Gold Creek)",
            "Gunung (Mountain)",
            "Kayu (Timber)",
            "Kuil (Temple)",
            "Ladang Hijau (Greenfield)",
            "Lembah (Valley)",
            "Menjau (Far Away)",
            "Ngarai (Canyon)",
            "Persimpangan (Crossroads)",
            "Puncakit (Hilltop)",
            "Sungairac (Poison River)",
            "Teibing (Cliffside)",
            "Tempat Aman (Safe Place)",
            "Tempat Istir (Rest Place)",
            "Terkutuk (Cursed)",
            "Tersentu (Touched by God)",
            "Wahah (Oasis)",
            "Yangtinggi (High Tower)"
    };

}
