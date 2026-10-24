package Modelo.ConsumirServicio;

public class RespuestaServicio {

    private final int codigoHttp;
    private final String cuerpo;
    private final String mensajeError;

    private RespuestaServicio(int codigoHttp, String cuerpo, String mensajeError) {
        this.codigoHttp = codigoHttp;
        this.cuerpo = cuerpo == null ? "" : cuerpo;
        this.mensajeError = mensajeError;
    }

    public static RespuestaServicio deHttp(int codigoHttp, String cuerpo) {
        return new RespuestaServicio(codigoHttp, cuerpo, null);
    }

    public static RespuestaServicio deError(String mensajeError) {
        return new RespuestaServicio(0, "", mensajeError == null ? "Error desconocido" : mensajeError);
    }

    public int getCodigoHttp() {
        return codigoHttp;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public boolean huboErrorDeConexion() {
        return mensajeError != null;
    }

    public boolean isExitosa() {
        return !huboErrorDeConexion() && codigoHttp >= 200 && codigoHttp < 300;
    }
}
