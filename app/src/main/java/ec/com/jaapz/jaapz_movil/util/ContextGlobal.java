package ec.com.jaapz.jaapz_movil.util;

public class ContextGlobal {
    String scriptBD = "";
    private final static ContextGlobal instance = new ContextGlobal();
    public static ContextGlobal getInstance() {
        return instance;
    }

    public String getScriptBD() {
        return scriptBD;
    }

    public void setScriptBD(String scriptBD) {
        this.scriptBD = scriptBD;
    }
}
