package clases.Medico;

public class ndConsultaClinica {

    private String id, historia, responsable, nombre, parentesco, motivo, t, peso, talla, fc, fr, pa, cefalico, abdominal, fisico1, fisico2, fisico3,
            problemas, cie1, cie2, cie3, cie4, tratamiento, recomendaciones, cup, usuario, fecha;
    private String cabezaCuello, cardioPulmonar, abdomen, pelvis, extremidades, orden, organosSentidos, musculoEsqueletico, neurologico, pielAnexos;
    private String familiar, fumador, ingesta, alergias, toxicos, medicamentos, mamas, gluteos,
            patologicos, psiquiatricos, quirurgicos, ultimaMenstruacion, ultimaCitologia,
            resultadosCitologia, embarazos, abortos, partos, cesarias, hijosVivos, planificacion, fotos, examenes;

    private String telefonoResponsable, ectopicos, cup2, observacionProcedimientos, revision, oido, vista, nariz, gusto, tacto;
    private String menarca, diagnosticoEspecializado, cc, torax, abdomen1, extremidades1, neurologico1, pielYfaneras;
    private String nutricionales;
    private String tipoDiagnostico, revisionSistemas, historialPersonal, traumaticos;

    public String getMamas() {
        return mamas;
    }

    public void setMamas(String mamas) {
        this.mamas = mamas;
    }

    public String getGluteos() {
        return gluteos;
    }

    public void setGluteos(String gluteos) {
        this.gluteos = gluteos;
    }

    public String getRevisionSistemas() {
        return revisionSistemas;
    }

    public void setRevisionSistemas(String revisionSistemas) {
        this.revisionSistemas = revisionSistemas;
    }

    public String getHistorialPersonal() {
        return historialPersonal;
    }

    public void setHistorialPersonal(String historialPersonal) {
        this.historialPersonal = historialPersonal;
    }

    public String getTraumaticos() {
        return traumaticos;
    }

    public void setTraumaticos(String traumaticos) {
        this.traumaticos = traumaticos;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getTorax() {
        return torax;
    }

    public void setTorax(String torax) {
        this.torax = torax;
    }

    public String getAbdomen1() {
        return abdomen1;
    }

    public void setAbdomen1(String abdomen1) {
        this.abdomen1 = abdomen1;
    }

    public String getExtremidades1() {
        return extremidades1;
    }

    public void setExtremidades1(String extremidades1) {
        this.extremidades1 = extremidades1;
    }

    public String getNeurologico1() {
        return neurologico1;
    }

    public void setNeurologico1(String neurologico1) {
        this.neurologico1 = neurologico1;
    }

    public String getPielYfaneras() {
        return pielYfaneras;
    }

    public void setPielYfaneras(String pielYfaneras) {
        this.pielYfaneras = pielYfaneras;
    }

    public String getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(String tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
    }

    public String getNutricionales() {
        return nutricionales;
    }

    public void setNutricionales(String nutricionales) {
        this.nutricionales = nutricionales;
    }

    public String getMenarca() {
        return menarca;
    }

    public void setMenarca(String menarca) {
        this.menarca = menarca;
    }

    public String getDiagnosticoEspecializado() {
        return diagnosticoEspecializado;
    }

    public void setDiagnosticoEspecializado(String diagnosticoEspecializado) {
        this.diagnosticoEspecializado = diagnosticoEspecializado;
    }

    public String getTelefonoResponsable() {
        return telefonoResponsable;
    }

    public void setTelefonoResponsable(String telefonoResponsable) {
        this.telefonoResponsable = telefonoResponsable;
    }

    public String getEctopicos() {
        return ectopicos;
    }

    public void setEctopicos(String ectopicos) {
        this.ectopicos = ectopicos;
    }

    public String getCup2() {
        return cup2;
    }

    public void setCup2(String cup2) {
        this.cup2 = cup2;
    }

    public String getObservacionProcedimientos() {
        return observacionProcedimientos;
    }

    public void setObservacionProcedimientos(String observacionProcedimientos) {
        this.observacionProcedimientos = observacionProcedimientos;
    }

    public String getOido() {
        return oido;
    }

    public void setOido(String oido) {
        this.oido = oido;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }

    public String getNariz() {
        return nariz;
    }

    public void setNariz(String nariz) {
        this.nariz = nariz;
    }

    public String getGusto() {
        return gusto;
    }

    public void setGusto(String gusto) {
        this.gusto = gusto;
    }

    public String getTacto() {
        return tacto;
    }

    public void setTacto(String tacto) {
        this.tacto = tacto;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getFamiliar() {
        return familiar;
    }

    public void setFamiliar(String familiar) {
        this.familiar = familiar;
    }

    public String getFumador() {
        return fumador;
    }

    public void setFumador(String fumador) {
        this.fumador = fumador;
    }

    public String getIngesta() {
        return ingesta;
    }

    public void setIngesta(String ingesta) {
        this.ingesta = ingesta;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getToxicos() {
        return toxicos;
    }

    public void setToxicos(String toxicos) {
        this.toxicos = toxicos;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getPatologicos() {
        return patologicos;
    }

    public void setPatologicos(String patologicos) {
        this.patologicos = patologicos;
    }

    public String getPsiquiatricos() {
        return psiquiatricos;
    }

    public void setPsiquiatricos(String psiquiatricos) {
        this.psiquiatricos = psiquiatricos;
    }

    public String getQuirurgicos() {
        return quirurgicos;
    }

    public void setQuirurgicos(String quirurgicos) {
        this.quirurgicos = quirurgicos;
    }

    public String getUltimaMenstruacion() {
        return ultimaMenstruacion;
    }

    public void setUltimaMenstruacion(String ultimaMenstruacion) {
        this.ultimaMenstruacion = ultimaMenstruacion;
    }

    public String getUltimaCitologia() {
        return ultimaCitologia;
    }

    public void setUltimaCitologia(String ultimaCitologia) {
        this.ultimaCitologia = ultimaCitologia;
    }

    public String getResultadosCitologia() {
        return resultadosCitologia;
    }

    public void setResultadosCitologia(String resultadosCitologia) {
        this.resultadosCitologia = resultadosCitologia;
    }

    public String getEmbarazos() {
        return embarazos;
    }

    public void setEmbarazos(String embarazos) {
        this.embarazos = embarazos;
    }

    public String getAbortos() {
        return abortos;
    }

    public void setAbortos(String abortos) {
        this.abortos = abortos;
    }

    public String getPartos() {
        return partos;
    }

    public void setPartos(String partos) {
        this.partos = partos;
    }

    public String getCesarias() {
        return cesarias;
    }

    public void setCesarias(String cesarias) {
        this.cesarias = cesarias;
    }

    public String getHijosVivos() {
        return hijosVivos;
    }

    public void setHijosVivos(String hijosVivos) {
        this.hijosVivos = hijosVivos;
    }

    public String getPlanificacion() {
        return planificacion;
    }

    public void setPlanificacion(String planificacion) {
        this.planificacion = planificacion;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public String getExamenes() {
        return examenes;
    }

    public void setExamenes(String examenes) {
        this.examenes = examenes;
    }

    public String getOrganosSentidos() {
        return organosSentidos;
    }

    public void setOrganosSentidos(String organosSentidos) {
        this.organosSentidos = organosSentidos;
    }

    public String getMusculoEsqueletico() {
        return musculoEsqueletico;
    }

    public void setMusculoEsqueletico(String musculoEsqueletico) {
        this.musculoEsqueletico = musculoEsqueletico;
    }

    public String getNeurologico() {
        return neurologico;
    }

    public void setNeurologico(String neurologico) {
        this.neurologico = neurologico;
    }

    public String getPielAnexos() {
        return pielAnexos;
    }

    public void setPielAnexos(String pielAnexos) {
        this.pielAnexos = pielAnexos;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getCabezaCuello() {
        return cabezaCuello;
    }

    public void setCabezaCuello(String cabezaCuello) {
        this.cabezaCuello = cabezaCuello;
    }

    public String getCardioPulmonar() {
        return cardioPulmonar;
    }

    public void setCardioPulmonar(String cardioPulmonar) {
        this.cardioPulmonar = cardioPulmonar;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getPelvis() {
        return pelvis;
    }

    public void setPelvis(String pelvis) {
        this.pelvis = pelvis;
    }

    public String getExtremidades() {
        return extremidades;
    }

    public void setExtremidades(String extremidades) {
        this.extremidades = extremidades;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHistoria() {
        return historia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public String getCefalico() {
        return cefalico;
    }

    public void setCefalico(String cefalico) {
        this.cefalico = cefalico;
    }

    public String getAbdominal() {
        return abdominal;
    }

    public void setAbdominal(String abdominal) {
        this.abdominal = abdominal;
    }

    public String getFisico1() {
        return fisico1;
    }

    public void setFisico1(String fisico1) {
        this.fisico1 = fisico1;
    }

    public String getFisico2() {
        return fisico2;
    }

    public void setFisico2(String fisico2) {
        this.fisico2 = fisico2;
    }

    public String getFisico3() {
        return fisico3;
    }

    public void setFisico3(String fisico3) {
        this.fisico3 = fisico3;
    }

    public String getProblemas() {
        return problemas;
    }

    public void setProblemas(String problemas) {
        this.problemas = problemas;
    }

    public String getCie1() {
        return cie1;
    }

    public void setCie1(String cie1) {
        this.cie1 = cie1;
    }

    public String getCie2() {
        return cie2;
    }

    public void setCie2(String cie2) {
        this.cie2 = cie2;
    }

    public String getCie3() {
        return cie3;
    }

    public void setCie3(String cie3) {
        this.cie3 = cie3;
    }

    public String getCie4() {
        return cie4;
    }

    public void setCie4(String cie4) {
        this.cie4 = cie4;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
