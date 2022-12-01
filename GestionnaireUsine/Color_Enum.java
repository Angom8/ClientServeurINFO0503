public enum Color_Enum {

    BLEU,
    ROUGE,
    VERT,
    FUSHIA,
    ORANGE,
    TRANSPARENT;

    public String toString(){
        String r = "";

        switch(this){
            case BLEU:
                r = "BLEU";
                break;
            case ROUGE:
                r = "ROUGE";
                break;
            case VERT:
                r = "VERT";
                break;
            case FUSHIA:
                r = "FUSHIA";
                break;
            case ORANGE:
                r = "ORANGE";
                break;
            default:
                r = "TRANSPARENT";
                break;
        }
        return r;
    }

}
