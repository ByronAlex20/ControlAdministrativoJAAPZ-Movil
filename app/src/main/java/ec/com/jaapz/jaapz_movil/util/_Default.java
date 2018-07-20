package ec.com.jaapz.jaapz_movil.util;

public class _Default {
    protected String _mensaje;
    protected boolean _estado;
    public _Default(){
        this._estado = true;
        this._mensaje = "";
    }

    public String get_mensaje() {
        return _mensaje;
    }

    public void set_mensaje(String _mensaje) {
        this._mensaje = _mensaje;
    }

    public boolean is_estado() {
        return _estado;
    }

    public void set_estado(boolean _estado) {
        this._estado = _estado;
    }
}
