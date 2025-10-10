/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enviroments;

/**
 *
 * @author sebastian.londono
 */
public class enviroments {

    public String urlCrearEmisor;
    public String tokenAutorizacion;

    public void generarEnviroments(boolean conURLProduccion) {
        if (conURLProduccion) {
            tokenAutorizacion = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm92ZWVkb3IiOiJIYXNOZXQiLCJpZCI6Mzd9.zeExjL1sFYqn6C7YRRHs_2L8Xk2UVZYLFtPHniKCT2Y";
            urlCrearEmisor = "https://alfaprod.dominadigital.com.co/api/CreacionEmisores";
        } else {
            tokenAutorizacion = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm92ZWVkb3IiOiJIYXNOZXQiLCJpZCI6Mzd9.zeExjL1sFYqn6C7YRRHs_2L8Xk2UVZYLFtPHniKCT2Y";
            urlCrearEmisor = "https://alfauat.dominadigital.com.co/api/CreacionEmisores";
        }
    }

}
