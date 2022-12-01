public enum Type_Moteur_Enum {

    DIESEL,
    ESSENCE,
    GPL,
    ELECTRIQUE,
    GAZNAT,
    SANS;

    public String toString(){
        String r = "";

        switch(this){
            case DIESEL:
                r = "DIESEL";
                break;
            case GPL:
                r = "GPL";
                break;
            case ESSENCE:
                r = "ESSENCE";
                break;
            case ELECTRIQUE:
                r = "ELECTRIQUE";
                break;
            case GAZNAT:
                r = "GAZNAT";
                break;
            default:
                r = "SANS";
                break;
        }
        return r;
    }
}
